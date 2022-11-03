package com.una.business.dtoModels;

import com.una.data.model.District;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.District} entity
 */
public class ReferenceDetails implements Serializable {
    private final Integer idDistrict;
    private final String nameDistrict;
    private final Integer idCanton;
    private final String nameCanton;
    private final Byte idProvince;
    private final String nameProvince;

    public ReferenceDetails(District d) {
        this.idDistrict = d.getId();
        this.nameDistrict = d.getNameDistrict();
        this.idCanton = d.getCanton().getId();
        this.nameCanton = d.getCanton().getNameCanton();
        this.idProvince = d.getCanton().getProvince().getId().byteValue();
        this.nameProvince = d.getCanton().getProvince().getNameProvince();
    }

    public ReferenceDetails(Integer idDistrict, String nameDistrict, Integer idCanton, String nameCanton, Byte idProvince, String nameProvince) {
        this.idDistrict = idDistrict;
        this.nameDistrict = nameDistrict;
        this.idCanton = idCanton;
        this.nameCanton = nameCanton;
        this.idProvince = idProvince;
        this.nameProvince = nameProvince;
    }

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public String getNameDistrict() {
        return nameDistrict;
    }

    public Integer getIdCanton() {
        return idCanton;
    }

    public String getNameCanton() {
        return nameCanton;
    }

    public Byte getIdProvince() {
        return idProvince;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferenceDetails entity = (ReferenceDetails) o;
        return Objects.equals(this.idDistrict, entity.idDistrict) &&
                Objects.equals(this.nameDistrict, entity.nameDistrict) &&
                Objects.equals(this.idCanton, entity.idCanton) &&
                Objects.equals(this.nameCanton, entity.nameCanton) &&
                Objects.equals(this.idProvince, entity.idProvince) &&
                Objects.equals(this.nameProvince, entity.nameProvince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDistrict, nameDistrict, idCanton, nameCanton, idProvince, nameProvince);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "idDistrict = " + idDistrict + ", " +
                "nameDistrict = " + nameDistrict + ", " +
                "cantonByIdIdCanton = " + idCanton + ", " +
                "cantonByIdNameCanton = " + nameCanton + ", " +
                "cantonByIdProvinceByIdIdProvince = " + idProvince + ", " +
                "cantonByIdProvinceByIdNameProvince = " + nameProvince + ")";
    }
}