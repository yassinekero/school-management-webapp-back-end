package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter


public class Departement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dept;
    @Column(name = "Departement_name", nullable = false , unique = true)
    private String dept_name;
    @ManyToOne
    @JoinColumn(name = "enseignant_id ",referencedColumnName = "id_enseignant")
    private Enseignant enseignant;
    public Departement() {
    }
    public Departement(int id_dept, String dept_name, String dept_respo, Enseignant enseignant) {
        this.id_dept = id_dept;
        this.dept_name = dept_name;
        this.enseignant = enseignant;
    }


}
