package com.example.groupmanagement.controller;
import com.example.groupmanagement.dto.ZoneRequest;
import com.example.groupmanagement.entity.Zone;
import com.example.groupmanagement.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
@CrossOrigin(origins = "http://localhost:3000")
public class ZoneController {

    private final ZoneService service;

    public ZoneController(ZoneService service) {
        this.service = service;
    }

    // ✅ ADD ZONE
    @PostMapping
    public Zone add(@RequestBody ZoneRequest req) {
        Zone z = new Zone();
        z.setZoneName(req.getZoneName());

        return service.add(z, req.getBrandId());
    }

    // ✅ GET ALL
    @GetMapping
    public List<Zone> getAll() {
        return service.getAll();
    }

    // ✅ FILTER BY BRAND
    @GetMapping("/brand/{brandId}")
    public List<Zone> getByBrand(@PathVariable Long brandId) {
        return service.getByBrand(brandId);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}