package com.example.groupmanagement.controller;

import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.service.GroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@CrossOrigin("*")
public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    // ✅ CREATE
    @PostMapping
    public Group add(@RequestBody Group g) {
        return service.add(g);
    }

    // ✅ READ
    @GetMapping
    public List<Group> getAll() {
        return service.getAll();
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Group update(@PathVariable Long id, @RequestBody Group g) {
        return service.update(id, g);
    }

    // ✅ DELETE (SOFT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // ✅ TOGGLE ACTIVE
    @PatchMapping("/{id}")
    public Group toggle(@PathVariable Long id) {
        return service.toggle(id);
    }
}