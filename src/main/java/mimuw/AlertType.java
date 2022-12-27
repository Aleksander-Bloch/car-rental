package mimuw;

public enum AlertType {
    NONE("none"),
    RENT_DAY("rentDay"),
    RENT_WEEK("rentWeek"),
    RENT_MONTH("rentMonth"),
    EXTEND("extend"),
    REMOVE("remove"),
    LOGOUT("logout"),
    FAILED("failed");

    private final String name;

    AlertType(String name) {
        this.name = name;
    }

    public String getMessage() {
        return switch (this) {
            case NONE -> null;
            case RENT_DAY -> "You have successfully rented a car for one day.";
            case RENT_WEEK -> "You have successfully rented a car for one week.";
            case RENT_MONTH -> "You have successfully rented a car for one month.";
            case EXTEND -> "The rental has been extended by one day.";
            case REMOVE -> "You have successfully returned a car.";
            case LOGOUT -> "You have been logged out from your TopG rental account.";
            case FAILED -> "Incorrect login information.";
        };
    }

    public String getName() {
        return name;
    }
}
