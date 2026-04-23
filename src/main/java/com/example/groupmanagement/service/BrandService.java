package com.example.groupmanagement.service;

import com.example.groupmanagement.entity.Brand;
import com.example.groupmanagement.entity.Chain;
import com.example.groupmanagement.repository.BrandRepository;
import com.example.groupmanagement.repository.ChainRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    private final BrandRepository brandRepo;
    private final ChainRepository chainRepo;

    public BrandService(BrandRepository brandRepo, ChainRepository chainRepo) {
        this.brandRepo = brandRepo;
        this.chainRepo = chainRepo;
    }

    public List<Brand> getAll() {
        return brandRepo.findByIsActiveTrue();
    }

    public List<Brand> getByChain(Long chainId) {
        return brandRepo.findByChain_ChainIdAndIsActiveTrue(chainId);
    }

    public Brand addBrand(String name, Long chainId) {
        Chain chain = chainRepo.findById(chainId)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        Brand b = new Brand();
        b.setBrandName(name);
        b.setChain(chain);
        b.setActive(true);

        return brandRepo.save(b);
    }

    public Brand updateBrand(Long id, String name, Long chainId) {
        Brand b = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Chain chain = chainRepo.findById(chainId)
                .orElseThrow(() -> new RuntimeException("Chain not found"));

        b.setBrandName(name);
        b.setChain(chain);

        return brandRepo.save(b);
    }

    public void deleteBrand(Long id) {
        Brand b = brandRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        b.setActive(false);
        brandRepo.save(b);
    }
}