package com.clip.crudRestSprinter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clip.crudRestSprinter.helper.HttpResponseArticle;
import com.clip.crudRestSprinter.model.ArticleDTO;
import com.clip.crudRestSprinter.service.IArticleService;
import com.clip.crudRestSprinter.utils.LoggerSharedArticle;

@CrossOrigin(origins = "http://localhost:8085")
@RestController
public class ArticleController {

    @Autowired
    public IArticleService articleService;

    @PostMapping("postArticle")
    public ResponseEntity<HttpResponseArticle> postArticle(@RequestBody ArticleDTO articleDto) {
        LoggerSharedArticle.logInfoArticle("POST", "Request to update article", articleDto);
        HttpResponseArticle response = articleService.postArticle(articleDto);
        articleService.clearCacheAllArticle();
        return new ResponseEntity<HttpResponseArticle>(response, response.getStatus());
    }

    @PutMapping("putArticle")
    public ResponseEntity<HttpResponseArticle> putArticle(@RequestBody ArticleDTO articleDto) {
        LoggerSharedArticle.logInfoArticle("PUT", "Request to insert article", articleDto);
        HttpResponseArticle response = articleService.putArticle(articleDto);
        articleService.clearCacheAllArticle();
        return new ResponseEntity<HttpResponseArticle>(response, response.getStatus());
    }

    @GetMapping("/getAllArticles")
    public ResponseEntity<HttpResponseArticle> getAllProduct() {
        LoggerSharedArticle.logInfo("GET", "Request to obtain all articles");
        HttpResponseArticle response = articleService.getAllArticle();
        return new ResponseEntity<HttpResponseArticle>(response, response.getStatus());
    }

    @GetMapping(path = "/getArticleById", params = "idArticle")
    public ResponseEntity<HttpResponseArticle> getArticleById(@RequestParam String idArticle) {
        LoggerSharedArticle.logInfo("GET", "Request for item data with id: " + idArticle);
        HttpResponseArticle response = articleService.getArticleById(idArticle);
        return new ResponseEntity<HttpResponseArticle>(response, response.getStatus());
    }

    @DeleteMapping(path = "/deleteArticleById", params = "idArticle")
    public ResponseEntity<HttpResponseArticle> deleteArticleById(@RequestParam String idArticle) {
        LoggerSharedArticle.logInfo("DELETE", "Request to remove from the article with id: " + idArticle);
        HttpResponseArticle response = articleService.deleteArticleById(idArticle);
        return new ResponseEntity<HttpResponseArticle>(response, response.getStatus());
    }

    @GetMapping("/flushcache")
    public ResponseEntity<String> flushCache() {
        LoggerSharedArticle.logInfo("GET", "Clear Caché");
        articleService.flushCache();
        return new ResponseEntity<String>("Cache Flushed!", HttpStatus.OK);
    }

}
