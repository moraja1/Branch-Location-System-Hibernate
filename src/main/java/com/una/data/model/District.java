package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "district")
@NamedQueries({
        @NamedQuery(name = "District.findById", query = "select d from District d where d.idDistrict = :idDistrict"),
        @NamedQuery(name = "District.findAll", query = "select d from District d"),
        @NamedQuery(name = "District.findByCantonById", query = "select d from District d where d.cantonById = :cantonById")
})
public class District extends EntityParent {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_district")
    private Integer idDistrict;
    @Basic
    @Column(name = "name_district")
    private String nameDistrict;
    @OneToMany(mappedBy = "districtById")
    private Collection<Branch> branchesById;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    private Canton cantonById;

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
        return branchesById;
    }

    public void setBranchesByIdDistrict(Collection<Branch> branchesByIdDistrict) {
        this.branchesById = branchesByIdDistrict;
    }

    public Canton getCantonByIdCanton() {
        return cantonById;
    }

    public void setCantonByIdCanton(Canton cantonByIdCanton) {
        this.cantonById = cantonByIdCanton;
    }
}
