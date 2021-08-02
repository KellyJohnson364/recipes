package com.recipes.repository;

import com.recipes.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Recipe, Integer> {
    List<Recipe> findAllRecipesByUserId(Integer id) throws Exception;

}
