package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;
import lombok.*;

@Entity

public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dept;
    @Column(name = "Departement_name", nullable = false , unique = true)

    private String dept_name;
    @Column(name = "Departement_responsable", nullable = false, unique = true)
    private String dept_respo;

    public Departement() {
    }

    public Departement(int id_dept ,String dept_name, String dept_respo)  {
        this.id_dept = id_dept;
        this.dept_name=dept_name;
        this.dept_respo=dept_respo;
    }

    public void setId_dept(int id_dept) {
        this.id_dept = id_dept;
    }

    public int getId_dept() {
        return id_dept;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_respo(String dept_respo) {
        this.dept_respo = dept_respo;
    }

    public String getDept_respo() {
        return dept_respo;
    }

    @Override
    public String toString() {
        return "Departement{" +
                "id_dept=" + id_dept +
                ", dept_name='" + dept_name + '\'' +
                ", dept_respo='" + dept_respo + '\'' +
                '}';
    }
}
