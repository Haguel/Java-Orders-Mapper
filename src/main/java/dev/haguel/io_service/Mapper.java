package dev.haguel.io_service;


import dev.haguel.entitiy.ProductReceipt;

import java.util.List;

public interface Mapper {
    ProductReceipt csvToProductReceipt(List<String> values);
    List<String> productReceiptToCsv(ProductReceipt productReceipt);
}
