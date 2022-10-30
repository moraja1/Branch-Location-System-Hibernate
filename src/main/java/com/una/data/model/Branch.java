package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "branch")
public class Branch extends EntityParent {
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
    private District districtByIdDistrict;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton", nullable = false)
    private Canton cantonByIdCanton;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private Province provinceByIdProvince;
    @OneToMany(mappedBy = "branchByIdBranch")
    private Collection<Employee> employeesByIdBranch;

    public Integer getId() {
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

    public District getDistrictByIdDistrict() {
        return districtByIdDistrict;
    }

    public void setDistrictByIdDistrict(District districtByIdDistrict) {
        this.districtByIdDistrict = districtByIdDistrict;
    }

    public Canton getCantonByIdCanton() {
        return cantonByIdCanton;
    }

    public void setCantonByIdCanton(Canton cantonByIdCanton) {
        this.cantonByIdCanton = cantonByIdCanton;
    }

    public Province getProvinceByIdProvince() {
        return provinceByIdProvince;
    }

    public void setProvinceByIdProvince(Province provinceByIdProvince) {
        this.provinceByIdProvince = provinceByIdProvince;
    }

    public Collection<Employee> getEmployeesByIdBranch() {
        return employeesByIdBranch;
    }

    public void setEmployeesByIdBranch(Collection<Employee> employeesByIdBranch) {
        this.employeesByIdBranch = employeesByIdBranch;
    }
}
