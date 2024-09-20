package dev.haguel;

import dev.haguel.entitiy.Order;
import dev.haguel.service.OrderService;
import dev.haguel.service.ProductReceiptService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App
{
    // To try this program please delete output_example_* directory with files from resources/data directory
    public static void main(String[] args) {
        Path dataPath = Paths.get(".", "src", "main", "resources", "data");
        Path inputPath1 = dataPath.resolve(Paths.get("input_example_1"));
        Path outputPath1 = dataPath.resolve(Paths.get("output_example_1"));
        Path outputPath2 = dataPath.resolve(Paths.get("output_example_2"));
        OrderService orderService = new OrderService(new ProductReceiptService());

        try {
            if(!outputPath1.toFile().exists()) Files.createDirectory(outputPath1);
            if(!outputPath2.toFile().exists()) Files.createDirectory(outputPath2);

            // example #1
            // reads files and then writing them
            List<Order> orders = orderService.readOrders(inputPath1);
            orderService.writeOrders(outputPath1, "order_", ".csv", orders);

            // example #2
            // reads files, separates them by store and writes then
            HashMap<String, Order> separatedOrders = orderService.readOrdersSeparatedByStore(inputPath1);
            for(Map.Entry<String, Order> entry : separatedOrders.entrySet()) {
                String storeName = entry.getKey();
                Order order = entry.getValue();
                Path destFilePath = outputPath2.resolve(Paths.get(storeName + "_order.csv"));

                orderService.writeOrder(destFilePath, order);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
