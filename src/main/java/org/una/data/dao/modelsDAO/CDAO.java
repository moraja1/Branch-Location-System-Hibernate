package org.una.data.dao.modelsDAO;

import org.una.data.dao.DAO;
import org.una.data.repository.Coordinates;

import java.util.HashMap;

public class CDAO extends DAO<Coordinates> {
    @Override
    public boolean add(Coordinates obj) {
        return false;
    }

    @Override
    public boolean erase(Coordinates obj) {
        return false;
    }

    @Override
    public boolean edit(Coordinates obj) {
        return false;
    }

    @Override
    public HashMap<String, Coordinates> getAllObjects() {
        return null;
    }

    @Override
    public Coordinates getSingleObject(String key) {
        return null;
    }
}
