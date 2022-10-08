package com.clip.crudRestSprinter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.clip.crudRestSprinter.helper.EArticleStatus;
import com.clip.crudRestSprinter.helper.HttpResponseArticle;
import com.clip.crudRestSprinter.model.ArticleDTO;
import com.clip.crudRestSprinter.repository.ArticleRepository;
import com.clip.crudRestSprinter.utils.LoggerSharedArticle;

@Service
public class ArticleServiceImp implements IArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public HttpResponseArticle putArticle(ArticleDTO articleDTO) {
        HttpResponseArticle response = new HttpResponseArticle();
        try {
            if (existArticleById(articleDTO.getBarCode())) {
                response.setStatus(HttpStatus.BAD_REQUEST);
                response.setMsg(EArticleStatus.ARTICLE_ALREADY_EXIST.msg());
                LoggerSharedArticle.logWarn(EArticleStatus.ARTICLE_ALREADY_EXIST.msg());
                return response;
            }
            response.setStatus(HttpStatus.CREATED);
            response.setMsg(EArticleStatus.ARTICLE_CREATED.msg());
            response.setArticleDto(articleRepository.save(articleDTO));
            clearCacheAllArticle();
            LoggerSharedArticle.logInfoSuccess();
        } catch (Exception e) {
            return setResponseInternalServerError(e);
        }
        return response;
    }

    @Override
    @CachePut(cacheNames = "articles", key = "#articleDTO.barCode")
    public HttpResponseArticle postArticle(ArticleDTO articleDTO) {
        HttpResponseArticle response = new HttpResponseArticle();
        try {
            if (!existArticleById(articleDTO.getBarCode()))
                return setResponseNotExistArticle();

            ArticleDTO articleUpdate = articleRepository.findById(articleDTO.getBarCode()).get();
            response.setStatus(HttpStatus.ACCEPTED);
            response.setMsg(EArticleStatus.ARTICLE_UPDATED.msg());
            articleUpdate.updateArticle(articleDTO);
            response.setArticleDto(articleRepository.save(articleUpdate));
            LoggerSharedArticle.logInfoSuccess();
        } catch (Exception e) {
            return setResponseInternalServerError(e);
        }
        return response;
    }

    @Override
    public HttpResponseArticle deleteArticleById(String barCode) {
        HttpResponseArticle response = new HttpResponseArticle();
        try {
            if (!existArticleById(barCode))
                return setResponseNotExistArticle();

            articleRepository.deleteById(barCode);
            response.setStatus(HttpStatus.OK);
            response.setMsg(EArticleStatus.ARTICLE_DELETED.msg());
            LoggerSharedArticle.logInfoSuccess();
        } catch (Exception e) {
            return setResponseInternalServerError(e);
        }
        return response;
    }

    @Override
    @Cacheable(cacheNames = "articles")
    public HttpResponseArticle getArticleById(String barCode) {
        HttpResponseArticle response = new HttpResponseArticle();
        try {
            if (!existArticleById(barCode))
                return setResponseNotExistArticle();

            ArticleDTO article = articleRepository.findById(barCode).get();
            response.setStatus(HttpStatus.OK);
            response.setMsg(EArticleStatus.OK.msg());
            response.setArticleDto(article);
            LoggerSharedArticle.logInfoSuccess();
        } catch (Exception e) {
            return setResponseInternalServerError(e);
        }
        return response;
    }

    @Override
    @Cacheable(cacheNames = "allArticles")
    public HttpResponseArticle getAllArticle() {
        HttpResponseArticle response = new HttpResponseArticle();
        try {
            if (articleRepository.count() <= 0) {
                response.setStatus(HttpStatus.NOT_FOUND);
                response.setMsg(EArticleStatus.ARTICLE_LIST_EMPTY.msg());
                LoggerSharedArticle.logWarn(EArticleStatus.ARTICLE_LIST_EMPTY.msg());
                return response;
            }

            response.setStatus(HttpStatus.OK);
            response.setMsg(EArticleStatus.OK.msg());
            response.setArticleDtoList(articleRepository.findAll());
            LoggerSharedArticle.logInfoSuccess();
        } catch (Exception e) {
            return setResponseInternalServerError(e);
        }
        return response;
    }

    @Override
    @CacheEvict(cacheNames = { "allArticles", "articles" }, allEntries = true)
    public void flushCache() {
        LoggerSharedArticle.logInfoSuccess();
    }

    @Override
    @CacheEvict(cacheNames = "allArticles", allEntries = true)
    public void clearCacheAllArticle() {
        LoggerSharedArticle.logInfo("Cleared cache allArticles");
    }

    private boolean existArticleById(String barCode) {
        return articleRepository.findById(barCode).isPresent();
    }

    private HttpResponseArticle setResponseNotExistArticle() {
        HttpResponseArticle response = new HttpResponseArticle();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setMsg(EArticleStatus.ARTICLE_NO_EXIST.msg());
        LoggerSharedArticle.logWarn(EArticleStatus.ARTICLE_NO_EXIST.msg());
        return response;
    }

    private HttpResponseArticle setResponseInternalServerError(Exception e) {
        HttpResponseArticle response = new HttpResponseArticle();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        response.setMsg(e.getMessage());
        LoggerSharedArticle.logError(e.getMessage());
        return response;
    }

}