package com.una.business.dtoModels;

import com.una.data.model.District;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.District} entity
 */
public class DistrictDetails implements Serializable {
    private String nameDistrict;

    public DistrictDetails(District district){
        this.nameDistrict = district.getNameDistrict();
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistrictDetails entity = (DistrictDetails) o;
        return Objects.equals(this.nameDistrict, entity.nameDistrict);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameDistrict);
    }

    @Override
    public String toString() {
        return nameDistrict;
    }
}