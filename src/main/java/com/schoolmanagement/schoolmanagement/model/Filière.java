package com.schoolmanagement.schoolmanagement.model;

import jakarta.persistence.*;

@Entity

public class Filière {
    @ManyToOne
    @JoinColumn(name = "dept_id",referencedColumnName = "id_dept")
    private Departement departement;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_filiere;
    @Column(name = "Filiere_name", nullable = false , unique = true)

    private String filiere_name;
    @Column(name = "Chef_filiere_name", nullable = false , unique = true)

    private String chef_filière;


    public Filière() {
    }

    public Filière(int id_filiere, String filiere_name, String chef_filière) {
        this.id_filiere = id_filiere;
        this.filiere_name = filiere_name;
        this.chef_filière = chef_filière;
    }

    public int getId_filiere() {
        return id_filiere;
    }

    public String getFiliere_name() {
        return filiere_name;
    }

    public String getChef_filière() {
        return chef_filière;
    }

    public Departement getDepartement(){
        return departement;
    }

    public void setFiliere_name(String filiere_name) {
        this.filiere_name = filiere_name;
    }

    public void setChef_filière(String chef_filière) {
        this.chef_filière = chef_filière;
    }

    public void setDepartement(Departement departement){
        this.departement=departement;
    }
    public int getDeptId() {
        if (departement != null) {
            return departement.getId_dept();
        }
        return -1; // Return -1 or handle this null case as needed
    }

    @Override
    public String toString() {
        return "Filière{" +
                "departement=" + departement +
                ", id_filiere=" + id_filiere +
                ", filiere_name='" + filiere_name + '\'' +
                ", chef_filière='" + chef_filière + '\'' +

                '}';
    }
}
