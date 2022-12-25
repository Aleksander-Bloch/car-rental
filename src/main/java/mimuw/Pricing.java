package mimuw;

public enum Pricing {
    // Enum with all possible pricing options.
    DAY(1),
    WEEK(7),
    MONTH(30);

    private final long days;

    Pricing(long days) {
        this.days = days;
    }

    public long getDays() {
        return days;
    }
}
