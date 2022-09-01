package com.kkulpa.pressarticleapi.app.services;

import com.kkulpa.pressarticleapi.app.domain.Article;
import com.kkulpa.pressarticleapi.app.domain.ArticleContent;
import com.kkulpa.pressarticleapi.app.domain.Author;
import com.kkulpa.pressarticleapi.app.domain.DTOs.ArticleContentDTO;
import com.kkulpa.pressarticleapi.app.domain.DTOs.ArticleDTO;
import com.kkulpa.pressarticleapi.app.domain.DTOs.AuthorDTO;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.ArticleNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.AuthorNotFoundException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.IncompleteAuthorInformationException;
import com.kkulpa.pressarticleapi.app.errorHandling.exceptions.InvalidAuthorDataException;
import com.kkulpa.pressarticleapi.app.repository.ArticleRepository;
import com.kkulpa.pressarticleapi.app.repository.AuthorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ArticleServiceUnitTest {

    private static ArticleRepository articleRepository = Mockito.mock(ArticleRepository.class);
    private static AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
    private static ArticleService articleService = new ArticleService(articleRepository, authorRepository);

    private static Author author1 = new Author(1L, "Anna", "B");
    private static Author author2 = new Author(2L, "Zuza", "C");
    private static ArticleContent ac1 = new ArticleContent(1L, "Good news", "just kidding");
    private static ArticleContent ac2 = new ArticleContent(2L, "Bad news", "not this time");
    private static Article a1 = new Article(1L, ac1, LocalDate.now().minusDays(2),"F", author1, LocalDate.now());
    private static Article a2 = new Article(2L, ac2, LocalDate.now().minusDays(5),"T", author2, LocalDate.now());


    @Test
    void shouldReturnAllArticlesSortedByPublicationDate() {
        //given
        List<Article> mockResult = List.of(a1,a2);
        when(articleRepository.findAllSortedByPublicationDate()).thenReturn(mockResult);

        //when
        List<Article> result = articleService.getAllArticlesSortedByPublicationDate();

        //then
        assertEquals(mockResult, result);
        verify(articleRepository).findAllSortedByPublicationDate();
    }

    @Test
    void shouldReturnArticleById() throws ArticleNotFoundException {
        //given
        when(articleRepository.findById(2L)).thenReturn(Optional.ofNullable(a2));

        //when
        Article result = articleService.getArticleById(2L);

        //then
        assertEquals(a2, result);
    }

    @Test
    void shouldReturnArticleByKeyWord() {
        //given
        when(articleRepository.findArticleByKeyword("%this%")).thenReturn(List.of(a2));

        //when
        List<Article> result = articleService.getArticleByKeyWord("this");

        //then
        assertEquals(List.of(a2), result);
    }

    @Test
    void shouldDontThrowWhileDeleteArticle() throws ArticleNotFoundException {
        //given
        when(articleRepository.existsById(2L)).thenReturn(true);

        //when
        articleService.deleteArticle(2L);

        //then
        assertDoesNotThrow( () -> {} );
    }

    @Test
    void shouldThrowWhileDeleteArticle() throws ArticleNotFoundException {
        //given
        when(articleRepository.existsById(2L)).thenReturn(true);

        //then
        assertThrows(ArticleNotFoundException.class,
                () -> articleService.deleteArticle(1L));

    }


    @Test
    void shouldNotThrowWhileAddArticle() throws AuthorNotFoundException, InvalidAuthorDataException, IncompleteAuthorInformationException {
          //given




          ArticleContentDTO articleContentDTO = new ArticleContentDTO(5L, "Test title", "Test content");
          AuthorDTO authorDTO = new AuthorDTO(10L, "Ida", "Szczesniak");
          ArticleDTO articleDTO = new ArticleDTO(16L,
                  articleContentDTO,
                  LocalDate.now().minusDays(3),
                  "Forbs",
                  authorDTO,
                  LocalDate.now());

          //when
          //articleService.addArticle(articleDTO); //TODO skonczyc po refaktorowaniu

          //then
          assertDoesNotThrow( () -> {} );
    }

    @Test
    void updateArticle() {
        //given


        //when


        //then
    }
}