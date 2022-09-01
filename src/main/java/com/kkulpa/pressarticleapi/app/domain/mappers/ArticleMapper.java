package com.kkulpa.pressarticleapi.app.domain.mappers;

import com.kkulpa.pressarticleapi.app.domain.Article;
import com.kkulpa.pressarticleapi.app.domain.ArticleContent;
import com.kkulpa.pressarticleapi.app.domain.Author;
import com.kkulpa.pressarticleapi.app.domain.DTOs.ArticleContentDTO;
import com.kkulpa.pressarticleapi.app.domain.DTOs.ArticleDTO;
import com.kkulpa.pressarticleapi.app.domain.DTOs.AuthorDTO;

public class ArticleMapper {

    public static Article mapToArticle(ArticleDTO articleDTO){

        return new Article(
                articleDTO.getId(),
                mapToArticleContent(articleDTO.getArticleContent()),
                articleDTO.getPublicationDate(),
                articleDTO.getPublisher(),
                mapToAuthor(articleDTO.getAuthor()),
                articleDTO.getTimestamp()
        );
    }

    public static ArticleContent mapToArticleContent(ArticleContentDTO articleContentDTO){
        return new ArticleContent(  null,
                                        articleContentDTO.getTitle(),
                                        articleContentDTO.getContent());
    }

    public static Author mapToAuthor(AuthorDTO authorDTO){
        return new Author(  authorDTO.getId(),
                            authorDTO.getFirstName(),
                            authorDTO.getLastName());
    }

}
