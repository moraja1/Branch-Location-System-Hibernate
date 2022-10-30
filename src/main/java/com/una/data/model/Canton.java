package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "canton")
public class Canton extends EntityParent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_canton")
    private Integer idCanton;
    @Basic
    @Column(name = "name_canton")
    private String nameCanton;
    @OneToMany(mappedBy = "cantonByIdCanton")
    private Collection<Branch> branchesByIdCanton;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private Province provinceByIdProvince;
    @OneToMany(mappedBy = "cantonByIdCanton")
    private Collection<District> districtsByIdCanton;

    public Integer getId() {
        return idCanton;
    }

    public void setIdCanton(Integer idCanton) {
        this.idCanton = idCanton;
    }

    public String getNameCanton() {
        return nameCanton;
    }

    public void setNameCanton(String nameCanton) {
        this.nameCanton = nameCanton;
    }

    public Collection<Branch> getBranchesByIdCanton() {
        return branchesByIdCanton;
    }

    public void setBranchesByIdCanton(Collection<Branch> branchesByIdCanton) {
        this.branchesByIdCanton = branchesByIdCanton;
    }

    public Province getProvinceByIdProvince() {
        return provinceByIdProvince;
    }

    public void setProvinceByIdProvince(Province provinceByIdProvince) {
        this.provinceByIdProvince = provinceByIdProvince;
    }

    public Collection<District> getDistrictsByIdCanton() {
        return districtsByIdCanton;
    }

    public void setDistrictsByIdCanton(Collection<District> districtsByIdCanton) {
        this.districtsByIdCanton = districtsByIdCanton;
    }
}
