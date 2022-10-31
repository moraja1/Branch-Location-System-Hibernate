package com.una.business.dtoModels;

import com.una.data.model.Province;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.Province} entity
 */
public class ProvinceDetails implements Serializable {
    private String nameProvince;

    public ProvinceDetails(Province province){
        this.nameProvince = province.getNameProvince();
    }

    public String getNameProvince() {
        return nameProvince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProvinceDetails entity = (ProvinceDetails) o;
        return Objects.equals(this.nameProvince, entity.nameProvince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameProvince);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nameProvince = " + nameProvince + ")";
    }
}