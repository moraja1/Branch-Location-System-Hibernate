package com.una.data.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityParent {
    public abstract Integer getId();
}
