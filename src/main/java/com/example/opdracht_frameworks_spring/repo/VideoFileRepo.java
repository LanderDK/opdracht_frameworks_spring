package com.example.opdracht_frameworks_spring.repo;

import com.example.opdracht_frameworks_spring.data.entity.VideoFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoFileRepo extends JpaRepository<VideoFile, Integer> {
}
