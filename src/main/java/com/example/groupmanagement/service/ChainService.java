package com.example.groupmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.groupmanagement.dto.ChainRequest;
import com.example.groupmanagement.entity.Chain;
import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.repository.ChainRepository;
import com.example.groupmanagement.repository.GroupRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChainService {

    @Autowired
    private ChainRepository repo;

    @Autowired
    private GroupRepository groupRepo;

    // ✅ ADD
    public Chain add(ChainRequest req) {
        

        validate(req);

        if (repo.existsByGstNumber(req.getGstNumber())) {
            throw new RuntimeException("GST already exists");
        }

        Group group = getGroup(req.getGroupId());

        Chain c = new Chain();
        c.setCompanyName(req.getCompanyName());
        c.setGstNumber(req.getGstNumber());
        c.setGroup(group);
        c.setActive(true);
        c.setCreatedAt(LocalDateTime.now());

        return repo.save(c);
    }

    // ✅ GET ALL ACTIVE
    public List<Chain> getAll() {
        return repo.findByIsActiveTrue();
    }

    // ✅ FILTER BY GROUP
    public List<Chain> getByGroup(Long groupId) {
        return repo.findByGroupIdAndIsActiveTrue(groupId);
    }

    // ✅ UPDATE
    public Chain update(Long id, ChainRequest req) {

        validate(req);

        Chain c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        // prevent duplicate GST
        if (!c.getGstNumber().equals(req.getGstNumber()) &&
                repo.existsByGstNumber(req.getGstNumber())) {
            throw new RuntimeException("GST already exists");
        }

        Group group = getGroup(req.getGroupId());

        c.setCompanyName(req.getCompanyName());
        c.setGstNumber(req.getGstNumber());
        c.setGroup(group);
        c.setUpdatedAt(LocalDateTime.now());

        return repo.save(c);
    }

    // ✅ SOFT DELETE
    public void delete(Long id) {
        Chain c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        c.setActive(false);
        repo.save(c);
    }

    // ✅ TOGGLE ACTIVE
    public Chain toggle(Long id) {
        Chain c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        c.setActive(!c.isActive());
        return repo.save(c);
    }

    // =========================
    // 🔧 HELPER METHODS
    // =========================

    private void validate(ChainRequest req) {
        if (req.getCompanyName() == null || req.getCompanyName().trim().isEmpty()) {
            throw new RuntimeException("Company name is required");
        }

        if (req.getGstNumber() == null || req.getGstNumber().trim().isEmpty()) {
            throw new RuntimeException("GST number is required");
        }

        if (req.getGroupId() == null) {
            throw new RuntimeException("Group must be selected");
        }
    }

    private Group getGroup(Long groupId) {
        return groupRepo.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));
    }
}