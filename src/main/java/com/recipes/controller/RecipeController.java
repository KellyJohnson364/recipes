package com.recipes.controller;

import com.recipes.model.Recipe;
import com.recipes.model.User;
import com.recipes.model.Vote;
import com.recipes.repository.RecipeRepository;
import com.recipes.repository.UserRepository;
import com.recipes.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class RecipeController {

    @Autowired
    RecipeRepository repository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/api/recipes")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = repository.findAll();
        for (Recipe p : recipeList) {
            p.setVoteCount(voteRepository.countVotesByRecipeId(p.getId()));
        }
        return recipeList;
    }
    @GetMapping("/api/recipes/{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        Recipe returnRecipe = repository.getOne(id);
        returnRecipe.setVoteCount(voteRepository.countVotesByRecipeId(returnRecipe.getId()));
        return returnRecipe;
    }
    @PostMapping("/api/recipes")
    @ResponseStatus(HttpStatus.CREATED)
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        repository.save(recipe);
        return recipe;
    }
    @PutMapping("/api/recipes/{id}")
    public Recipe updateRecipe(@PathVariable int id, @RequestBody Recipe recipe) {
        Recipe tempRecipe = repository.getOne(id);
        tempRecipe.setTitle(recipe.getTitle());
        return repository.save(tempRecipe);
    }
    @PutMapping("/api/recipes/upvote")
    public String addVote(@RequestBody Vote vote, HttpServletRequest request) {
        String returnValue = "";
        if(request.getSession(false) != null) {
            Recipe returnRecipe = null;
            User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
            vote.setUserId(sessionUser.getId());
            voteRepository.save(vote);
            returnRecipe = repository.getOne(vote.getRecipeId());
            returnRecipe.setVoteCount(voteRepository.countVotesByRecipeId(vote.getRecipeId()));
            returnValue = "";
        } else {
            returnValue = "login";
        }
        return returnValue;
    }
    @DeleteMapping("/api/recipes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipe(@PathVariable int id) {
        repository.deleteById(id);
    }
}
