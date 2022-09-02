package com.kkulpa.pressarticleapi.app.repository;

import com.kkulpa.pressarticleapi.app.domain.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Query(value =  "SELECT a FROM Article a " +
                    "LEFT JOIN FETCH a.articleContent " +
                    "LEFT JOIN FETCH a.author " +
                    "ORDER BY a.publicationDate DESC ")
    List<Article> findAllSortedByPublicationDate();
    @Query(value =  "SELECT a FROM Article a " +
                    "LEFT JOIN FETCH a.author " +
                    "LEFT JOIN FETCH a.articleContent AS content " +
                    "WHERE LOWER(content.title) LIKE LOWER(:keyWord)" +
                    "OR LOWER(content.content)  LIKE LOWER(:keyWord)")
    List<Article> findArticleByKeyword(@Param("keyWord") String keyWord);

}
