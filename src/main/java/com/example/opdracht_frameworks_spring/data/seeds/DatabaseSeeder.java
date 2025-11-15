package com.example.opdracht_frameworks_spring.data.seeds;

import com.example.opdracht_frameworks_spring.dao.*;
import com.example.opdracht_frameworks_spring.data.entity.*;
import com.example.opdracht_frameworks_spring.dto.BlogPayload;
import com.example.opdracht_frameworks_spring.dto.VideoFilePayload;
import com.example.opdracht_frameworks_spring.dto.VlogPayload;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ArticleDAO articleDAO;
    private final BlogDAO blogDAO;
    private final VlogDAO vlogDAO;
    private final UserDAO userDAO;
    private final CommentDAO commentDAO;

    public DatabaseSeeder(ArticleDAO articleDAO, BlogDAO blogDAO, VlogDAO vlogDAO, UserDAO userDAO, CommentDAO commentDAO) {
        this.articleDAO = articleDAO;
        this.blogDAO = blogDAO;
        this.vlogDAO = vlogDAO;
        this.userDAO = userDAO;
        this.commentDAO = commentDAO;
    }

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println("Seeding database...");

        // Seed only if database is empty
        if (articleDAO.findAll().isEmpty()) {
            seedUsers();
            seedBlogs();
            seedVlogs();
            seedComments();
            System.out.println("Database seeded successfully!");
        } else {
            System.out.println("Database already contains data, skipping seed.");
        }

        // Print results
        printResults();
    }

    private void seedUsers() {
        User user1 = new User(
                "john_doe",
                "johndoe@mail.com",
                Arrays.asList("USER", "ADMIN")
        );

        User user2 = new User(
                "jane_smith",
                "janesmith@mail.com",
                Arrays.asList("USER")
        );

        userDAO.save(user1);
        userDAO.save(user2);
    }

    private void seedBlogs() {
        BlogPayload blogPayload1 = new BlogPayload(
                "Getting Started with Spring Boot",
                "Learn the basics of Spring Boot framework",
                "Spring Boot is an opinionated framework that makes it easy to create stand-alone applications...",
                "getting-started-spring-boot",
                Arrays.asList("java", "spring", "tutorial"),
                Arrays.asList(1)
        );

        BlogPayload blogPayload2 = new BlogPayload(
                "Understanding JPA Inheritance",
                "A deep dive into JPA inheritance strategies",
                "JPA provides several strategies for mapping inheritance hierarchies to database tables...",
                "understanding-jpa-inheritance",
                Arrays.asList("jpa", "hibernate", "database"),
                Arrays.asList(1, 2)
        );

        BlogPayload blogPayload3 = new BlogPayload(
                "MySQL Best Practices",
                "Optimize your MySQL database performance",
                "Learn about indexing, query optimization, and database design patterns...",
                "mysql-best-practices",
                Arrays.asList("mysql", "database", "performance"),
                Arrays.asList(2)
        );

        Blog blog1 = blogDAO.save(blogPayload1);
        Blog blog2 = blogDAO.save(blogPayload2);
        Blog blog3 = blogDAO.save(blogPayload3);

        // link blogs and users
        List<User> users = userDAO.findAll();
        blog1.addUser(users.get(0));
        blog2.addUser(users.get(1));
        blog3.addUser(users.get(0));
        blog3.addUser(users.get(1));
    }

    private void seedVlogs() {
        VlogPayload vlogPayload1 = new VlogPayload(
                "Spring Boot Tutorial",
                "An introduction to Spring Boot",
                "In this vlog, we will explore the basics of Spring Boot...",
                "spring-boot-tutorial",
                Arrays.asList("java", "spring", "vlog"),
                new VideoFilePayload("http://example.com/videos/spring-boot-tutorial.mp4"),
                Arrays.asList(1)
        );

        VlogPayload vlogPayload2 = new VlogPayload(
                "JPA Inheritance Explained",
                "Understanding JPA inheritance strategies",
                "This vlog covers the different inheritance strategies in JPA...",
                "jpa-inheritance-explained",
                Arrays.asList("jpa", "hibernate", "vlog"),
                new VideoFilePayload("http://example.com/videos/jpa-inheritance-explained.mp4"),
                Arrays.asList(1, 2)
        );

        VlogPayload vlogPayload3 = new VlogPayload(
                "Optimizing MySQL Performance",
                "Tips and tricks for MySQL optimization",
                "In this vlog, we discuss various techniques to optimize MySQL databases...",
                "optimizing-mysql-performance",
                Arrays.asList("mysql", "database", "vlog"),
                new VideoFilePayload("http://example.com/videos/optimizing-mysql-performance.mp4"),
                Arrays.asList(2)
        );

        Vlog vlog1 = vlogDAO.save(vlogPayload1);
        Vlog vlog2 = vlogDAO.save(vlogPayload2);
        Vlog vlog3 = vlogDAO.save(vlogPayload3);

        // link vlogs and users
        List<User> users = userDAO.findAll();
        vlog1.addUser(users.get(0));
        vlog2.addUser(users.get(1));
        vlog3.addUser(users.get(0));
        vlog3.addUser(users.get(1));
    }

    private void seedComments() {
        Comment comment1 = new Comment(
                "Great article on Spring Boot!",
                new Date()
        );
        Comment comment2 = new Comment(
                "Very informative vlog about JPA.",
                new Date()
        );

        List<Article> articles = articleDAO.findAll();
        List<User> users = userDAO.findAll();

        if (!articles.isEmpty() && !users.isEmpty()) {
            comment1.setArticle(articles.get(0));
            comment1.setUser(users.get(0));
            comment2.setArticle(articles.get(1));
            comment2.setUser(users.get(1));
        }

        commentDAO.save(comment1);
        commentDAO.save(comment2);
    }

    private void printResults() {
        System.out.println("\nDatabase Contents:");
        System.out.println("====================");
        articleDAO.findAll().forEach(article -> {
            System.out.println(" [" + article.getClass().getSimpleName() + "] " + article.getTitle());
            if (article instanceof Blog) {
                System.out.println("   Read time: " + ((Blog) article).getReadTime());
            }
            System.out.println("   Tags: " + article.getTags());
            System.out.println();
        });
    }
}