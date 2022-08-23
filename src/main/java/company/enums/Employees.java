package company.enums;

public enum Employees {
    ACCOUNTANT(250),
    DEVELOPER(300),
    SELLER(200);

    int salary;

    Employees(int salary) {
        this.salary = salary;
    }
}
