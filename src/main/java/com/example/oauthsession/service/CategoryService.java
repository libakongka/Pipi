package com.example.oauthsession.service;

import com.example.oauthsession.entity.CategoryEntity;

public interface CategoryService {
    boolean checkCategoryNameExists(String categoryName);
    CategoryEntity addNewCategory(String categoryName, String categorySection);
    int checkCategoryNo(String categoryName);
}
