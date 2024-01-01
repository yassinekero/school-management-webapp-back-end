package com.schoolmanagement.schoolmanagement.repository;

import com.schoolmanagement.schoolmanagement.model.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant,Integer> {
}
