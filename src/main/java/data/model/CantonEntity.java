package data.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "canton", schema = "sise", catalog = "")
public class CantonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_canton")
    private Integer idCanton;
    @Basic
    @Column(name = "name_canton")
    private String nameCanton;
    @OneToMany(mappedBy = "canton")
    private Set<BranchEntity> branches;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private ProvinceEntity province;
    @OneToMany(mappedBy = "canton")
    private Set<DistrictEntity> districts;

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

    public Set<BranchEntity> getBranches() {
        return branches;
    }

    public void setBranches(Set<BranchEntity> branches) {
        this.branches = branches;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public Set<DistrictEntity> getDistricts() {
        return districts;
    }

    public void setDistricts(Set<DistrictEntity> districts) {
        this.districts = districts;
    }
}
