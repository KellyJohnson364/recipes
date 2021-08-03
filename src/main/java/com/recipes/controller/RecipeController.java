package com.recipes.controller;

import com.recipes.model.Favorite;
import com.recipes.model.Recipe;
import com.recipes.model.User;
import com.recipes.repository.FavoriteRepository;
import com.recipes.repository.RecipeRepository;
import com.recipes.repository.UserRepository;
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
    FavoriteRepository favoriteRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/api/recipes")
    public List<Recipe> getAllRecipes() {
        List<Recipe> recipeList = repository.findAll();
        for (Recipe p : recipeList) {
            p.setFavorite(favoriteRepository.isFavoriteByRecipeId(p.getId()));
        }
        return recipeList;
    }
    @GetMapping("/api/recipes/{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        Recipe returnRecipe = repository.getOne(id);
        returnRecipe.setFavorite(favoriteRepository.isFavoriteByRecipeId(returnRecipe.getId()));
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
    @PutMapping("/api/recipes/favorite")
    public String addFavorite(@RequestBody Favorite favorite, HttpServletRequest request) {
        String returnValue = "";
        if(request.getSession(false) != null) {
            Recipe returnRecipe = null;
            User sessionUser = (User) request.getSession().getAttribute("SESSION_USER");
            favorite.setUserId(sessionUser.getId());
            favoriteRepository.save(favorite);
            returnRecipe = repository.getOne(favorite.getRecipeId());
            returnRecipe.setFavorite(favoriteRepository.isFavoriteByRecipeId(favorite.getRecipeId()));
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
