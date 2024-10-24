import lombok.Getter;

import java.time.LocalDate;


@Getter
public class Contract {
    private Client client;
    private Item item;
    private double amount;
    private LocalDate redemptionWithoutInterest;
    private LocalDate nonRedemptionPeriod;

    public Contract(Client client, Item item, double amount, LocalDate redemptionWithoutInterest, LocalDate nonRedemptionPeriod) {
        this.client = client;
        this.item = item;
        this.amount = amount;
        this.redemptionWithoutInterest = redemptionWithoutInterest;
        this.nonRedemptionPeriod = nonRedemptionPeriod;
    }
}
