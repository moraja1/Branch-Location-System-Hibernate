package com.una.data.model;

import com.una.data.dao.DistrictDAO;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "district")
@NamedQueries({
        @NamedQuery(name = "District.findById", query = "select d from District d where d.idDistrict = :idDistrict"),
        @NamedQuery(name = "District.findAll", query = "select d from District d"),
        @NamedQuery(name = "District.findByCanton", query = "select d from District d where d.cantonById = :cantonById"),
        @NamedQuery(name = "District.findByCantonByName", query = "select d from District d where d.cantonById.nameCanton = :nameCanton")
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

    public Collection<Branch> getBranches() {
        if(branchesById == null){
            branchesById = DistrictDAO.getBranches(this);
        }
        return branchesById;
    }

    public void setBranches(Collection<Branch> branchesByIdDistrict) {
        this.branchesById = branchesByIdDistrict;
    }

    public Canton getCanton() {
        return cantonById;
    }

    public void setCanton(Canton canton) {
        this.cantonById = canton;
    }
}
