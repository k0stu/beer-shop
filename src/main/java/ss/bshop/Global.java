package ss.bshop;

import java.util.Hashtable;

import ss.bshop.mobile.entities.ArticleMobile;
import ss.bshop.mobile.entities.OutletMobile;

public class Global {
	public static String username = "";
	/**
	 * Goods are stored in string-article pairs, where string represents good's
	 * name. This is made for easy using of android spinners (getting names for
	 * the list and getting article by it's name from the list).
	 */
	public static Hashtable<String, ArticleMobile> goods = new Hashtable();
	/**
	 * objectStorage is here for passing objects between activities. The
	 * problem is that to pass an object in a bundle it has to implement special
	 * interfaces, while primitives are passed with no additional actions. So
	 * you put your object in objectStorage, pass the key and get it in other
	 * activity. Don't forget to remove object from the table when you extracted
	 * it.
	 */
	public static Hashtable<String, Object> objectStorage = new Hashtable();
	public static Hashtable<String, OutletMobile> outlets = new Hashtable();
}
