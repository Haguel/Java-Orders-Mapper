package dev.haguel.io_service;


import dev.haguel.entitiy.ProductReceipt;

import java.util.List;

public interface Mapper {
    ProductReceipt mapToProductReceipt(List<String> values);
    List<String> mapFromProductReceipt(ProductReceipt productReceipt);
}
