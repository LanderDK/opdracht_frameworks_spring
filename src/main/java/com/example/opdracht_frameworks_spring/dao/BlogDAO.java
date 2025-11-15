package com.example.opdracht_frameworks_spring.dao;

import com.example.opdracht_frameworks_spring.data.entity.Blog;
import com.example.opdracht_frameworks_spring.data.entity.User;
import com.example.opdracht_frameworks_spring.dto.BlogPayload;
import com.example.opdracht_frameworks_spring.repo.BlogRepo;
import com.example.opdracht_frameworks_spring.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BlogDAO {
    private final BlogRepo blogRepo;
    private final UserRepo userRepo;

    public BlogDAO(BlogRepo repo, UserRepo userRepo) {
        this.blogRepo = repo;
        this.userRepo = userRepo;
    }

    public List<Blog> findAll() {
        return blogRepo.findAll();
    }

    public Optional<Blog> findById(Integer id) {
        return blogRepo.findById(id);
    }

    public Blog save(BlogPayload payload) {
        Blog blog = new Blog();
        blog.setTitle(payload.getTitle());
        blog.setExcerpt(payload.getExcerpt());
        blog.setContent(payload.getContent());
        blog.setSlug(payload.getSlug());
        blog.setTags(payload.getTags());
        Date now = new Date();
        blog.setPublishedAt(now);
        blog.setUpdatedAt(now);
        blog.setReadTime(blog.calculateReadTime());

        for (Integer userId : payload.getUserIds()) {
            Optional<User> user = userRepo.findById(userId);
            if (user.isEmpty()) {
                throw new IllegalArgumentException("User with ID " + userId + " not found");
            }
            blog.addUser(user.get());
        }

        return blogRepo.save(blog);
    }

    public List<Blog> saveAll(List<BlogPayload> payloads) {
        return payloads.stream().map(this::save).toList();
    }

    public Optional<Blog> update(Integer id, BlogPayload payload) {
        return blogRepo.findById(id)
                .map(existingBlog -> {
                    existingBlog.setTitle(payload.getTitle());
                    existingBlog.setExcerpt(payload.getExcerpt());
                    existingBlog.setContent(payload.getContent());
                    existingBlog.setSlug(payload.getSlug());
                    existingBlog.setTags(payload.getTags());
                    existingBlog.setUpdatedAt(new Date());
                    existingBlog.setReadTime(existingBlog.calculateReadTime());
                    return blogRepo.save(existingBlog);
                });
    }

    public void deleteById(Integer id) {
        blogRepo.deleteById(id);
    }
}
