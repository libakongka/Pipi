package com.example.oauthsession.service;

import com.example.oauthsession.dto.CategoryDTO;
import com.example.oauthsession.dto.ProductDTO;
import com.example.oauthsession.entity.CategoryEntity;
import com.example.oauthsession.entity.ProductEntity;
import com.example.oauthsession.repository.CategoryRepository;
import com.example.oauthsession.repository.ProductRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<CategoryDTO> getCategoryList() {
        List<CategoryEntity> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTO = new ArrayList<>();
        for (CategoryEntity category : categories) {
            categoryDTO.add(new CategoryDTO(category.getCategoryNo(), category.getCategoryName(), category.getCategorySection()));
            // CategoryDTO 생성자와 매칭되는 방식으로 객체를 생성해야 합니다.
        }
        return categoryDTO;
    }

    @Override
    public boolean checkCategoryNameExists(String categoryName) {
        return categoryRepository.countByCategoryName(categoryName) > 0;
    }

    @Override
    @Transactional
    public CategoryEntity addNewCategory(String categoryName, String categorySection) {
        if (!checkCategoryNameExists(categoryName)) {
            CategoryEntity category = new CategoryEntity();
            category.setCategoryName(categoryName);
            category.setCategorySection(categorySection);
            return categoryRepository.save(category);
        }
        // 이미 존재하는 카테고리인 경우, 예외 처리나 적절한 로직을 추가할 수 있습니다.
        return null; // 또는 예외를 던질 수 있습니다.
    }

    /*카테고리 이름으로 카테고리No찾기*/
    @Override
    @Transactional(readOnly = true)
    public int checkCategoryNo(String categoryName) {
        System.out.println("@#@#@#@# findCategoryNo"+categoryRepository.findCategoryNoByCategoryName(categoryName));
        return categoryRepository.findCategoryNoByCategoryName(categoryName);
    }

    /*상품 등록*/
    @Transactional
    public void addNewProduct(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();

        productEntity.setCategoryNo(Long.valueOf(productDTO.getCategoryNo()));
        productEntity.setProdName(productDTO.getProdName());
        productEntity.setProdDesc(productDTO.getProdDesc());
        productEntity.setProdPrice(productDTO.getProdPrice());
        productEntity.setDiscount(productDTO.getDiscount());
        productEntity.setTag(productDTO.getTag());
        productEntity.setProdOrigin(productDTO.getProdOrigin());
        productEntity.setBodyColor(productDTO.getBodyColor());
        productEntity.setProdDetailContent(productDTO.getProdDetailContent());
        productEntity.setAmount(productDTO.getAmount());

        productRepository.save(productEntity);
    }
}
