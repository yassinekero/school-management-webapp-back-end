package com.schoolmanagement.schoolmanagement.repository;

import com.schoolmanagement.schoolmanagement.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Integer> {
}
