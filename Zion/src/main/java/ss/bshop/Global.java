package ss.bshop;

import java.util.Hashtable;

import ss.bshop.mobile.entities.ArticleMobile;

public class Global {
	public static String username = "";
	/**
	 * Goods are stored in string-article pairs, where string represents good's
	 * name. This is made for easy using of android spinners (getting names for
	 * the list and getting article by it's name from the list).
	 */
	public static Hashtable<String, ArticleMobile> goods = new Hashtable();
}
