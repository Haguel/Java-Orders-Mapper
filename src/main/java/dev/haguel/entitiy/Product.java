package dev.haguel.entitiy;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Data
public class Product {
    private String name;
    private double price;
}
