package com.kkulpa.pressarticleapi.repository;

import com.kkulpa.pressarticleapi.app.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
