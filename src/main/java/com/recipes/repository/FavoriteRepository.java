package com.recipes.repository;

import com.recipes.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface FavoriteRepository extends JpaRepository<Favorite, Boolean> {
    boolean isFavoriteByRecipeId(@Param("id") Integer id);
}
