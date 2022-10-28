package org.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "branch", schema = "sise")
@NamedQueries({
        @NamedQuery(name = "BranchEntity.existsByID", query = "select (count(b) > 0) from BranchEntity b where b.idBranch = :idBranch")
})
public class BranchEntity {
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private DistrictEntity districtByIdDistrict;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton", nullable = false)
    private CantonEntity cantonByIdCanton;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private ProvinceEntity provinceByIdProvince;
    @OneToMany(mappedBy = "branchByIdBranch")
    private Collection<EmployeeEntity> employeesByIdBranch;

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
        return districtByIdDistrict;
    }

    public void setDistrict(DistrictEntity district) {
        this.districtByIdDistrict = districtByIdDistrict;
    }

    public CantonEntity getCanton() {
        return cantonByIdCanton;
    }

    public void setCanton(CantonEntity canton) {
        this.cantonByIdCanton = cantonByIdCanton;
    }

    public ProvinceEntity getProvince() {
        return provinceByIdProvince;
    }

    public void setProvince(ProvinceEntity province) {
        this.provinceByIdProvince = provinceByIdProvince;
    }

    public Collection<EmployeeEntity> getEmployees() {
        return employeesByIdBranch;
    }

    public void setEmployees(Collection<EmployeeEntity> employees) {
        this.employeesByIdBranch = employeesByIdBranch;
    }
}
