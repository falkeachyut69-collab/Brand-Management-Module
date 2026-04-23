package com.example.groupmanagement.service;

import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository repo;

    public GroupService(GroupRepository repo) {
        this.repo = repo;
    }

    // ✅ ADD
    public Group add(Group g) {

        if (g.getGroupName() == null || g.getGroupName().trim().isEmpty()) {
            throw new RuntimeException("Group name is required");
        }

        g.setActive(true);

        // ❌ REMOVED timestamps (handled automatically)

        return repo.save(g);
    }

    // ✅ GET ALL ACTIVE
    public List<Group> getAll() {
        return repo.findByIsActiveTrue();
    }

    // ✅ UPDATE
    public Group update(Long id, Group g) {

        Group existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        if (g.getGroupName() == null || g.getGroupName().trim().isEmpty()) {
            throw new RuntimeException("Group name is required");
        }

        existing.setGroupName(g.getGroupName());

        // ❌ REMOVED updatedAt (auto handled)

        return repo.save(existing);
    }

    // ✅ SOFT DELETE
    public void delete(Long id) {
        Group g = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        g.setActive(false);
        repo.save(g);
    }

    // ✅ TOGGLE ACTIVE
    public Group toggle(Long id) {
        Group g = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        g.setActive(!g.isActive());
        return repo.save(g);
    }
}