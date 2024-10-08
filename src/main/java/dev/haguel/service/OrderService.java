package dev.haguel.service;

import com.google.common.collect.Lists;
import dev.haguel.entitiy.Order;
import dev.haguel.entitiy.ProductReceipt;
import dev.haguel.io_service.*;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
public class OrderService {
    private final ProductReceiptService productReceiptService;

    public List<Order> readOrders(Path source) throws IOException {
        CsvStorageProps csvStorageProps = CsvStorageProps.builder()
                .encoding("utf-8")
                .valuesDelimiter(";")
                .isHeaderLine(true)
                .build();
        Storage storage = new CsvStorage(csvStorageProps);
        Mapper mapper = new CsvMapper();
        List<List<ProductReceipt>> wrappedProductReceipts = productReceiptService.readProductReceiptsFrom(source, storage, mapper);
        List<Order> orders = new ArrayList<>();

        for (List<ProductReceipt> productReceipts : wrappedProductReceipts) {
            orders.add(new Order(productReceipts));
        }

        return orders;
    }

    public HashMap<String, Order> readOrdersSeparatedByStore(Path sourceDirectoryPath) throws IOException {
        CsvStorageProps csvStorageProps = CsvStorageProps.builder()
                .encoding("utf-8")
                .valuesDelimiter(";")
                .isHeaderLine(true)
                .build();
        Storage storage = new CsvStorage(csvStorageProps);
        Mapper mapper = new CsvMapper();
        List<List<ProductReceipt>> wrappedProductReceipts = productReceiptService.readProductReceiptsFrom(sourceDirectoryPath, storage, mapper);
        List<ProductReceipt> combinedProductsReceipts = new ArrayList<>();

        for (List<ProductReceipt> productReceipts : wrappedProductReceipts) {
            combinedProductsReceipts.addAll(productReceipts);
        }

        HashMap<String, Order> separatedOrders = getOrdersSeparatedByStore(combinedProductsReceipts);

        return separatedOrders;
    }

    public void writeOrders(Path destDirectoryPath, String nameFileRule, String extension, List<Order> orders) throws IOException {
        CsvStorageProps csvStorageProps = CsvStorageProps.builder()
                .encoding("utf-8")
                .valuesDelimiter(";")
                .headerLines(Lists.newArrayList("STORE", "PRODUCT", "PRICE", "QUANTITY"))
                .build();
        Storage storage = new CsvStorage(csvStorageProps);
        Mapper mapper = new CsvMapper();

        int counter = 1;
        for (Order order : orders) {
            Path orderPath = destDirectoryPath.resolve(Paths.get(nameFileRule + counter + extension));

            productReceiptService.writeProductReceiptsFrom(orderPath, storage, mapper, order.getProductReceipts());

            counter++;
        }
    }

    public void writeOrder(Path destFilePath, Order order) throws IOException {
        CsvStorageProps csvStorageProps = CsvStorageProps.builder()
                .encoding("utf-8")
                .valuesDelimiter(";")
                .headerLines(Lists.newArrayList("STORE", "PRODUCT", "PRICE", "QUANTITY"))
                .build();
        Storage storage = new CsvStorage(csvStorageProps);
        Mapper mapper = new CsvMapper();

        productReceiptService.writeProductReceiptsFrom(destFilePath, storage, mapper, order.getProductReceipts());
    }

    private HashMap<String, Order> getOrdersSeparatedByStore(List<ProductReceipt> productReceipts) {
        HashMap<String, Order> separatedOrders = new HashMap<>();

        for (ProductReceipt productReceipt : productReceipts) {
            separatedOrders.merge(
                    productReceipt.getStore().getName(),
                    new Order(productReceipt),
                    Order::addOrderValues);
        }

        return separatedOrders;
    }
}
