package com.example.oauthsession.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "attachment")
public class AttachmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentNo;  //첨부파일번호

    private Long refProdNo; //상품번호

    private Long refRevwNo; //리뷰번호

    private Long refInqNo;  //문의번호

    private String origFileName;    //원본파일명

    private String saveFileName;    //저장파일명

    private String savePath;    //저장경로

    private String thumbnailPath;   //썸네일경로

    private String fileType;    //파일유형

    private String attachStatusYn;  //게재 여부, 없어도됨


}
