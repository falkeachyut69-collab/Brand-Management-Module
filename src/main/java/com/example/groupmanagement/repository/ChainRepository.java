package com.example.groupmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.groupmanagement.entity.Chain;

import java.util.List;

public interface ChainRepository extends JpaRepository<Chain, Long> {

    // ✅ Already correct
    boolean existsByGstNumber(String gstNumber);

    // ✅ Get all ACTIVE chains
    List<Chain> findByIsActiveTrue();

    // ✅ Filter by group + only ACTIVE
    List<Chain> findByGroupIdAndIsActiveTrue(Long groupId);
}