package com.kkulpa.pressarticleapi.app.controllers;

import com.kkulpa.pressarticleapi.app.domain.Article;
import com.kkulpa.pressarticleapi.app.domain.DTOs.ArticleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    @GetMapping
    public ResponseEntity<List<Article>> getAllArticlesSorted(){
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping(value = "{articleId}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long articleId){
        return ResponseEntity.ok(null);
    }

    @GetMapping("/search")
    public ResponseEntity<Article> getArticleByKeyWord(@RequestParam String keyWord){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping(value = "{articleId}")
    public ResponseEntity<Article> deleteArticleById(@PathVariable Long articleId){
        return ResponseEntity.ok(null);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addArticle(@RequestBody ArticleDTO articleDTO){
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateArticle(@RequestBody ArticleDTO articleDTO){
        return ResponseEntity.ok().build();
    }

}
