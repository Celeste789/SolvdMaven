package company.enums;

public enum DiscountPerDay {
    MONDAY(200),
    TUESDAY(250),
    WEDNESDAY(300),
    THURSDAY(250),
    FRIDAY(200),
    WEEKEND(0);

    private int discount;

    DiscountPerDay(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
