package com.example.groupmanagement.controller;

import com.example.groupmanagement.dto.ChainRequest;
import com.example.groupmanagement.entity.Chain;
import com.example.groupmanagement.service.ChainService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/chains")
public class ChainController {

    private final ChainService service;

    public ChainController(ChainService service) {
        this.service = service;
    }

    // ✅ GET ALL CHAINS
    @GetMapping
    public List<Chain> getAll() {
        return service.getAll();
    }

    // ✅ GET CHAINS BY GROUP
    @GetMapping("/group/{groupId}")
    public List<Chain> getByGroup(@PathVariable Long groupId) {
        return service.getByGroup(groupId);
    }

    // ✅ CREATE
    @PostMapping
    public Chain create(@RequestBody ChainRequest req) {
        return service.add(req);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Chain update(@PathVariable Long id, @RequestBody ChainRequest req) {
        return service.update(id, req);
    }

    // ✅ DELETE (SOFT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}