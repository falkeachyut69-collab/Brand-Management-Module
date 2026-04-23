package com.example.groupmanagement.repository;

import com.example.groupmanagement.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {

    List<Brand> findByIsActiveTrue();

    List<Brand> findByChain_ChainIdAndIsActiveTrue(Long chainId);
}