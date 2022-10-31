package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "province")
@NamedQueries({
        @NamedQuery(name = "Province.findById", query = "select p from Province p where p.idProvince = :idProvince"),
        @NamedQuery(name = "Province.findAll", query = "select p from Province p")
})
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
    @OneToMany(mappedBy = "provinceById")
    private Collection<Branch> branchesById;
    @OneToMany(mappedBy = "provinceById")
    private Collection<Canton> cantonsById;

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
        return branchesById;
    }

    public void setBranchesByIdProvince(Collection<Branch> branchesByIdProvince) {
        this.branchesById = branchesByIdProvince;
    }

    public Collection<Canton> getCantonsByIdProvince() {
        return cantonsById;
    }

    public void setCantonsByIdProvince(Collection<Canton> cantonsByIdProvince) {
        this.cantonsById = cantonsByIdProvince;
    }
}
