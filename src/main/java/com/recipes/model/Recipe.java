package com.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String recipeUrl;
    @Transient
    private String userName;
    @Transient
    private int voteCount;
    private Integer userId;

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "posted_at")
    private Date postedAt = new Date();

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

    @OneToMany(mappedBy = "recipeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;

    public Recipe(){};

    public Recipe(Integer id, String title, String recipeUrl, int voteCount, Integer userId) {
        this.id = id;
        this.title = title;
        this.recipeUrl = recipeUrl;
        this.voteCount = voteCount;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public void setRecipeUrl(String recipeUrl) {
        this.recipeUrl = recipeUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getVoteCount() { return voteCount; }

    public void setVoteCount(int voteCount) { this.voteCount = voteCount; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) { this.userId = userId; }

    public Date getPostedAt() { return postedAt; }

    public void setPostedAt(Date postedAt) { this.postedAt = postedAt; }

    public Date getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }

    public List<Note> getNotes() { return notes; }

    public void setNotes(List<Note> notes) { this.notes = notes; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(id, recipe.id) && Objects.equals(title, recipe.title) && Objects.equals(recipeUrl, recipe.recipeUrl) && Objects.equals(userName, recipe.userName) && Objects.equals(userId, recipe.userId) && Objects.equals(postedAt, recipe.postedAt) && Objects.equals(updatedAt, recipe.updatedAt) && Objects.equals(notes, recipe.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, recipeUrl, userName, voteCount, userId, postedAt, updatedAt, notes);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", recipeUrl='" + recipeUrl + '\'' +
                ", userName='" + userName + '\'' +
                ", voteCount=" + voteCount +
                ", userId=" + userId +
                ", postedAt=" + postedAt +
                ", updatedAt=" + updatedAt +
                ", notes=" + notes +
                '}';
    }
}