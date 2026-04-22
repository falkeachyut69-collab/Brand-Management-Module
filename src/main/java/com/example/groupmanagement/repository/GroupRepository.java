package com.example.groupmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.groupmanagement.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {
}