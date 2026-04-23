package com.example.groupmanagement.service;

import org.springframework.stereotype.Service;

import com.example.groupmanagement.dto.ChainRequest;
import com.example.groupmanagement.entity.Chain;
import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.repository.ChainRepository;
import com.example.groupmanagement.repository.GroupRepository;

import java.util.List;

@Service
public class ChainService {

    private final ChainRepository repo;
    private final GroupRepository groupRepo;

    public ChainService(ChainRepository repo, GroupRepository groupRepo) {
        this.repo = repo;
        this.groupRepo = groupRepo;
    }

    public Chain add(ChainRequest req) {

        if (repo.existsByGstNumber(req.getGstNumber())) {
            throw new RuntimeException("GST already exists");
        }

        Group group = groupRepo.findById(req.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        Chain c = new Chain();
        c.setCompanyName(req.getCompanyName());
        c.setGstNumber(req.getGstNumber());
        c.setGroup(group);
        c.setActive(true);

        return repo.save(c);
    }

    public List<Chain> getAll() {
        return repo.findByIsActiveTrue();
    }

    public List<Chain> getByGroup(Long groupId) {
        return repo.findByGroup_GroupIdAndIsActiveTrue(groupId);
    }

    public Chain update(Long id, ChainRequest req) {

        Chain c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        Group group = groupRepo.findById(req.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        c.setCompanyName(req.getCompanyName());
        c.setGstNumber(req.getGstNumber());
        c.setGroup(group);

        return repo.save(c);
    }

    public void delete(Long id) {
        Chain c = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        c.setActive(false);
        repo.save(c);
    }
}