package data.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "district", schema = "sise", catalog = "")
public class DistrictEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_district")
    private Integer idDistrict;
    @Basic
    @Column(name = "name_district")
    private String nameDistrict;
    @OneToMany(mappedBy = "district")
    private Set<BranchEntity> branches;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton")
    private CantonEntity canton;

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

    public Set<BranchEntity> getBranches() {
        return branches;
    }

    public void setBranches(Set<BranchEntity> branches) {
        this.branches = branches;
    }

    public CantonEntity getCanton() {
        return canton;
    }

    public void setCanton(CantonEntity canton) {
        this.canton = canton;
    }
}
