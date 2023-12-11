package com.ll.medium.domain.article.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {
    @GetMapping("/article/write")
    public String writeArticle(){
        return "domain/article/article/write";
    }
}
