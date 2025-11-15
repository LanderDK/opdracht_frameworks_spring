package com.example.opdracht_frameworks_spring.dao;

import com.example.opdracht_frameworks_spring.data.entity.User;
import com.example.opdracht_frameworks_spring.data.entity.VideoFile;
import com.example.opdracht_frameworks_spring.data.entity.Vlog;
import com.example.opdracht_frameworks_spring.dto.VlogPayload;
import com.example.opdracht_frameworks_spring.repo.UserRepo;
import com.example.opdracht_frameworks_spring.repo.VideoFileRepo;
import com.example.opdracht_frameworks_spring.repo.VlogRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VlogDAO {
    private final VlogRepo vlogRepo;
    private final VideoFileRepo videoFileRepo;
    private final UserRepo userRepo;

    public VlogDAO(VlogRepo vlogRepo, VideoFileRepo videoFileRepo, UserRepo userRepo) {
        this.vlogRepo = vlogRepo;
        this.videoFileRepo = videoFileRepo;
        this.userRepo = userRepo;
    }

    public List<Vlog> findAll() {
        return vlogRepo.findAll();
    }

    public Optional<Vlog> findById(Integer id) {
        return vlogRepo.findById(id);
    }

    @Transactional
    public Vlog save(VlogPayload payload) {
        // Create and save VideoFile first (explicit like in Node.js)
        VideoFile videoFile = new VideoFile();
        if (payload.getVideoFile() != null) {
            videoFile.setVideoFileUrl(payload.getVideoFile().getVideoFileUrl());
        }
        VideoFile savedVideoFile = videoFileRepo.save(videoFile);

        // Create Vlog with saved VideoFile
        Vlog vlog = new Vlog();
        vlog.setTitle(payload.getTitle());
        vlog.setExcerpt(payload.getExcerpt());
        vlog.setContent(payload.getContent());
        vlog.setSlug(payload.getSlug());
        vlog.setTags(payload.getTags());
        Date now = new Date();
        vlog.setPublishedAt(now);
        vlog.setUpdatedAt(now);
        vlog.setVideoFile(savedVideoFile);

        for (Integer userId : payload.getUserIds()) {
            Optional<User> user = userRepo.findById(userId);
            if (user.isEmpty()) {
                throw new IllegalArgumentException("User with ID " + userId + " not found");
            }
            vlog.addUser(user.get());
        }

        return vlogRepo.save(vlog);
    }

    public List<Vlog> saveAll(List<VlogPayload> payloads) {
        return payloads.stream().map(this::save).toList();
    }

    @Transactional
    public Optional<Vlog> update(Integer id, VlogPayload payload) {
        return vlogRepo.findById(id)
                .map(existingVlog -> {
                    existingVlog.setTitle(payload.getTitle());
                    existingVlog.setExcerpt(payload.getExcerpt());
                    existingVlog.setContent(payload.getContent());
                    existingVlog.setSlug(payload.getSlug());
                    existingVlog.setTags(payload.getTags());
                    existingVlog.setUpdatedAt(new Date());
                    // Update VideoFile if provided
                    if (payload.getVideoFile() != null) {
                        VideoFile videoFile = existingVlog.getVideoFile();
                        videoFile.setVideoFileUrl(payload.getVideoFile().getVideoFileUrl());
                        videoFileRepo.save(videoFile);
                    }
                    return vlogRepo.save(existingVlog);
                });
    }

    public void deleteById(Integer id) {
        vlogRepo.deleteById(id);
    }
}
