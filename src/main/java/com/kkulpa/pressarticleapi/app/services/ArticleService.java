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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.kkulpa.pressarticleapi.app.domain.mappers.ArticleMapper.mapToArticle;
import static com.kkulpa.pressarticleapi.app.domain.mappers.ArticleMapper.mapToArticleContent;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final AuthorRepository authorRepository;

    public List<Article> getAllArticlesSortedByPublicationDate(){
        return articleRepository.findAllSortedByPublicationDate();
    }

    public Article getArticleById(Long articleId) throws ArticleNotFoundException {
        return articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new );
    }

    public List<Article> getArticleByKeyWord(String keyWord){
        String search = "%" + keyWord + "%";
        return articleRepository.findArticleByKeyword(search);
    }

    @Transactional
    public void deleteArticle(Long articleId) throws ArticleNotFoundException {
        if(!articleRepository.existsById(articleId))
            throw new ArticleNotFoundException();
        articleRepository.deleteById(articleId);
    }

    @Transactional
    public Article addArticle(ArticleDTO articleDTO)
                            throws  AuthorNotFoundException,
                                    InvalidAuthorDataException,
                                    IncompleteAuthorInformationException {

        AuthorDTO authorDTO = articleDTO.getAuthor();


        Author author = findOrAddAuthor(authorDTO);// TODO śmierdzi

        Article article = mapToArticle(articleDTO);



        article.setAuthor(author);
        article.setId(null);
        article.getArticleContent().setId(null);
        article.setTimestamp(LocalDate.now());

        return articleRepository.save(article);
    }

/*    private Optional<Author> findAuthor(AuthorDTO authorDTO){//TODO refactoring in progress
        if(authorDTO.getId()!=null)
            return authorRepository.findById(authorDTO.getId());



    }*/



    @Transactional
    public Article updateArticle(ArticleDTO articleDTO)
                            throws  ArticleNotFoundException,
                                    AuthorNotFoundException,
                                    InvalidAuthorDataException,
                                    IncompleteAuthorInformationException {

        validateArticleId(articleDTO.getId());

        Article article = mapToArticle(articleDTO);
        Author author   = findOrAddAuthor(articleDTO.getAuthor());
        ArticleContent articleContent = updatedArticleContent(articleDTO.getId() ,articleDTO.getArticleContent());

        article.setAuthor(author);
        article.setTimestamp(LocalDate.now());
        article.setArticleContent(articleContent);

        return articleRepository.save(article);
    }

    private void validateArticleId(Long articleId) throws ArticleNotFoundException {
        if(articleId == null)
            throw new ArticleNotFoundException();

        if(!articleRepository.existsById(articleId))
            throw new ArticleNotFoundException();
    }

    private ArticleContent updatedArticleContent(Long articleId, ArticleContentDTO articleContentDTO) throws ArticleNotFoundException {
        if ( articleContentDTO.getId() == null)
            return mapToArticleContent(articleContentDTO);

        Article article = articleRepository.findById(articleId).orElseThrow(ArticleNotFoundException::new);
        ArticleContent articleContent = article.getArticleContent();

        articleContent.setTitle(articleContentDTO.getTitle());
        articleContent.setContent(articleContentDTO.getContent());

        return articleContent;
    }

    private Author findOrAddAuthor(AuthorDTO authorDTO) throws AuthorNotFoundException, InvalidAuthorDataException, IncompleteAuthorInformationException {

        if(authorDTO.getId() == null){
            Author author = authorRepository.findAuthorByFirstNameAndLastName(authorDTO.getFirstName(), authorDTO.getLastName());// TODO zmienic repo albo refaktoring

            if(author != null)
                return author;

            if(authorDTO.getFirstName() == null || authorDTO.getLastName() == null)
                throw new IncompleteAuthorInformationException();

            return authorRepository.save(new Author(null, authorDTO.getFirstName(), authorDTO.getLastName()));
        }

        Author author = authorRepository.findById(authorDTO.getId()).orElseThrow(AuthorNotFoundException::new);
        validate(author, authorDTO);

        return author;
    }

    private void validate(Author author, AuthorDTO authorDTO) throws InvalidAuthorDataException {

        if(!author.getFirstName().equals(authorDTO.getFirstName()) && authorDTO.getFirstName()!=null)
            throw new InvalidAuthorDataException();

        if(!author.getLastName().equals(authorDTO.getLastName()) && authorDTO.getLastName() != null)
            throw new InvalidAuthorDataException();
    }

}
