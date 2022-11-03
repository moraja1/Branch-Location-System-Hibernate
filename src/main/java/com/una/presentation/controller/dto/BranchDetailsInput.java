package com.una.presentation.controller.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.Branch} entity
 */
public class BranchDetailsInput implements Serializable {
    private final Integer idBranch;
    private final String address;
    private final Integer coordX;
    private final Integer coordY;
    private final Integer districtId;
    private final Integer cantonId;
    private final Byte provinceId;

    public BranchDetailsInput(Integer idBranch, String address, Integer coordX, Integer coordY, Integer districtId, Integer cantonId, Byte provinceId) {
        this.idBranch = idBranch;
        this.address = address;
        this.coordX = coordX;
        this.coordY = coordY;
        this.districtId = districtId;
        this.cantonId = cantonId;
        this.provinceId = provinceId;
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

    public Integer getDistrictId() {
        return districtId;
    }

    public Integer getCantonId() {
        return cantonId;
    }

    public Byte getProvinceId() {
        return provinceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchDetailsInput entity = (BranchDetailsInput) o;
        return Objects.equals(this.idBranch, entity.idBranch) &&
                Objects.equals(this.address, entity.address) &&
                Objects.equals(this.coordX, entity.coordX) &&
                Objects.equals(this.coordY, entity.coordY) &&
                Objects.equals(this.districtId, entity.districtId) &&
                Objects.equals(this.cantonId, entity.cantonId) &&
                Objects.equals(this.provinceId, entity.provinceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBranch, address, coordX, coordY, districtId, cantonId, provinceId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idBranch = " + idBranch + ", " +
                "address = " + address + ", " +
                "coordX = " + coordX + ", " +
                "coordY = " + coordY + ", " +
                "districtIdDistrict = " + districtId + ", " +
                "cantonIdCanton = " + cantonId + ", " +
                "provinceIdProvince = " + provinceId + ")";
    }
}