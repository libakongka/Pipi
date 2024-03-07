package com.example.oauthsession.repository;

import com.example.oauthsession.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    int countByCategoryName(String categoryName);

    @Query("SELECT c.categoryNo FROM CategoryEntity c WHERE c.categoryName = :categoryName")
    int findCategoryNoByCategoryName(String categoryName);
}
