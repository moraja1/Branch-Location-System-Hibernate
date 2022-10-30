package data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "province", schema = "sise", catalog = "")
public class ProvinceEntity {
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
    @OneToMany(mappedBy = "province")
    private Collection<BranchEntity> branches;
    @OneToMany(mappedBy = "province")
    private Collection<CantonEntity> cantons;

    public Byte getIdProvince() {
        return idProvince;
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

    public Collection<BranchEntity> getBranches() {
        return branches;
    }

    public void setBranches(Collection<BranchEntity> branches) {
        this.branches = branches;
    }

    public Collection<CantonEntity> getCantons() {
        return cantons;
    }

    public void setCantons(Collection<CantonEntity> cantons) {
        this.cantons = cantons;
    }
}
