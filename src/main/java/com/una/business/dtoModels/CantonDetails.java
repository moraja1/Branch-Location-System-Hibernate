package com.una.business.dtoModels;

import com.una.data.model.Canton;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.una.data.model.Canton} entity
 */
public class CantonDetails implements Serializable {
    private String nameCanton;

    public CantonDetails(Canton canton){
        this.nameCanton = canton.getNameCanton();
    }

    public String getNameCanton() {
        return nameCanton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CantonDetails entity = (CantonDetails) o;
        return Objects.equals(this.nameCanton, entity.nameCanton);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameCanton);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "nameCanton = " + nameCanton + ")";
    }
}