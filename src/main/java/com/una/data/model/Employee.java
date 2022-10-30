package com.una.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "id_employee", nullable = false)
    private Integer id;

    @Column(name = "name_employee", length = 32)
    private String nameEmployee;

    @Column(name = "phone_number", length = 32)
    private String phoneNumber;

    @Column(name = "base_salary")
    private Double baseSalary;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_branch", nullable = false)
    private Branch idBranch;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public Branch getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Branch idBranch) {
        this.idBranch = idBranch;
    }

}