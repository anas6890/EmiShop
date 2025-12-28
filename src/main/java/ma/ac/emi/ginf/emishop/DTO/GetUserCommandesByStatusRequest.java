package ma.ac.emi.ginf.emishop.DTO;

import ma.ac.emi.ginf.emishop.Enum.CommandeStatus;

public class GetUserCommandesByStatusRequest {
    private Long userId;
    private CommandeStatus status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public CommandeStatus getStatus() {
        return status;
    }

    public void setStatus(CommandeStatus status) {
        this.status = status;
    }
}
