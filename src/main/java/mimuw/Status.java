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

    public static int applyDiscount(String status, int rate) {
        double discount =  switch (status) {
            case "Platinum" -> 0.5;
            case "Gold" -> 0.25;
            case "Silver" -> 0.1;
            default -> 0.0;
        };

        // Return discounted rate rounded down to tenth place.
        return (int) ((1 - discount) * rate) / 10 * 10;
    }
}
