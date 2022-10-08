package com.clip.crudRestSprinter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clip.crudRestSprinter.model.ArticleDTO;

public interface ArticleRepository extends JpaRepository<ArticleDTO, String> {

}
