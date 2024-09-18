package dev.haguel.test_util;

import com.google.common.collect.Lists;
import dev.haguel.io_service.CsvStorage;
import dev.haguel.io_service.CsvStorageProps;

public class PropsManager {
    public static void setCsvReadProps(CsvStorage storage) {
        CsvStorageProps csvStorageProps = CsvStorageProps.builder()
                .encoding("utf-8")
                .valuesDelimiter(";")
                .isHeaderLine(true)
                .build();
        storage.setProps(csvStorageProps);
    }

    public static void setCsvWriteProps(CsvStorage storage) {
        CsvStorageProps csvStorageProps = CsvStorageProps.builder()
                .encoding("utf-8")
                .valuesDelimiter(";")
                .headerLines(Lists.newArrayList("STORE", "PRODUCT", "PRICE", "QUANTITY"))
                .build();
        storage.setProps(csvStorageProps);
    }
}
