package dev.haguel.entitiy;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class ProductReceipt {
    private Store store;
    private Product product;
    private Double quantity;

    public void printRow(String settings) {
        System.out.printf(settings, store.getName(), product.getName(), product.getPrice(), quantity);
    }
}
