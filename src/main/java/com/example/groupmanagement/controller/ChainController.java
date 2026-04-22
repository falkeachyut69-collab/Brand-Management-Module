package com.example.groupmanagement.controller;
import com.example.groupmanagement.dto.ChainRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.groupmanagement.entity.Chain;
import com.example.groupmanagement.service.ChainService;

import java.util.List;

@RestController
@RequestMapping("/chains")
@CrossOrigin("*")
public class ChainController {

    @Autowired
    private ChainService service;

   @PostMapping
public Chain add(@RequestBody ChainRequest req) {
    return service.add(req);
}

    @GetMapping
    public List<Chain> getAll() {
        return service.getAll();
    }

    @GetMapping("/group/{groupId}")
    public List<Chain> getByGroup(@PathVariable Long groupId) {
        return service.getByGroup(groupId);
    }

   @PutMapping("/{id}")
public Chain update(@PathVariable Long id, @RequestBody ChainRequest req) {
    return service.update(id, req);
}

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    public Chain toggle(@PathVariable Long id) {
        return service.toggle(id);
    }

 
}