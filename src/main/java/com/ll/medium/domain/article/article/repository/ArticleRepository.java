package com.ll.medium.domain.article.article.repository;

import com.ll.medium.domain.article.article.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findByAuthorId(Long id);
    List<Article> findByPublishTrue();
    Page<Article> findAll(Pageable pageable);
}
