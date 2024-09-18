package dev.haguel.service;

import dev.haguel.entitiy.ProductReceipt;
import dev.haguel.exception.DirectoryNotFoundException;
import dev.haguel.io_service.Mapper;
import dev.haguel.io_service.Storage;
import dev.haguel.util.StringWithNumberComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductReceiptService {
    public List<List<ProductReceipt>> readProductReceiptsFrom(Path sourceDirectoryPath,
                                                              Storage storage, Mapper mapper) throws IOException {
        File directory = sourceDirectoryPath.toFile();
        List<List<ProductReceipt>> productReceipts = new ArrayList<>();

        if(directory.exists() && directory.isDirectory()) {
            File[] sourceFiles = directory.listFiles();
            if(sourceFiles.length == 0) {
                throw new FileNotFoundException("Absence of source files in provided directory");
            }

            List<File> sortedSourceFiles = Arrays.stream(sourceFiles)
                    .sorted(Comparator.comparing(File::getName, new StringWithNumberComparator()))
                    .collect(Collectors.toList());

            for (File sourceFile : sortedSourceFiles) {
                productReceipts.add(storage.read(sourceFile.toPath(), mapper::mapToProductReceipt));
            }
        } else {
            throw new DirectoryNotFoundException("Absence of provided source directory");
        }

        return productReceipts;
    }

    public void writeProductReceiptsFrom(Path destFilePath, Storage storage,
                                         Mapper mapper, List<ProductReceipt> productReceipts) throws IOException {
        storage.write(destFilePath, productReceipts, mapper::mapFromProductReceipt);
    }
}
