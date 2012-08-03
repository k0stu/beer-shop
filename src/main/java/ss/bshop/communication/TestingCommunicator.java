package ss.bshop.communication;

import java.util.ArrayList;
import java.util.List;

import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletMobile;
import ss.bshop.mobile.entities.VisitMobile;

public class TestingCommunicator {

	public static List<ArticleMobile> getArticles() {
		List<ArticleMobile> toReturn = new ArrayList<ArticleMobile>();
		for (int i = 0; i < 5; i++) {
			ArticleMobile article = new ArticleMobile();
			article.setName("beer " + i);
			article.setPrice(10.51);
			article.setType("beverage");
			toReturn.add(article);
		}
		return toReturn;
	}
	public static List<OutletMobile> getOutletsForToday(String username) {
		List<OutletMobile> toReturn = new ArrayList<OutletMobile>();
		for (int i = 0; i < 5; i++) {
			OutletMobile outlet = new OutletMobile();
			outlet.setName("kiosk " + i);
			toReturn.add(outlet);
		}
		return toReturn;
	}
	public static void addVisit(VisitMobile visit) {
		// do nothing
	}
}
