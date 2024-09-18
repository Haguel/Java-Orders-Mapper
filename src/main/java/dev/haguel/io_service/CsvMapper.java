package dev.haguel.io_service;

import com.google.common.collect.Lists;
import dev.haguel.entitiy.Product;
import dev.haguel.entitiy.ProductReceipt;
import dev.haguel.entitiy.Store;

import java.util.List;

public class CsvMapper implements Mapper {
    @Override
    public ProductReceipt mapToProductReceipt(List<String> values) {
        String storeName = values.get(0);
        String productName = values.get(1);
        Double price = Double.valueOf(values.get(2));
        Double quantity = Double.valueOf(values.get(3));
        Store store = new Store(storeName);
        Product product = new Product(productName, Double.valueOf(price));
        ProductReceipt productReceipt = new ProductReceipt(store, product, quantity);

        return productReceipt;
    }

    @Override
    public List<String> mapFromProductReceipt(ProductReceipt productReceipt) {
        List<String> data = Lists.newArrayList(
                productReceipt.getStore().getName(),
                productReceipt.getProduct().getName(),
                String.valueOf(productReceipt.getProduct().getPrice()),
                String.valueOf(productReceipt.getQuantity())
        );

        return data;
    }
}
