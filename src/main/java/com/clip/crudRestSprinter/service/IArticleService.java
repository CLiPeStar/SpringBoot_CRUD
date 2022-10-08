package com.clip.crudRestSprinter.service;

import com.clip.crudRestSprinter.helper.HttpResponseArticle;
import com.clip.crudRestSprinter.model.ArticleDTO;

public interface IArticleService {

    public HttpResponseArticle putArticle(ArticleDTO articleDTO);

    public HttpResponseArticle postArticle(ArticleDTO articleDTO);

    public HttpResponseArticle deleteArticleById(String barCode);

    public HttpResponseArticle getArticleById(String barCode);

    public HttpResponseArticle getAllArticle();

    public void flushCache();

    public void clearCacheAllArticle();

}