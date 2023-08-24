package com.spring.shopping.repository;


import com.spring.shopping.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // 전체 조회
    Page<Product> findAll(Pageable pageable);

    // 카테고리별 조회
    Page<Product> findByShopCategoryCategoryId(Long categoryId, Pageable pageable);

    // 상세 조회
    Product findByProductId(Long productId);


    // 검색 - 상품명으로 찾기
    Page<Product> findByProductNameContainingIgnoreCase(String keyword, Pageable pageable);

    // 카테고리 내 검색
    Page<Product> findByShopCategoryCategoryIdAndProductNameContainingIgnoreCase(Long categoryId, String keyword, Pageable pageable);

    Product findByProductName(String productName);


}
