package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Element;
import java.util.HashSet;
import java.util.Set;

@Table(name = "Enseignant", schema = "school_management")
@Entity
@Getter
@Setter
public class Enseignant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_enseignant;
    private String nomEnseignant;

    private String prenomEnseignant;






}
