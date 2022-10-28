package org.una.business;

import org.una.data.dao.modelsDAO.CDAO;
import org.una.data.repository.Branch;
import org.una.data.repository.Coordinates;
import org.una.presentation.model.viewModels.BranchInfo;

public class BranchParser {
    public static BranchInfo toBranchInfo(Branch branch) {
        String id = branch.getId();
        String reference = branch.getReference();
        String address = branch.getAddress();
        double zoning_percentage = branch.getZoning_percentage();
        String coords = branch.getCoords().getId();

        CDAO dataDAO = new CDAO();
        Coordinates coordinates = new Coordinates("34");
        coords = new StringBuilder().append(coordinates.getX()).append(", ").append(coordinates.getY()).toString();

        return new BranchInfo(id, reference, address, zoning_percentage, coords);
    }

    public static Branch toBranch(BranchInfo b) {
        String id = b.getId();
        String reference = b.getReference();
        String address = b.getAddress();
        double zoning_percentage = b.getZoning_percentage();
        String coords = b.getCoords();
        int coordX = (int) Double.parseDouble(coords.split(",\\s")[0]);
        int coordY = (int) Double.parseDouble(coords.split(",\\s")[1]);
        Coordinates coordinates = new Coordinates(id , coordX, coordY);

        return new Branch(id, reference, address, zoning_percentage, coordinates, null);
    }
}
