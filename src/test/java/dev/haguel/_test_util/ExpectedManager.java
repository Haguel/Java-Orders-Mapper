package dev.haguel._test_util;

import com.google.common.collect.Lists;
import dev.haguel.entitiy.Product;
import dev.haguel.entitiy.ProductReceipt;
import dev.haguel.entitiy.Store;

import java.util.List;

public class ExpectedManager {
    public static List<ProductReceipt> getExpectedFromOrder1() {
        return Lists.newArrayList(
                new ProductReceipt(new Store("Silpo"), new Product("Rice", 27.6), 24.0),
                new ProductReceipt(new Store("Silpo"), new Product("Flour", 25.93), 99.0),
                new ProductReceipt(new Store("Silpo"), new Product("Sugar", 21.06), 107.0),
                new ProductReceipt(new Store("Metro"), new Product("Flour", 25.31), 170.0),
                new ProductReceipt(new Store("Metro"), new Product("Sugar", 24.65), 120.0),
                new ProductReceipt(new Store("Metro"), new Product("Buckwheat", 31.33), 80.0)
        );
    }

    public static List<ProductReceipt> getExpectedFromOrder2() {
        return Lists.newArrayList(
                new ProductReceipt(new Store("Silpo"), new Product("Flour", 23.26), 107.0),
                new ProductReceipt(new Store("Silpo"), new Product("Rice", 26.35), 99.0),
                new ProductReceipt(new Store("Silpo"), new Product("Sugar", 20.92), 24.0),
                new ProductReceipt(new Store("Metro"), new Product("Milk", 20.47), 170.0),
                new ProductReceipt(new Store("Metro"), new Product("Flour", 24.61), 80.0),
                new ProductReceipt(new Store("Metro"), new Product("Rice", 26.62), 120.0)
        );
    }

    public static List<ProductReceipt> getExpectedFromOrder3() {
        return Lists.newArrayList(
                new ProductReceipt(new Store("Novus"), new Product("Flour", 24.14), 99.0),
                new ProductReceipt(new Store("Novus"), new Product("Buckwheat", 30.74), 170.0),
                new ProductReceipt(new Store("Novus"), new Product("Milk", 24.19), 24.0),
                new ProductReceipt(new Store("Ashan"), new Product("Rice", 23.29), 107.0),
                new ProductReceipt(new Store("Ashan"), new Product("Buckwheat", 31.57), 120.0),
                new ProductReceipt(new Store("Ashan"), new Product("Flour", 27.22), 80.0)
        );
    }

    public static List<ProductReceipt> getExpectedFromOrder4() {
        return Lists.newArrayList(
                new ProductReceipt(new Store("ATB"), new Product("Sugar", 20.31), 120.0),
                new ProductReceipt(new Store("ATB"), new Product("Rice", 25.7), 80.0),
                new ProductReceipt(new Store("ATB"), new Product("Flour", 26.85), 170.0),
                new ProductReceipt(new Store("Metro"), new Product("Sugar", 21.45), 99.0),
                new ProductReceipt(new Store("Metro"), new Product("Flour", 25.8), 24.0),
                new ProductReceipt(new Store("Metro"), new Product("Rice", 22.29), 107.0)
        );
    }

    public static List<ProductReceipt> getExpectedFromOrder5() {
        return Lists.newArrayList(
                new ProductReceipt(new Store("ATB"), new Product("Sugar", 21.95), 99.0),
                new ProductReceipt(new Store("ATB"), new Product("Milk", 19.57), 170.0),
                new ProductReceipt(new Store("ATB"), new Product("Buckwheat", 30.94), 80.0),
                new ProductReceipt(new Store("Metro"), new Product("Rice", 27.26), 107.0),
                new ProductReceipt(new Store("Metro"), new Product("Buckwheat", 31.78), 120.0),
                new ProductReceipt(new Store("Metro"), new Product("Sugar", 22.78), 24.0)
        );
    }
}
