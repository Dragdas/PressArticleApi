package com.kkulpa.pressarticleapi.app.controllers;

import com.kkulpa.pressarticleapi.app.domain.Article;
import com.kkulpa.pressarticleapi.app.domain.DTOs.ArticleDTO;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.ArticleNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.AuthorNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.IncompleteAuthorInformationException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.InvalidAuthorDataException;
import com.kkulpa.pressarticleapi.app.services.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticlesSorted(){
        return ResponseEntity.ok(articleService.getAllArticlesSortedByPublicationDate());
    }

    @GetMapping(value = "{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long articleId) throws ArticleNotFoundException {
        return ResponseEntity.ok(articleService.getArticleById(articleId));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Article>> getArticleByKeyWord(@RequestParam String keyWord){
        return ResponseEntity.ok(articleService.getArticleByKeyWord(keyWord));
    }

    @DeleteMapping(value = "{articleId}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long articleId) throws ArticleNotFoundException {
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> addArticle(@RequestBody ArticleDTO articleDTO)
                                throws  InvalidAuthorDataException,
                                        IncompleteAuthorInformationException {
        Article article = articleService.addArticle(articleDTO);
        return ResponseEntity.ok(article);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Article> updateArticle(@RequestBody ArticleDTO articleDTO)
                                throws ArticleNotFoundException,
                                        AuthorNotFoundException,
                                        InvalidAuthorDataException,
                                        IncompleteAuthorInformationException {

        Article article = articleService.updateArticle(articleDTO);
        return ResponseEntity.ok(article);
    }

}
