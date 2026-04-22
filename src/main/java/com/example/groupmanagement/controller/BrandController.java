package com.example.groupmanagement.controller;

import com.example.groupmanagement.entity.Brand;
import com.example.groupmanagement.service.BrandService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping
    public List<Brand> getAll() {
        return service.getAll();
    }

    @GetMapping("/chain/{id}")
    public List<Brand> byChain(@PathVariable Long id) {
        return service.getByChain(id);
    }

    @PostMapping
    public Brand add(@RequestBody Map<String, Object> data) {
        return service.addBrand(
                (String) data.get("brandName"),
                Long.valueOf(data.get("chainId").toString())
        );
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id, @RequestBody Map<String, Object> data) {
        return service.updateBrand(
                id,
                (String) data.get("brandName"),
                Long.valueOf(data.get("chainId").toString())
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteBrand(id);
    }
}