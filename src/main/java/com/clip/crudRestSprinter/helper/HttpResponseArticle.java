package com.clip.crudRestSprinter.helper;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.clip.crudRestSprinter.model.ArticleDTO;

import lombok.Data;

@Data
public class HttpResponseArticle {

    private HttpStatus status;
    private String msg;
    private ArticleDTO articleDto;
    private List<ArticleDTO> articleDtoList;

    public HttpResponseArticle(HttpStatus status, String msg, ArticleDTO articleDto) {
        this.status = status;
        this.msg = msg;
        this.articleDto = articleDto;
    }

    public HttpResponseArticle(HttpStatus status, String msg, List<ArticleDTO> articleDtoList) {
        this.status = status;
        this.msg = msg;
        this.articleDtoList = articleDtoList;
    }

    public HttpResponseArticle(HttpStatus status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public HttpResponseArticle() {

    }

}
