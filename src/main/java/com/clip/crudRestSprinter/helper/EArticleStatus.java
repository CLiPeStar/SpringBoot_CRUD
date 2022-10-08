package com.clip.crudRestSprinter.helper;

public enum EArticleStatus {

    OK("OK"),
    ARTICLE_LIST_EMPTY("There are no items in the system"),
    ARTICLE_DELETED("Article successfully removed"),
    ARTICLE_CREATED("Successfully created article"),
    ARTICLE_UPDATED("Article successfully updated"),
    ARTICLE_DUPLICATED("Duplicate Article"),
    ARTICLE_ALREADY_EXIST("Article already exists"),
    ARTICLE_NO_EXIST("Article does not exist");

    private String msg;

    EArticleStatus(String msg) {
        this.msg = msg;
    }

    public String msg() {
        return this.msg;
    }

}