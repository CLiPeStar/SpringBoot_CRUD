package com.clip.crudRestSprinter.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.clip.crudRestSprinter.model.ArticleDTO;

public interface ArticleRepository extends JpaRepository<ArticleDTO, String> {

}
