package mimuw;

import lombok.Data;

@Data
public class AlertData {
    private String message;
    private String type;
    private boolean seen;

    public AlertData() {
        this.type = AlertType.NONE.getName();
    }

    public void setAlert(AlertType type) {
        this.type = type.getName();
        this.message = type.getMessage();
        this.seen = false;
    }

    public void clearAlert() {
        if (seen) this.type = AlertType.NONE.getName();
        seen = true;
    }
}
