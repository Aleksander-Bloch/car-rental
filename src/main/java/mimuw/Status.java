package mimuw;

public enum Status {
    BRONZE(0),
    SILVER(1_000),
    GOLD(10_000),
    PLATINUM(100_000);

    private final int threshold;

    Status(int threshold) {
        this.threshold = threshold;
    }

    public static String assignStatus(int amountSpent) {
        if (amountSpent >= Status.PLATINUM.threshold) {
            return "Platinum";
        } else if (amountSpent >= Status.GOLD.threshold) {
            return "Gold";
        } else if (amountSpent >= Status.SILVER.threshold) {
            return "Silver";
        } else {
            return "Bronze";
        }
    }
}
