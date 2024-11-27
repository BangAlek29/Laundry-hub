package kasir;

import dao.LayananDAO;
import model.LayananModel;
import java.util.ArrayList;

public class LayananController {
    private final LayananDAO layananDAO;

    public LayananController() {
        this.layananDAO = new LayananDAO();
    }

    public ArrayList<LayananModel> getAllLayanan() {
        return layananDAO.getAllLayanan();
    }

    public void updateLayanan(LayananModel layanan) throws Exception {
        layananDAO.updateLayanan(layanan);
    }

    public void addLayanan(LayananModel layanan) throws Exception {
        layananDAO.addLayanan(layanan);
    }
}
