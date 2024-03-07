package com.example.oauthsession.controller;

import com.example.oauthsession.dto.CategoryDTO;
import com.example.oauthsession.entity.CategoryEntity;
import com.example.oauthsession.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/list")
    public String listPage(){   //헤더 카테고리 페이지 호출

        return "product/list";
    }
}
