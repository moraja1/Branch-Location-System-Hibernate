package org.una.data.model;

import jakarta.persistence.*;

@Entity
@Table(name = "employee", schema = "sise", catalog = "")
public class EmployeeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_employee")
    private Integer idEmployee;
    @Basic
    @Column(name = "name_employee")
    private String nameEmployee;
    @Basic
    @Column(name = "phone_number")
    private String phoneNumber;
    @Basic
    @Column(name = "base_salary")
    private Double baseSalary;
    @Basic
    @Column(name = "id_branch")
    private Integer idBranch;
    @ManyToOne
    @JoinColumn(name = "id_branch", referencedColumnName = "id_branch", nullable = false)
    private BranchEntity branchByIdBranch;

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
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

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployeeEntity that = (EmployeeEntity) o;

        if (idEmployee != null ? !idEmployee.equals(that.idEmployee) : that.idEmployee != null) return false;
        if (nameEmployee != null ? !nameEmployee.equals(that.nameEmployee) : that.nameEmployee != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (baseSalary != null ? !baseSalary.equals(that.baseSalary) : that.baseSalary != null) return false;
        if (idBranch != null ? !idBranch.equals(that.idBranch) : that.idBranch != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idEmployee != null ? idEmployee.hashCode() : 0;
        result = 31 * result + (nameEmployee != null ? nameEmployee.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (baseSalary != null ? baseSalary.hashCode() : 0);
        result = 31 * result + (idBranch != null ? idBranch.hashCode() : 0);
        return result;
    }

    public BranchEntity getBranchByIdBranch() {
        return branchByIdBranch;
    }

    public void setBranchByIdBranch(BranchEntity branchByIdBranch) {
        this.branchByIdBranch = branchByIdBranch;
    }
}
