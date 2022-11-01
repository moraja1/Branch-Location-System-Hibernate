package com.una.data.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "branch")
@NamedQueries({
        @NamedQuery(name = "Branch.findById", query = "select b from Branch b where b.idBranch = :idBranch"),
        @NamedQuery(name = "Branch.findAll", query = "select b from Branch b"),
        @NamedQuery(name = "Branch.findByDistrict", query = "select b from Branch b where b.districtById = :districtById"),
        @NamedQuery(name = "Branch.findByCanton", query = "select b from Branch b where b.cantonById = :cantonById"),
        @NamedQuery(name = "Branch.findByProvince", query = "select b from Branch b where b.provinceById = :provinceById"),
})
public class Branch extends EntityParent{
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
    private District districtById;
    @ManyToOne
    @JoinColumn(name = "id_canton", referencedColumnName = "id_canton", nullable = false)
    private Canton cantonById;
    @ManyToOne
    @JoinColumn(name = "id_province", referencedColumnName = "id_province", nullable = false)
    private Province provinceById;
    @OneToMany(mappedBy = "branchById")
    private Collection<Employee> employeesById;

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

    public District getDistrict() {
        return districtById;
    }

    public void setDistrict(District district) {
        this.districtById = district;
    }

    public Canton getCanton() {
        return cantonById;
    }

    public void setCanton(Canton cantonByIdCanton) {
        this.cantonById = cantonByIdCanton;
    }

    public Province getProvince() {
        return provinceById;
    }

    public void setProvince(Province province) {
        this.provinceById = province;
    }

    public Collection<Employee> getEmployees() {
        return employeesById;
    }

    public void setEmployees(Collection<Employee> employees) {
        this.employeesById = employees;
    }
}
