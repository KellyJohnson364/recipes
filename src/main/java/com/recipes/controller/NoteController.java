package com.recipes.controller;

import com.recipes.model.Note;
import com.recipes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {
    @Autowired
    NoteRepository repository;
    @GetMapping("/api/notes")
    public List<Note> getAllNotes() {
        return repository.findAll();
    }
    @GetMapping("/api/notes/{id}")
    public Note getNote(@PathVariable int id) {
        return repository.getOne(id);
    }
    @PostMapping("/api/notes")
    @ResponseStatus(HttpStatus.CREATED)
    public Note createNote(@RequestBody Note note) {
        return repository.save(note);
    }
    @PutMapping("/api/update/notes")
    public Note updateNote(@RequestBody Note note) {
        return repository.save(note);
    }
    @DeleteMapping("/api/notes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteNote(@PathVariable int id) {
        repository.deleteById(id);
    }
}
