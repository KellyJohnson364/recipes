package com.recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "comment")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String noteText;
    private Integer userId;
    private Integer recipeId;

    public Note(Integer id, String noteText, Integer userId, Integer recipeId) {
        this.id = id;
        this.noteText = noteText;
        this.userId = userId;
        this.recipeId = recipeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note notes = (Note) o;
        return Objects.equals(id, notes.id) && Objects.equals(noteText, notes.noteText) && Objects.equals(userId, notes.userId) && Objects.equals(recipeId, notes.recipeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, noteText, userId, recipeId);
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", noteText='" + noteText + '\'' +
                ", userId=" + userId +
                ", recipeId=" + recipeId +
                '}';
    }
}
