package org.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

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
    @Basic
    @Column(name = "id_district")
    private Integer idDistrict;
    @Basic
    @Column(name = "id_canton")
    private Integer idCanton;
    @Basic
    @Column(name = "id_province")
    private Byte idProvince;
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

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(Integer idDistrict) {
        this.idDistrict = idDistrict;
    }

    public Integer getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Integer idCanton) {
        this.idCanton = idCanton;
    }

    public Byte getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Byte idProvince) {
        this.idProvince = idProvince;
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
        if (idDistrict != null ? !idDistrict.equals(that.idDistrict) : that.idDistrict != null) return false;
        if (idCanton != null ? !idCanton.equals(that.idCanton) : that.idCanton != null) return false;
        if (idProvince != null ? !idProvince.equals(that.idProvince) : that.idProvince != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idBranch != null ? idBranch.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (coordX != null ? coordX.hashCode() : 0);
        result = 31 * result + (coordY != null ? coordY.hashCode() : 0);
        result = 31 * result + (idDistrict != null ? idDistrict.hashCode() : 0);
        result = 31 * result + (idCanton != null ? idCanton.hashCode() : 0);
        result = 31 * result + (idProvince != null ? idProvince.hashCode() : 0);
        return result;
    }

    public DistrictEntity getDistrictByIdDistrict() {
        return districtByIdDistrict;
    }

    public void setDistrictByIdDistrict(DistrictEntity districtByIdDistrict) {
        this.districtByIdDistrict = districtByIdDistrict;
    }

    public CantonEntity getCantonByIdCanton() {
        return cantonByIdCanton;
    }

    public void setCantonByIdCanton(CantonEntity cantonByIdCanton) {
        this.cantonByIdCanton = cantonByIdCanton;
    }

    public ProvinceEntity getProvinceByIdProvince() {
        return provinceByIdProvince;
    }

    public void setProvinceByIdProvince(ProvinceEntity provinceByIdProvince) {
        this.provinceByIdProvince = provinceByIdProvince;
    }

    public Collection<EmployeeEntity> getEmployeesByIdBranch() {
        return employeesByIdBranch;
    }

    public void setEmployeesByIdBranch(Collection<EmployeeEntity> employeesByIdBranch) {
        this.employeesByIdBranch = employeesByIdBranch;
    }
}
