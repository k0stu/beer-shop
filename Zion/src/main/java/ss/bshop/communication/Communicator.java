package ss.bshop.communication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.util.Log;

import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletMobile;
import ss.bshop.mobile.entities.VisitMobile;

public class Communicator {

	private static final List<MediaType> mediaTypes =
			new ArrayList<MediaType>();
	static {
		mediaTypes.add(MediaType.APPLICATION_JSON);
	}
	private static final String uri = "/mobile/";
	private static final String TAG = "Communicator";
	@SuppressWarnings("unchecked")
	public static List<OutletMobile> getOutletsForToday(String username) {
		List response = Communicator.
				performRequest(uri + "getoutlets/" + username);
		return response;
	}

	public static List<ArticleMobile> getArticles() {
		List response = Communicator.performRequest(uri + "getgoods");
		return response;
	}

	public void addOrder(VisitMobile visit) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<VisitMobile> entity = new HttpEntity(visit, requestHeaders);
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().
				add(new MappingJacksonHttpMessageConverter());
		String finalUri = uri + "addvisit";
		template.exchange(finalUri, HttpMethod.POST, entity, Void.class);
	}

	private static List performRequest(String finalUri) {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setAccept(mediaTypes);
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
		RestTemplate template = new RestTemplate();
		template.getMessageConverters().
				add(new MappingJacksonHttpMessageConverter());
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> response =
				template.exchange(finalUri, HttpMethod.GET,
				requestEntity, List.class);
		List responseList = null;
		if (response.getBody() instanceof List) {
			responseList = response.getBody();
		} else {
			Log.i(TAG, "Server returned not a list");
		}
		return responseList;

	}
}
