package com.una.business.dtoModels;

import com.una.data.model.Employee;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.Employee} entity
 */
public class EmployeeDetails implements Serializable {
    private final Integer idEmployee;
    private final String nameEmployee;
    private final String phoneNumber;
    private final Double baseSalary;
    private final String branchReference;
    private Integer branchId;
    private final Byte zonePercentage;
    private final Double totalSalary;

    public EmployeeDetails(Employee employee){
        this.idEmployee = employee.getId();
        this.nameEmployee = employee.getNameEmployee();
        this.phoneNumber = employee.getPhoneNumber();
        this.baseSalary = employee.getBaseSalary();
        this.branchReference = employee.getBranch().getDistrict().getNameDistrict();
        this.zonePercentage = employee.getBranch().getProvince().getZonePercentage();
        this.totalSalary = baseSalary + (baseSalary * (zonePercentage / 100));
        this.branchId = employee.getBranch().getId();
    }

    public EmployeeDetails(Integer idEmployee, String nameEmployee, String phoneNumber, Double baseSalary, String branchReference, Integer branchID) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.phoneNumber = phoneNumber;
        this.baseSalary = baseSalary;
        this.branchReference = branchReference;
        this.zonePercentage = 0;
        this.totalSalary = 0.0;
    }

    public EmployeeDetails(Integer idEmployee, String nameEmployee, String phoneNumber, Double baseSalary, String branchReference, Byte zonePercentage, Double totalSalary) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.phoneNumber = phoneNumber;
        this.baseSalary = baseSalary;
        this.branchReference = branchReference;
        this.zonePercentage = zonePercentage;
        this.totalSalary = totalSalary;
    }

    public EmployeeDetails(Integer idEmployee, String nameEmployee, String phoneNumber, Double baseSalary, Integer branchId) {
        this.idEmployee = idEmployee;
        this.nameEmployee = nameEmployee;
        this.phoneNumber = phoneNumber;
        this.baseSalary = baseSalary;
        this.branchId = branchId;
        this.branchReference = "";
        this.zonePercentage = 0;
        this.totalSalary = 0.0;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }
    public String getBranchReference() {
        return branchReference;
    }

    public Byte getZonePercentage() {
        return zonePercentage;
    }
    public Double getTotalSalary() { return totalSalary; }

    public Integer getBranchId() {
        return branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDetails entity = (EmployeeDetails) o;
        return Objects.equals(this.idEmployee, entity.idEmployee) &&
                Objects.equals(this.nameEmployee, entity.nameEmployee) &&
                Objects.equals(this.phoneNumber, entity.phoneNumber) &&
                Objects.equals(this.baseSalary, entity.baseSalary) &&
                Objects.equals(this.branchReference, entity.branchReference) &&
                Objects.equals(this.zonePercentage, entity.zonePercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEmployee, nameEmployee, phoneNumber, baseSalary, branchReference, zonePercentage);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idEmployee = " + idEmployee + ", " +
                "nameEmployee = " + nameEmployee + ", " +
                "phoneNumber = " + phoneNumber + ", " +
                "baseSalary = " + baseSalary + ", " +
                "branchByIdDistrictByIdNameDistrict = " + branchReference + ", " +
                "branchByIdProvinceByIdZonePercentage = " + zonePercentage + ")";
    }
}