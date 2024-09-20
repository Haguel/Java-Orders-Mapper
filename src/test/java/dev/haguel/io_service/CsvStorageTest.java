package dev.haguel.io_service;

import dev.haguel.entitiy.ProductReceipt;
import dev.haguel._test_util.DirectoryCleaner;
import dev.haguel._test_util.ExpectedManager;
import dev.haguel._test_util.PropsManager;
import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CsvStorageTest {
    private CsvStorage csvStorage;
    private Path dataPath;

    @BeforeAll
    public void setUp() {
        csvStorage = new CsvStorage();
        dataPath = Paths.get(".", "src", "test", "java", "dev", "haguel", "data");
    }

    @BeforeEach
    public void setUpEach() {
        DirectoryCleaner.deleteFilesInFolders(dataPath.toFile(), "output");
    }

    @Test
    void shouldReadFromBufferedReader() {
        Path inputPath = dataPath.resolve(Paths.get("input", "order_1.csv"));
        Mapper mapper = new CsvMapper();
        List<ProductReceipt> expected = ExpectedManager.getExpectedFromOrder1();
        PropsManager.setCsvReadProps(csvStorage);

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = Files.newBufferedReader(inputPath);
        } catch (IOException exception) {
            System.out.println("Invalid test input path");

            fail();
        }

        BufferedReader finalBufferedReader = bufferedReader;
        List<ProductReceipt> actual = new ArrayList<>();
        assertDoesNotThrow(() -> {
            actual.addAll(csvStorage.read(finalBufferedReader, mapper::mapToProductReceipt));
        });

        assertEquals(expected, actual);
    }

    @Test
    void shouldReadFromPath() {
        Path inputPath = dataPath.resolve(Paths.get("input", "order_2.csv"));
        Mapper mapper = new CsvMapper();
        List<ProductReceipt> expected = ExpectedManager.getExpectedFromOrder2();
        PropsManager.setCsvReadProps(csvStorage);

        if(!inputPath.toFile().exists()) {
            System.out.println("Invalid test input path");

            fail();
        }

        List<ProductReceipt> actual = new ArrayList<>();
        assertDoesNotThrow(() -> {
            actual.addAll(csvStorage.read(inputPath, mapper::mapToProductReceipt));
        });

        assertEquals(expected, actual);
    }

    @Test
    void shouldWrite() {
        Path outputPath = dataPath.resolve(Paths.get("output", "output.csv"));
        List<ProductReceipt> expected = ExpectedManager.getExpectedFromOrder3();
        Mapper mapper = new CsvMapper();
        PropsManager.setCsvWriteProps(csvStorage);

        assertDoesNotThrow(() -> {
            csvStorage.write(outputPath, expected, mapper::mapFromProductReceipt);
        });


        PropsManager.setCsvReadProps(csvStorage);
        List<ProductReceipt> actual = new ArrayList<>();
        assertDoesNotThrow(() -> {
            actual.addAll(csvStorage.read(outputPath, mapper::mapToProductReceipt));
        });

        assertEquals(expected, actual);
    }
}