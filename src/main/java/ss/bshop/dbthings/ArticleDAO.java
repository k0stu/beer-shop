package ss.bshop.dbthings;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import ss.bshop.mobile.entities.ArticleMobile;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.support.ConnectionSource;

public class ArticleDAO extends BaseDaoImpl<ArticleMobile, Integer> {

	protected ArticleDAO(ConnectionSource connectionSource,
			Class<ArticleMobile> dataClass) throws SQLException {
		super(connectionSource, dataClass);
	}

	public List<ArticleMobile> getAllArticles() throws SQLException {
		return this.queryForAll();
	}
	public void saveAll(final List<ArticleMobile> articles) throws SQLException {
		TransactionManager.callInTransaction(connectionSource,
				new Callable<Void>() {
					@Override
					public Void call() throws Exception {
						for (ArticleMobile article : articles) {
							ArticleDAO.this.createOrUpdate(article);
						}
						return null;
					}
		});
	}
}
