package ma.ac.emi.ginf.emishop.DTO;

import ma.ac.emi.ginf.emishop.Enum.PanierStatus;

public class ChangePanierStatusRequest {
    private PanierStatus panierStatus;

    public PanierStatus getPanierStatus() {
        return panierStatus;
    }

    public void setPanierStatus(PanierStatus panierStatus) {
        this.panierStatus = panierStatus;
    }
}
