package data.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "branch", schema = "sise", catalog = "")
public class BranchEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_branch")
    private Integer idBranch;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "coord_x")
    private Integer coordX;
    @Basic
    @Column(name = "coord_y")
    private Integer coordY;
    @ManyToOne
    @JoinColumn(name = "id_district", referencedColumnName = "id_district", nullable = false)
    private DistrictEntity district;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton", nullable = false)
    private CantonEntity canton;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private ProvinceEntity province;
    @OneToMany(mappedBy = "branch")
    private Set<EmployeeEntity> employees;

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public void setCoordX(Integer coordX) {
        this.coordX = coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }

    public void setCoordY(Integer coordY) {
        this.coordY = coordY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BranchEntity that = (BranchEntity) o;

        if (idBranch != null ? !idBranch.equals(that.idBranch) : that.idBranch != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (coordX != null ? !coordX.equals(that.coordX) : that.coordX != null) return false;
        if (coordY != null ? !coordY.equals(that.coordY) : that.coordY != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBranch != null ? idBranch.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (coordX != null ? coordX.hashCode() : 0);
        result = 31 * result + (coordY != null ? coordY.hashCode() : 0);
        return result;
    }

    public DistrictEntity getDistrict() {
        return district;
    }

    public void setDistrict(DistrictEntity district) {
        this.district = district;
    }

    public CantonEntity getCanton() {
        return canton;
    }

    public void setCanton(CantonEntity canton) {
        this.canton = canton;
    }

    public ProvinceEntity getProvince() {
        return province;
    }

    public void setProvince(ProvinceEntity province) {
        this.province = province;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }
}
