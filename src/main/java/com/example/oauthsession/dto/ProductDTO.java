package com.example.oauthsession.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductDTO {
    private String categoryNo;    //카테고리번호(FK)
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
}
