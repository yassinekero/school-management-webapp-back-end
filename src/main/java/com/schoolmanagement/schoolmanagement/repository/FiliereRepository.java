package com.schoolmanagement.schoolmanagement.repository;

import com.schoolmanagement.schoolmanagement.model.Departement;
import com.schoolmanagement.schoolmanagement.model.Filière;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FiliereRepository extends JpaRepository<Filière, Integer> {
}
