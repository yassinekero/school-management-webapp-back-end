package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Table(name = "ElementModule", schema = "school_management", uniqueConstraints = {
        @UniqueConstraint(
                name = "code_unique",
                columnNames = "cem"
        )
})
@Getter
@Setter
@Entity
public class ElementModule {
    @ManyToOne
    @JoinColumn(name = "module_id", referencedColumnName = "id_module")
    private Modules modules;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_element_module;
    @Column(name = "cem", nullable = false, unique = true)
    private String code_element_module;
    @Column(name = "emn", nullable = false, unique = true)
    private String element_module_name;
    @ManyToOne
    @JoinColumn(name = "enseignant_id ",referencedColumnName = "id_enseignant")
    private Enseignant enseignant;

    @Override
    public String toString() {
        return "ElementModule{" +
                "modules=" + modules +
                ", id_element_module=" + id_element_module +
                ", code_element_module='" + code_element_module + '\'' +
                ", element_module_name='" + element_module_name + '\'' +
                ", enseignant=" + enseignant +
                '}';
    }
}
