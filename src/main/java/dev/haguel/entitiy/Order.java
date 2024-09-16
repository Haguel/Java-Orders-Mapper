package dev.haguel.entitiy;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Order {
    private List<ProductReceipt> productReceipts;

    public Order(List<ProductReceipt> productReceipts) {
        this.productReceipts = new ArrayList<>(productReceipts);
    }
    public Order(ProductReceipt... productReceipts) {
        this.productReceipts = new ArrayList<>(List.of(productReceipts));
    }

    public Order addOrderValues(Order order) {
        productReceipts.addAll(order.getProductReceipts());

        return this;
    }

    public void printTable() {
        String rowSettings = "%-12s %-12s %-12s %-12s\n";
        System.out.printf(rowSettings, "Store", "Product", "Price", "Quantity");
        for(ProductReceipt productReceipt : productReceipts) {
            productReceipt.printRow(rowSettings);
        }
    }
}
