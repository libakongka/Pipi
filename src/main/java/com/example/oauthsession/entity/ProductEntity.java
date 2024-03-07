package com.example.oauthsession.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodNo;    //상품번호
    private Long categoryNo;    //카테고리번호(FK)
    private String prodName;    //상품명
    private String prodDesc;    //상품한줄설명
    private BigDecimal prodPrice;   //상품원가
    private BigDecimal discount;    //할인율
    private String tag; //상품태그
    private String prodOrigin;   //원산지
    private String bodyColor;   //상품 컬러
    private String prodDetailContent;   //상품상세설명

    @Column(columnDefinition = "int default 0")
    private int prodDetailViewCount;    //상품상세조회수
    private int amount; //입고량


    @CreatedDate
    private LocalDateTime prodUploadDate;   //상품등록일

    @LastModifiedDate
    private LocalDateTime prodModifyDate;   //상품수정일




}
