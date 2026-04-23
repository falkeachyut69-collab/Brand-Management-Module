package com.example.groupmanagement.repository;

import com.example.groupmanagement.entity.Chain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChainRepository extends JpaRepository<Chain, Long> {

    List<Chain> findByIsActiveTrue();

    List<Chain> findByGroup_GroupIdAndIsActiveTrue(Long groupId);

    boolean existsByGstNumber(String gstNumber);
}