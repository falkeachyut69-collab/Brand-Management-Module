package com.example.groupmanagement.controller;

import com.example.groupmanagement.entity.Brand;
import com.example.groupmanagement.service.BrandService;
import com.example.groupmanagement.dto.BrandRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/brands")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    // ✅ GET ALL BRANDS
    @GetMapping
    public List<Brand> getAll() {
        return service.getAll();
    }

    // ✅ GET BRANDS BY CHAIN
    @GetMapping("/chain/{chainId}")
    public List<Brand> getByChain(@PathVariable Long chainId) {
        return service.getByChain(chainId);
    }

    // ✅ CREATE BRAND
    @PostMapping
    public Brand create(@RequestBody BrandRequest request) {
        return service.addBrand(
                request.getBrandName(),
                request.getChainId()
        );
    }

    // ✅ UPDATE BRAND
    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id, @RequestBody BrandRequest request) {
        return service.updateBrand(
                id,
                request.getBrandName(),
                request.getChainId()
        );
    }

    // ✅ DELETE BRAND
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteBrand(id);
    }
}