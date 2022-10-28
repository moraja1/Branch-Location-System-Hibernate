package org.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "canton", schema = "sise")
public class CantonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_canton")
    private Integer idCanton;
    @Basic
    @Column(name = "name_canton")
    private String nameCanton;
    @OneToMany(mappedBy = "cantonByIdCanton")
    private Collection<BranchEntity> branchesByIdCanton;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private ProvinceEntity provinceByIdProvince;
    @OneToMany(mappedBy = "cantonByIdCanton")
    private Collection<DistrictEntity> districtsByIdCanton;

    public Integer getIdCanton() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CantonEntity that = (CantonEntity) o;

        if (idCanton != null ? !idCanton.equals(that.idCanton) : that.idCanton != null) return false;
        if (nameCanton != null ? !nameCanton.equals(that.nameCanton) : that.nameCanton != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCanton != null ? idCanton.hashCode() : 0;
        result = 31 * result + (nameCanton != null ? nameCanton.hashCode() : 0);
        return result;
    }

    public Collection<BranchEntity> getBranchesByIdCanton() {
        return branchesByIdCanton;
    }

    public void setBranchesByIdCanton(Collection<BranchEntity> branchesByIdCanton) {
        this.branchesByIdCanton = branchesByIdCanton;
    }

    public ProvinceEntity getProvinceByIdProvince() {
        return provinceByIdProvince;
    }

    public void setProvinceByIdProvince(ProvinceEntity provinceByIdProvince) {
        this.provinceByIdProvince = provinceByIdProvince;
    }

    public Collection<DistrictEntity> getDistrictsByIdCanton() {
        return districtsByIdCanton;
    }

    public void setDistrictsByIdCanton(Collection<DistrictEntity> districtsByIdCanton) {
        this.districtsByIdCanton = districtsByIdCanton;
    }
}
