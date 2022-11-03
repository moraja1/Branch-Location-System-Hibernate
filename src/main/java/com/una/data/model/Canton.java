package com.una.data.model;

import com.una.data.dao.CantonDAO;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "canton")
@NamedQueries({
        @NamedQuery(name = "Canton.findById", query = "select c from Canton c where c.idCanton = :idCanton"),
        @NamedQuery(name = "Canton.findAll", query = "select c from Canton c"),
        @NamedQuery(name = "Canton.findByProvince", query = "select c from Canton c where c.provinceById = :provinceById"),
        @NamedQuery(name = "Canton.findByProvinceByName", query = "select c from Canton c where c.provinceById.nameProvince = :nameProvince"),
        @NamedQuery(name = "Canton.findByNameCanton", query = "select c from Canton c where c.nameCanton = :nameCanton")
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

    public Collection<Branch> getBranches() {
        if(branchesById == null){
            branchesById = CantonDAO.getBranches(this);
        }
        return branchesById;
    }

    public void setBranches(Collection<Branch> branches) {
        this.branchesById = branches;
    }

    public Province getProvince() {
        return provinceById;
    }

    public void setProvince(Province province) {
        this.provinceById = province;
    }

    public Collection<District> getDistricts() {
        return districtsById;
    }

    public void setDistricts(Collection<District> districts) {
        this.districtsById = districts;
    }
}
