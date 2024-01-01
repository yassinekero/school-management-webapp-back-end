package com.schoolmanagement.schoolmanagement.repository;

import com.schoolmanagement.schoolmanagement.model.Filière;
import com.schoolmanagement.schoolmanagement.model.Modules;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<Modules, Integer> {
}