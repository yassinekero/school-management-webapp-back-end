package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;

@Table(name = "Module", schema = "school_management",uniqueConstraints = {
        @UniqueConstraint(
                name = "code_unique",
                columnNames = "cm"
        )
})
@Entity
public class Modules {
    @ManyToOne
    @JoinColumn(name = "filiere_id",referencedColumnName = "id_filiere")
    private Filière filière;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_module;
    @Column(name = "cm", nullable = false, unique = true)
    private String code_module;
    @Column(name = "mn", nullable = false, unique = true)

    private String module_name;

    public Modules(int id_module, String code_module, String module_name) {
        this.id_module = id_module;
        this.code_module = code_module;
        this.module_name = module_name;
    }

    public Modules() {
    }

    public int getId_module() {
        return id_module;
    }

    public String getCode_module() {
        return code_module;
    }

    public String getModule_name() {
        return module_name;
    }

    public Filière getFilière(){
        return filière;
    }

    public void setId_module(int id_module) {
        this.id_module = id_module;
    }

    public void setCode_module(String code_module) {
        this.code_module = code_module;
    }

    public void setModule_name(String module_name) {
        this.module_name = module_name;
    }

    public void setFilière(Filière filière){
        this.filière = filière;
    }

    @Override
    public String toString() {
        return "Modules{" +
                "filière=" + filière +
                ", id_module=" + id_module +
                ", code_module='" + code_module + '\'' +
                ", module_name='" + module_name + '\'' +
                '}';
    }
}

