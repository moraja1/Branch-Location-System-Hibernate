package com.una.business.dtoModels;

import com.una.data.model.Province;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.Province} entity
 */
public class ProvinceDetails implements Serializable {
    private final Byte idProvince;
    private final String nameProvince;
    private final Byte zonePercentage;

    public ProvinceDetails(Province province){
        this.idProvince = province.getId().byteValue();
        this.nameProvince = province.getNameProvince();
        this.zonePercentage = province.getZonePercentage();
    }

    public Byte getIdProvince() {
        return idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public Byte getZonePercentage() {
        return zonePercentage;
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
        return nameProvince;
    }
}