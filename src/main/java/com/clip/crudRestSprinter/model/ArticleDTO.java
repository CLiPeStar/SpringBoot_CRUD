package com.clip.crudRestSprinter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "articles")
public class ArticleDTO {

    @Id
    @Setter(AccessLevel.NONE)
    private String barCode;
    private String itemName;
    private String dscription;
    private float price;

    protected ArticleDTO() {
    }

    public ArticleDTO(String barcode, String itemName, String dscription, float price) {
        this.barCode = barcode;
        this.itemName = itemName;
        this.dscription = dscription;
        this.price = price;
    }

    public void updateArticle(ArticleDTO articleDTO) {
        this.itemName = articleDTO.itemName;
        this.dscription = articleDTO.dscription;
        this.price = articleDTO.price;
    }

}
