package com.una.data.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "province")
public class Province {
    @Id
    @Column(name = "id_province", nullable = false)
    private Boolean id = false;

    @Column(name = "name_province", length = 10)
    private String nameProvince;

    @Column(name = "zone_percentage", nullable = false)
    private Byte zonePercentage;

    @OneToMany(mappedBy = "idProvince")
    private Set<Branch> branches = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idProvince")
    private Set<Canton> cantons = new LinkedHashSet<>();

    public Boolean getId() {
        return id;
    }

    public void setId(Boolean id) {
        this.id = id;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public Byte getZonePercentage() {
        return zonePercentage;
    }

    public void setZonePercentage(Byte zonePercentage) {
        this.zonePercentage = zonePercentage;
    }

    public Set<Branch> getBranches() {
        return branches;
    }

    public void setBranches(Set<Branch> branches) {
        this.branches = branches;
    }

    public Set<Canton> getCantons() {
        return cantons;
    }

    public void setCantons(Set<Canton> cantons) {
        this.cantons = cantons;
    }

}