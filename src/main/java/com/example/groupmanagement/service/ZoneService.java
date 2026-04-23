package com.example.groupmanagement.service;

import com.example.groupmanagement.entity.Brand;
import com.example.groupmanagement.entity.Zone;
import com.example.groupmanagement.repository.BrandRepository;
import com.example.groupmanagement.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepo;
    private final BrandRepository brandRepo;

    public ZoneService(ZoneRepository zoneRepo, BrandRepository brandRepo) {
        this.zoneRepo = zoneRepo;
        this.brandRepo = brandRepo;
    }

    // ✅ ADD
    public Zone add(Zone z, Long brandId) {
        Brand brand = brandRepo.findById(brandId)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        z.setBrand(brand);
        return zoneRepo.save(z);
    }

    // ✅ GET ALL
    public List<Zone> getAll() {
        return zoneRepo.findAll();
    }

    // ✅ FILTER BY BRAND
    public List<Zone> getByBrand(Long brandId) {
        return zoneRepo.findByBrand_BrandId(brandId);
    }

    // ✅ DELETE
    public void delete(Long id) {
        zoneRepo.deleteById(id);
    }
}