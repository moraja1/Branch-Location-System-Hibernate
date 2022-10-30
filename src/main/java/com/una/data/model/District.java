package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "district")
public class District extends EntityParent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_district")
    private Integer idDistrict;
    @Basic
    @Column(name = "name_district")
    private String nameDistrict;
    @OneToMany(mappedBy = "districtByIdDistrict")
    private Collection<Branch> branchesByIdDistrict;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    private Canton cantonByIdCanton;

    public Integer getId() {
        return idDistrict;
    }

    public void setIdDistrict(Integer idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public void setNameDistrict(String nameDistrict) {
        this.nameDistrict = nameDistrict;
    }

    public Collection<Branch> getBranchesByIdDistrict() {
        return branchesByIdDistrict;
    }

    public void setBranchesByIdDistrict(Collection<Branch> branchesByIdDistrict) {
        this.branchesByIdDistrict = branchesByIdDistrict;
    }

    public Canton getCantonByIdCanton() {
        return cantonByIdCanton;
    }

    public void setCantonByIdCanton(Canton cantonByIdCanton) {
        this.cantonByIdCanton = cantonByIdCanton;
    }
}
