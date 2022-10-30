package com.una.data.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_district", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_canton")
    private Canton idCanton;

    @Column(name = "name_district", length = 45)
    private String nameDistrict;

    @OneToMany(mappedBy = "idDistrict")
    private Set<Branch> branches = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Canton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Canton idCanton) {
        this.idCanton = idCanton;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

}