package com.ll.medium.domain.article.article.service;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.domain.article.article.repository.ArticleRepository;
import com.ll.medium.domain.member.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional
    public Article write(Member author, String title, String body,boolean publish) {
        Article article = Article.builder()
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .author(author)
                .title(title)
                .body(body)
                .publish(publish)
                .build();

        articleRepository.save(article);

        return article;
    }

    public Page<Article> getList(int page) {
        Pageable pageable = PageRequest.of(page,30);
        return articleRepository.findAll(pageable);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public void delete(Article article) {
        articleRepository.delete(article);
    }
    @Transactional
    public void modify(Article article, String title, String body, boolean publish) {
        article.setTitle(title);
        article.setBody(body);
        article.setPublish(publish);
    }

    public List<Article> findByAuthorId(Long id) {
        List<Article> articles = articleRepository.findByAuthorId(id);
        return articles;
    }

    public List<Article> findByPublishTrue() {
        List<Article> byPublishTrue = articleRepository.findByPublishTrue();
        return byPublishTrue;
    }
}
