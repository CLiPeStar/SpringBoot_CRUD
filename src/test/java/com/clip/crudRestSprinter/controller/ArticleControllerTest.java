package com.clip.crudRestSprinter.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.clip.crudRestSprinter.helper.HttpResponseArticle;
import com.clip.crudRestSprinter.model.ArticleDTO;
import com.clip.crudRestSprinter.service.IArticleService;
import com.google.gson.Gson;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTest {

    private static final String ARTICLE_PATH = "src\\test\\java\\com\\clip\\crudRestSprinter\\resources\\article.json";
    private static final String ARTICLES_PATH = "src\\test\\java\\com\\clip\\crudRestSprinter\\resources\\articles.json";

    private HttpResponseArticle response = new HttpResponseArticle();

    @InjectMocks
    private ArticleController articleController;

    @Mock
    private IArticleService articleService;

    @BeforeEach
    public void setup() {
        response = new HttpResponseArticle();
    }

    @Test
    public void createArticleControllerTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDTO articleExpected = new Gson().fromJson(reader, ArticleDTO.class);
        response.setStatus(HttpStatus.CREATED);
        response.setArticleDto(articleExpected);
        when(articleService.putArticle(any())).thenReturn(response);
        ResponseEntity<HttpResponseArticle> responseController = articleController.putArticle(articleExpected);
        assertThat(responseController.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseController.getBody().getArticleDto()).isEqualTo(articleExpected);
    }

    @Test
    public void listAllArticlesControllerTest() throws FileNotFoundException {
        BufferedReader readerArticles = new BufferedReader(new FileReader(ARTICLES_PATH));
        final List<ArticleDTO> articleExpected = new Gson().fromJson(readerArticles, List.class);
        response.setStatus(HttpStatus.OK);
        response.setArticleDtoList(articleExpected);
        when(articleService.getAllArticle()).thenReturn(response);
        ResponseEntity<HttpResponseArticle> responseController = articleController.getAllProduct();
        assertThat(responseController.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseController.getBody().getArticleDtoList()).isEqualTo(articleExpected);
    }

    @Test
    public void listArticleControllerTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDTO articleExpected = new Gson().fromJson(reader, ArticleDTO.class);
        response.setStatus(HttpStatus.OK);
        response.setArticleDto(articleExpected);
        when(articleService.getArticleById(anyString())).thenReturn(response);
        ResponseEntity<HttpResponseArticle> responseController = articleController.getArticleById("1");
        assertThat(responseController.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseController.getBody().getArticleDto()).isEqualTo(articleExpected);
    }

    @Test
    public void updateArticlesControllerTest() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDTO articleExpected = new Gson().fromJson(reader, ArticleDTO.class);
        response.setStatus(HttpStatus.OK);
        response.setArticleDto(articleExpected);
        when(articleService.postArticle(any())).thenReturn(response);
        ResponseEntity<HttpResponseArticle> responseController = articleController.postArticle(articleExpected);
        assertThat(responseController.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseController.getBody().getArticleDto()).isEqualTo(articleExpected);
    }

    @Test
    public void deleteArticlesControllerTest() {
        response.setStatus(HttpStatus.OK);
        when(articleService.deleteArticleById(anyString())).thenReturn(response);
        ResponseEntity<HttpResponseArticle> responseEntity = articleController.deleteArticleById("1");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
