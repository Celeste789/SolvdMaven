package company.enums;

public enum PayingDiscount {
    CASH(250),
    DEBIT_CARD(100),
    CREDIT_CARD(0);

    private int discount;

    PayingDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }
}
