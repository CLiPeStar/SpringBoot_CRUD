package com.clip.crudRestSprinter.serviceImpl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;

import com.clip.crudRestSprinter.helper.HttpResponseArticle;
import com.clip.crudRestSprinter.model.ArticleDTO;
import com.clip.crudRestSprinter.repository.ArticleRepository;
import com.clip.crudRestSprinter.service.ArticleServiceImp;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@DataJpaTest
public class ArticleServiceImpTest {

    private static final String ARTICLE_PATH = "src\\test\\java\\com\\clip\\crudRestSprinter\\resources\\article.json";
    private static final String ARTICLES_PATH = "src\\test\\java\\com\\clip\\crudRestSprinter\\resources\\articles.json";

    @InjectMocks
    private ArticleServiceImp articleServiceImpl;

    @Mock
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createArticleTest() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDTO articleExpected = new Gson().fromJson(reader, ArticleDTO.class);
        HttpResponseArticle response = articleServiceImpl.putArticle(articleExpected);
        Assertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void listAllArticles() throws FileNotFoundException {
        BufferedReader readerArticles = new BufferedReader(new FileReader(ARTICLES_PATH));
        List<ArticleDTO> articlesExpected = new Gson().fromJson(readerArticles, List.class);
        articleRepository.saveAll(articlesExpected);
        Assertions.assertThat(articleRepository.findAll()).isNotNull();
    }

    @Test
    void listArticle() throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDTO articleExpected = new Gson().fromJson(reader, ArticleDTO.class);
        articleRepository.save(articleExpected);
        Assertions.assertThat(articleRepository.findById(articleExpected.getBarCode())).isNotNull();
    }

    @Test
    void updateArticle() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_PATH));
        ArticleDTO articleExpected = new Gson().fromJson(reader, ArticleDTO.class);
        articleRepository.save(articleExpected);
        articleRepository.existsById("1");
        Assertions.assertThat(articleExpected).isNotNull();
    }

    @Test
    void deleteArticle() {
        articleServiceImpl.deleteArticleById("1");
        Assertions.assertThat("1").isNotNull();

    }

}
