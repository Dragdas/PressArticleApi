package com.kkulpa.pressarticleapi.app.controllers;

import com.kkulpa.pressarticleapi.app.domain.Article;
import com.kkulpa.pressarticleapi.app.domain.ArticleContent;
import com.kkulpa.pressarticleapi.app.domain.Author;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.ArticleNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.IncompleteAuthorInformationException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.InvalidAuthorDataException;
import com.kkulpa.pressarticleapi.app.services.ArticleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ArticleControllerUnitTest {
    private static final ArticleService articleService = Mockito.mock(ArticleService.class);
    private static final ArticleController articleController = new ArticleController(articleService);

    private static  final Author author1 = new Author(1L, "Anna", "B");
    private static  final Author author2 = new Author(2L, "Zuza", "C");
    private static  final ArticleContent ac1 = new ArticleContent(1L,
                                                                "Good news",
                                                              "just kidding");
    private static  final ArticleContent ac2 = new ArticleContent(2L,
                                                                "Bad news",
                                                             "not this time");
    private static  final Article a1 = new Article(1L,
                                                    ac1,
                                                    LocalDate.now().minusDays(2),
                                            "F",
                                                    author1,
                                                    LocalDate.now());
    private static final  Article a2 = new Article(2L,
                                                    ac2,
                                                    LocalDate.now().minusDays(5),
                                            "T",
                                                    author2,
                                                    LocalDate.now());


    @Test
    void shouldReturnAllArticlesSorted() {
        //given
        when(articleService.getAllArticlesSortedByPublicationDate()).thenReturn(List.of(a1, a2));
        ResponseEntity<List<Article>> expectedResult = ResponseEntity.ok(List.of(a1,a2));

        //when
        ResponseEntity<List<Article>> result = articleController.getAllArticlesSorted();

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldReturnArticleById() throws ArticleNotFoundException {
        //given
        when( articleService.getArticleById(2L) ).thenReturn(a2);
        ResponseEntity<Article> expectedResult = ResponseEntity.ok(a2);
        //when
        ResponseEntity<Article> result = articleController.getArticleById(2L);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void getArticleByKeyWord() {
        //given
        when( articleService.getArticleByKeyWord("this") ).thenReturn(List.of(a2));
        ResponseEntity<List<Article>> expectedResult = ResponseEntity.ok(List.of(a2));

        //when
        ResponseEntity<List<Article>> result = articleController.getArticleByKeyWord("this");

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldDeleteArticleById() throws ArticleNotFoundException {
        //given
        ResponseEntity<Void> expectedResult = ResponseEntity.ok().build();

        //when
        ResponseEntity<Void> result = articleController.deleteArticleById(2L);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void shouldAddArticle() throws InvalidAuthorDataException, IncompleteAuthorInformationException {
        //given
        when(articleService.addArticle(null)).thenReturn(a1);
        ResponseEntity<Article> expectedResult = ResponseEntity.ok(a1);

        //when
        ResponseEntity<Article> result = articleController.addArticle(null);

        //then
        assertEquals(expectedResult, result);
    }

    @Test
    void updateArticle() throws InvalidAuthorDataException,
                        ArticleNotFoundException,
                        IncompleteAuthorInformationException {
        //given
        when(articleService.updateArticle(null)).thenReturn(a1);
        ResponseEntity<Article> expectedResult = ResponseEntity.ok(a1);

        //when
        ResponseEntity<Article> result = articleController.updateArticle(null);

        //then
        assertEquals(expectedResult, result);
    }
}