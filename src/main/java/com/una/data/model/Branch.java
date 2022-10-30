package com.una.data.model;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @Column(name = "id_branch", nullable = false)
    private Integer id;

    @Column(name = "address", length = 32)
    private String address;

    @Column(name = "coord_x")
    private Integer coordX;

    @Column(name = "coord_y")
    private Integer coordY;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_district", nullable = false)
    private District idDistrict;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_canton", nullable = false)
    private Canton idCanton;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_province", nullable = false)
    private Province idProvince;

    @OneToMany(mappedBy = "idBranch")
    private Set<Employee> employees = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public District getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(District idDistrict) {
        this.idDistrict = idDistrict;
    }

    public Canton getIdCanton() {
        return idCanton;
    }

    public void setIdCanton(Canton idCanton) {
        this.idCanton = idCanton;
    }

    public Province getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Province idProvince) {
        this.idProvince = idProvince;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

}