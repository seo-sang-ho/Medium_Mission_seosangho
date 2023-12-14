package com.ll.medium.domain.article.article.controller;

import com.ll.medium.domain.article.article.entity.Article;
import com.ll.medium.domain.article.article.service.ArticleService;
import com.ll.medium.domain.member.member.entity.Member;
import com.ll.medium.global.loginMember.LoginMember;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
public class ArticleController {
    private final ArticleService articleService;
    private final LoginMember loginMember;

    @GetMapping("/post/myList")
    public String myList(Model model){
        Member member = loginMember.getMember();

        List<Article> articles = articleService.findByAuthorId(member.getId());

        model.addAttribute("articles",articles);

        return "article/article/myList";
    }

    @GetMapping("/post/list")
    public String articleList(Model model){
        List<Article> articles = articleService.findByPublishTrue();

        model.addAttribute("articles",articles);

        return "article/article/list";
    }
    @GetMapping("/")
    public String recentList(Model model,@RequestParam(value="page", defaultValue="0") int page){
        Page<Article> paging = articleService.getList(page);

        model.addAttribute("articles",paging);

        return "article/article/recentList";
    }

    @GetMapping("/post/write")
    public String getArticle(){
        return "article/article/write";
    }

    @Data
    public static class ArticleForm{
        @NotBlank
        private String title;
        @NotBlank
        private String body;
        private boolean a;
    }

    @PostMapping("/post/write")
    public String postArticle(@Valid ArticleForm articleForm){
        articleService.write(loginMember.getMember(),articleForm.title,articleForm.body,articleForm.a);
        return "redirect:/post/list";
    }

    @GetMapping("/post/{id}")
    public String detailArticle(Model model, @PathVariable Long id){
        Article article = articleService.findById(id).get();

        model.addAttribute("article",article);

        return "article/article/detail";
    }

    @GetMapping("/post/modify/{id}")
    public String getModify(Model model,@PathVariable Long id){
        Article article = articleService.findById(id).get();

        model.addAttribute("article", article);

        return "article/article/modify";
    }
    @Data
    public static class ModifyForm{
        @NotBlank
        private String title;
        @NotBlank
        private String body;
        private boolean a;
    }
    @PostMapping("/post/modify/{id}")
    public String modify(@PathVariable Long id, @Valid ModifyForm modifyForm){
        Article article = articleService.findById(id).get();

        articleService.modify(article,modifyForm.title,modifyForm.body,modifyForm.a);

        return "redirect:/post/list";
    }

    @PostMapping("/post/delete/{id}")
    public String deleteArticle(@PathVariable Long id){
        Article article = articleService.findById(id).get();

        articleService.delete(article);

        return "redirect:/post/list";
    }
}
