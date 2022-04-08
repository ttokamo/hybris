package de.hybris.news.dao;

import de.hybris.platform.core.model.NewsModel;

import java.util.List;

public interface NewsDAO {

    /**
     * Returns the entire news list if it exists. If not, returns an empty list
     * @return all news
     */
    List<NewsModel> findAllNews();


    /**
     * Returns the specified news by code
     * @param code news search code
     * @return news
     */
    List<NewsModel> findNewsByCode(String code);
}
