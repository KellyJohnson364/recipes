package com.recipes.repository;

import com.recipes.model.Vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Query("SELECT count(*) FROM Vote v where v.recipeId = :id")
    int countVotesByRecipeId(@Param("id") Integer id);

}
