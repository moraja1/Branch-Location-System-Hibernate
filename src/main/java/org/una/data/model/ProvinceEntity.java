package org.una.data.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.Collection;

@Entity
@Table(name = "province", schema = "sise")
public class ProvinceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_province")
    private Integer idProvince;
    @Basic
    @Column(name = "name_province")
    private String nameProvince;
    @Basic
    @Column(name = "zone_percentage")
    private Byte zonePercentage;
    @OneToMany(mappedBy = "provinceByIdProvince")
    private Collection<BranchEntity> branchesByIdProvince;
    @OneToMany(mappedBy = "provinceByIdProvince")
    private Collection<CantonEntity> cantonsByIdProvince;

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProvinceEntity that = (ProvinceEntity) o;

        if (idProvince != null ? !idProvince.equals(that.idProvince) : that.idProvince != null) return false;
        if (nameProvince != null ? !nameProvince.equals(that.nameProvince) : that.nameProvince != null) return false;
        if (zonePercentage != null ? !zonePercentage.equals(that.zonePercentage) : that.zonePercentage != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idProvince != null ? idProvince.hashCode() : 0;
        result = 31 * result + (nameProvince != null ? nameProvince.hashCode() : 0);
        result = 31 * result + (zonePercentage != null ? zonePercentage.hashCode() : 0);
        return result;
    }

    public Collection<BranchEntity> getBranchesByIdProvince() {
        return branchesByIdProvince;
    }

    public void setBranchesByIdProvince(Collection<BranchEntity> branchesByIdProvince) {
        this.branchesByIdProvince = branchesByIdProvince;
    }

    public Collection<CantonEntity> getCantonsByIdProvince() {
        return cantonsByIdProvince;
    }

    public void setCantonsByIdProvince(Collection<CantonEntity> cantonsByIdProvince) {
        this.cantonsByIdProvince = cantonsByIdProvince;
    }
}
