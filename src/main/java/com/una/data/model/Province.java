package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "province")
public class Province extends EntityParent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_province")
    private Byte idProvince;
    @Basic
    @Column(name = "name_province")
    private String nameProvince;
    @Basic
    @Column(name = "zone_percentage")
    private Byte zonePercentage;
    @OneToMany(mappedBy = "provinceByIdProvince")
    private Collection<Branch> branchesByIdProvince;
    @OneToMany(mappedBy = "provinceByIdProvince")
    private Collection<Canton> cantonsByIdProvince;

    public Integer getId() {
        return idProvince.intValue();
    }

    public void setIdProvince(Byte idProvince) {
        this.idProvince = idProvince;
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

    public Collection<Branch> getBranchesByIdProvince() {
        return branchesByIdProvince;
    }

    public void setBranchesByIdProvince(Collection<Branch> branchesByIdProvince) {
        this.branchesByIdProvince = branchesByIdProvince;
    }

    public Collection<Canton> getCantonsByIdProvince() {
        return cantonsByIdProvince;
    }

    public void setCantonsByIdProvince(Collection<Canton> cantonsByIdProvince) {
        this.cantonsByIdProvince = cantonsByIdProvince;
    }
}
