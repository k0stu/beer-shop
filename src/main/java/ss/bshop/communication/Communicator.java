package ss.bshop.communication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	private static final List<MediaType> mediaTypes =
			new ArrayList<MediaType>();
	static {
		mediaTypes.add(MediaType.APPLICATION_JSON);
	}
	private static final String uri = "BShopMaster/mobile/";
	private static final String TAG = "Communicator";
	@SuppressWarnings("unchecked")
	public static List<OutletMobile> getOutletsForToday(String username) {
		List response = Communicator.
				performRequest(Global.server + uri + "getoutlets/" + username);
		return response;
	}

	public static List<ArticleMobile> getArticles() {
		List response = Communicator
				.performRequest(Global.server + uri + "getgoods");
		return response;
	}

	public static void addVisit(VisitMobile visit) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<VisitMobile> entity = new HttpEntity(visit, requestHeaders);
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().
				add(new MappingJacksonHttpMessageConverter());
		String finalUri = Global.server + uri + "addvisit";
		template.exchange(finalUri, HttpMethod.POST, entity, Void.class);
	}

	private static List performRequest(String finalUri) {
		List responseList = new ArrayList();
		try {
			HttpHeaders requestHeaders = new HttpHeaders();
			Log.d(TAG, "Created headers");
			requestHeaders.setAccept(mediaTypes);
			Log.d(TAG, "Set accepted media types");
			HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
			Log.d(TAG, "Created request entity");
			RestTemplate template = new RestTemplate();
			Log.d(TAG, "Created rest template");
			template.getMessageConverters().
					add(new MappingJacksonHttpMessageConverter());
			Log.d(TAG, "Added message converter");
			@SuppressWarnings("rawtypes")
			ResponseEntity<List> response = null;
			response = template.exchange(finalUri, HttpMethod.GET,
			requestEntity, List.class);
			Log.d(TAG, "Fired exchange");
			if (response.getBody() instanceof List) {
				responseList = response.getBody();
			} else {
				Log.w(TAG, "Server returned not a list");
			}
			Log.d(TAG, "Received list");
		} catch (HttpClientErrorException hcee) {
			Log.e(TAG, hcee.getMessage());
		}
		return responseList;
	}
}
