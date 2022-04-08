package de.hybris.news.dao.impl;

import de.hybris.news.dao.NewsDAO;
import de.hybris.platform.core.model.NewsModel;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;

import java.util.List;

public class DefaultNewsDAOImpl implements NewsDAO {
    private FlexibleSearchService flexibleSearchService;

    @Override
    public List<NewsModel> findAllNews() {
        final String query =
                "SELECT {" + NewsModel.PK + "} " +
                "FROM {" + NewsModel._TYPECODE + "}";

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        return flexibleSearchService.<NewsModel> search(searchQuery).getResult();
    }

    @Override
    public List<NewsModel> findNewsByCode(final String code) {
        final String query =
                "SELECT {" + NewsModel.PK + "} " +
                "FROM {" + NewsModel._TYPECODE + "} " +
                "WHERE {" + NewsModel.CODE + "}=?code";

        final FlexibleSearchQuery searchQuery = new FlexibleSearchQuery(query);
        searchQuery.addQueryParameter("code", code);
        return flexibleSearchService.<NewsModel> search(searchQuery).getResult();
    }

    public void setFlexibleSearchService(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }
}
