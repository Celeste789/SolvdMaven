package company.enums;

public enum TypesOfDiscount {
    ANTIQUITY_DISCOUNT(10),
    DISCOUNT_PER_APP(2),
    DISCOUNT_PER_DAY(0);

    int condition;

    public int getCondition() {
        return condition;
    }

    TypesOfDiscount(int i) {
    }
}
