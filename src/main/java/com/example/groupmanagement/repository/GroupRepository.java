package com.example.groupmanagement.repository;

import com.example.groupmanagement.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    // ✅ Get all ACTIVE groups
    List<Group> findByIsActiveTrue();



    
}