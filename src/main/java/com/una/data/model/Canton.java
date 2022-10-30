package com.una.data.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "canton")
public class Canton {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_canton", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_province", nullable = false)
    private Province idProvince;

    @Column(name = "name_canton", length = 45)
    private String nameCanton;

    @OneToMany(mappedBy = "idCanton")
    private Set<Branch> branches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idCanton")
    private Set<District> districts = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Province getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Province idProvince) {
        this.idProvince = idProvince;
    }

    public String getNameCanton() {
        return nameCanton;
    }

    public void setNameCanton(String nameCanton) {
        this.nameCanton = nameCanton;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Set<District> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<District> districts) {
        this.districts = districts;
    }

}