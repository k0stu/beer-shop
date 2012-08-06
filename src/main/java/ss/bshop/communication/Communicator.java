package ss.bshop.communication;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import android.util.Log;
import android.widget.Toast;

import ss.bshop.Global;
import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletMobile;
import ss.bshop.mobile.entities.VisitMobile;

public class Communicator {

	private static final String uri = "/BShopMaster/mobile/";
	private static final String TAG = "Communicator";
	private static final String extension = ".mob";
	public static List<OutletMobile> getOutletsForToday(String username) {
		OutletMobile[] outlets = (OutletMobile[]) Communicator
			.performRequest(Global.server + uri + "getoutlets/"	+ username
					+ extension, OutletMobile[].class);
		return Arrays.asList(outlets);
	}

	public static List<ArticleMobile> getArticles() {
		ArticleMobile[] articles = (ArticleMobile[]) Communicator
				.performRequest(Global.server + uri + "getgoods" + extension,
						ArticleMobile[].class);
		return Arrays.asList(articles);
	}

	public static String addVisit(VisitMobile visit) {
		HttpHeaders requestHeaders = new HttpHeaders();
		HttpEntity<VisitMobile> entity = new HttpEntity<VisitMobile>(visit, requestHeaders);
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().
				add(new StringHttpMessageConverter());
		template.getMessageConverters().
				add(new MappingJacksonHttpMessageConverter());
		String finalUri = Global.server + uri + "addvisit" + extension;
		Log.d(TAG, "Hitting the " + finalUri);
		ResponseEntity<String> response = template.exchange(finalUri,
				HttpMethod.POST, entity, String.class);
		String responseString = response.getBody();
		return responseString;
	}

	private static Object[] performRequest(String finalUri, Class clazz) {
		Object[] result = null;
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			Log.d(TAG, "Created headers");
			requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			Log.d(TAG, "Set accepted media types");
			HttpEntity<?> requestEntity =
					new HttpEntity<Object>(requestHeaders);
			Log.d(TAG, "Created request entity");
			RestTemplate template = new RestTemplate();
			Log.d(TAG, "Created rest template");
			template.getMessageConverters().
					add(new MappingJacksonHttpMessageConverter());
			Log.d(TAG, "Added message converter");
			@SuppressWarnings("rawtypes")
			ResponseEntity response = 
					template.exchange(finalUri, HttpMethod.GET,	requestEntity,
							clazz);
			Log.d(TAG, "Fired exchange");
			result = (Object[]) response.getBody();
			Log.d(TAG, "Received object is: " + Arrays.toString(result));
		} catch (HttpClientErrorException hcee) {
			Log.e(TAG, hcee.getMessage());
		}
		return result;
	}

	public static String login() {
		String input = Global.username + ":" + Global.password;
		HttpHeaders requestHeaders = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(input, requestHeaders);
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().
				add(new StringHttpMessageConverter());
		String finalUri = Global.server + uri + "login" + extension;
		Log.d(TAG, "Hitting the " + finalUri);
		ResponseEntity<String> response = template.exchange(finalUri,
				HttpMethod.POST, entity, String.class);
		String responseString = response.getBody();
		return responseString;
	}
}
