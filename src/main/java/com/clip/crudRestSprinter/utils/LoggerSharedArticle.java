package com.clip.crudRestSprinter.utils;

import com.clip.crudRestSprinter.model.ArticleDTO;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoggerSharedArticle {

    public static void logInfoArticle(String operation, String msg, ArticleDTO article) {
        log.info("*********" + operation + "*********");
        log.info(msg);
        log.info(article);
    }

    public static void logInfo(String operation, String msg) {
        log.info("*********" + operation + "*********");
        log.info(msg);
    }

    public static void logInfo(String msg) {
        log.info(msg);
    }

    public static void logInfoSuccess() {
        log.info("Operación realizada con éxito");
    }

    public static void logWarn(String msg) {
        log.warn(msg);
    }

    public static void logError(String msg) {
        log.error(msg);
    }

}
