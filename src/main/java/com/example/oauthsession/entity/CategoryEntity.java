package com.example.oauthsession.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryNo;    //카테고리번호
    private String categoryName;    //카테고리명
    private String categorySection; //카테고리섹션
}
