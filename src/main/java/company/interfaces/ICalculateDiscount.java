package company.interfaces;

import company.enums.TypesOfDiscount;
import company.human.Client;

public interface ICalculateDiscount {
    double calculateDiscount(Client client, double discountOfTheDay, TypesOfDiscount typesOfDiscount);
}
