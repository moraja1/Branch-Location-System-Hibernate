package com.una.business.dtoModels;

import com.una.data.model.Branch;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.Branch} entity
 */
public class BranchDetails implements Serializable {
    private final Integer idBranch;
    private final String address;
    private final Integer coordX;
    private final Integer coordY;
    private final String reference;
    private final Byte zonePercentage;

    public BranchDetails(Branch branch) {
        this.idBranch = branch.getId();
        this.address = branch.getAddress();
        this.coordX = branch.getCoordX();
        this.coordY = branch.getCoordY();
        this.reference = branch.getDistrict().getNameDistrict();
        this.zonePercentage = branch.getProvince().getZonePercentage();
    }

    public BranchDetails(Integer idBranch, String address, Integer coordX, Integer coordY, String reference, Byte zonePercentage) {
        this.idBranch = idBranch;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
        this.reference = reference;
        this.zonePercentage = zonePercentage;
    }

    public Integer getIdBranch() {
        return idBranch;
    }

    public String getAddress() {
        return address;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public Integer getCoordY() {
        return coordY;
    }

    public String getReference() {
        return reference;
    }

    public Byte getZonePercentage() {
        return zonePercentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchDetails entity = (BranchDetails) o;
        return Objects.equals(this.idBranch, entity.idBranch) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.coordX, entity.coordX) &&
                Objects.equals(this.coordY, entity.coordY) &&
                Objects.equals(this.reference, entity.reference) &&
                Objects.equals(this.zonePercentage, entity.zonePercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBranch, address, coordX, coordY, reference, zonePercentage);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idBranch = " + idBranch + ", " +
                "address = " + address + ", " +
                "coordX = " + coordX + ", " +
                "coordY = " + coordY + ", " +
                "districtByIdNameDistrict = " + reference + ", " +
                "provinceByIdZonePercentage = " + zonePercentage + ")";
    }
}