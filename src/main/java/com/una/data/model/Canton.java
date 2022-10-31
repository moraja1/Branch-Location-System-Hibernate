package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "canton")
@NamedQueries({
        @NamedQuery(name = "Canton.findById", query = "select c from Canton c where c.idCanton = :idCanton"),
        @NamedQuery(name = "Canton.findAll", query = "select c from Canton c"),
        @NamedQuery(name = "Canton.findByProvinceById", query = "select c from Canton c where c.provinceById = :provinceById")
})
public class Canton extends EntityParent{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_canton")
    private Integer idCanton;
    @Basic
    @Column(name = "name_canton")
    private String nameCanton;
    @OneToMany(mappedBy = "cantonById")
    private Collection<Branch> branchesById;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private Province provinceById;
    @OneToMany(mappedBy = "cantonById")
    private Collection<District> districtsById;

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
        return branchesById;
    }

    public void setBranchesByIdCanton(Collection<Branch> branchesByIdCanton) {
        this.branchesById = branchesByIdCanton;
    }

    public Province getProvinceByIdProvince() {
        return provinceById;
    }

    public void setProvinceByIdProvince(Province provinceByIdProvince) {
        this.provinceById = provinceByIdProvince;
    }

    public Collection<District> getDistrictsByIdCanton() {
        return districtsById;
    }

    public void setDistrictsByIdCanton(Collection<District> districtsByIdCanton) {
        this.districtsById = districtsByIdCanton;
    }
}
