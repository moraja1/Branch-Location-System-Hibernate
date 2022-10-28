package org.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "district", schema = "sise")
public class DistrictEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_district")
    private Integer idDistrict;
    @Basic
    @Column(name = "name_district")
    private String nameDistrict;
    @OneToMany(mappedBy = "districtByIdDistrict")
    private Collection<BranchEntity> branchesByIdDistrict;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    private CantonEntity cantonByIdCanton;

    public Integer getIdDistrict() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistrictEntity that = (DistrictEntity) o;

        if (idDistrict != null ? !idDistrict.equals(that.idDistrict) : that.idDistrict != null) return false;
        if (nameDistrict != null ? !nameDistrict.equals(that.nameDistrict) : that.nameDistrict != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idDistrict != null ? idDistrict.hashCode() : 0;
        result = 31 * result + (nameDistrict != null ? nameDistrict.hashCode() : 0);
        return result;
    }

    public Collection<BranchEntity> getBranchesByIdDistrict() {
        return branchesByIdDistrict;
    }

    public void setBranchesByIdDistrict(Collection<BranchEntity> branchesByIdDistrict) {
        this.branchesByIdDistrict = branchesByIdDistrict;
    }

    public CantonEntity getCantonByIdCanton() {
        return cantonByIdCanton;
    }

    public void setCantonByIdCanton(CantonEntity cantonByIdCanton) {
        this.cantonByIdCanton = cantonByIdCanton;
    }
}
