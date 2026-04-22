package com.example.groupmanagement.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.groupmanagement.entity.Group;
import com.example.groupmanagement.repository.GroupRepository;

@RestController
@RequestMapping("/groups")
@CrossOrigin("*")
public class GroupController {

    @Autowired
    private GroupRepository repo;

    @PostMapping
    public Group add(@RequestBody Group g) {
        return repo.save(g);
    }

    @GetMapping
    public List<Group> getAll() {
        return repo.findAll();
    }
}