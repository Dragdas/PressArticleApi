package com.kkulpa.pressarticleapi.repository;

import com.kkulpa.pressarticleapi.app.domain.ArticleContent;
import org.springframework.data.repository.CrudRepository;

public interface ArticleContentRepository extends CrudRepository<ArticleContent, Long> {
}
