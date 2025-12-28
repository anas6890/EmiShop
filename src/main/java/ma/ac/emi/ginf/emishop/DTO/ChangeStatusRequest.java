package ma.ac.emi.ginf.emishop.DTO;

import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;

public class ChangeStatusRequest {
    private CommandeStatus newStatus;

    public CommandeStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(CommandeStatus newStatus) {
        this.newStatus = newStatus;
    }
}
