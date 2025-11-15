package com.example.opdracht_frameworks_spring.controller;

import com.example.opdracht_frameworks_spring.dao.BlogDAO;
import com.example.opdracht_frameworks_spring.data.entity.Blog;
import com.example.opdracht_frameworks_spring.dto.BlogPayload;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
public class BlogController {
    private final BlogDAO dao;

    public BlogController(BlogDAO blogDAO) {
        this.dao = blogDAO;
    }

    @GetMapping
    public List<Blog> getBlogs() {
        return dao.findAll();
    }

    @GetMapping("{id}")
    public Blog getBlogById(@PathVariable("id") Integer id) {
        return dao.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found"));
    }

    @PostMapping
    public ResponseEntity<Void> createBlog(@RequestBody BlogPayload payload) {
        Blog blog = dao.save(payload);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(blog.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateBlog(@PathVariable("id") Integer id, @RequestBody BlogPayload payload) {
        return dao.update(id, payload)
                .map(updatedBlog -> new ResponseEntity<>("", HttpStatus.NO_CONTENT))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlog(@PathVariable("id") Integer id) {
        if (dao.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Blog not found");
        }
        dao.deleteById(id);
    }
}
