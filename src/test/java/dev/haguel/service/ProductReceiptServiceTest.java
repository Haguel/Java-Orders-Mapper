package dev.haguel.service;

import com.google.common.collect.Lists;
import dev.haguel.entitiy.ProductReceipt;
import dev.haguel.exception.DirectoryNotFoundException;
import dev.haguel.io_service.CsvMapper;
import dev.haguel.io_service.CsvStorage;
import dev.haguel.io_service.Mapper;
import dev.haguel.test_util.DirectoryCleaner;
import dev.haguel.test_util.ExpectedManager;
import dev.haguel.test_util.PropsManager;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProductReceiptServiceTest {
    private ProductReceiptService productReceiptService;
    private Path dataPath;
    CsvStorage csvStorage;
    Mapper mapper;


    @BeforeAll
    public void setUp() {
        productReceiptService = new ProductReceiptService();
        dataPath = Paths.get(".", "src", "test", "java", "dev", "haguel", "data");
        csvStorage = Mockito.spy(new CsvStorage());
        mapper = new CsvMapper();
    }

    @BeforeEach
    public void setUpEach() {
        DirectoryCleaner.deleteFilesInFolders(dataPath.toFile(), "output");
    }

    @Test
    void shouldReadProductReceipts() {
        Path inputPath = dataPath.resolve("input");
        List<List<ProductReceipt>> expected = Lists.newArrayList(
                ExpectedManager.getExpectedFromOrder1(),
                ExpectedManager.getExpectedFromOrder2(),
                ExpectedManager.getExpectedFromOrder3(),
                ExpectedManager.getExpectedFromOrder4(),
                ExpectedManager.getExpectedFromOrder5()
        );
        PropsManager.setCsvReadProps(csvStorage);

        List<List<ProductReceipt>> actual = new ArrayList<>();
        assertDoesNotThrow(() -> {
            actual.addAll(productReceiptService.readProductReceiptsFrom(inputPath, csvStorage, mapper));

            Mockito.verify(csvStorage, Mockito.times(expected.size())).read(
                    any(Path.class), any());
        });


        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionIfDirectoryEmpty() {
        Path inputPath = dataPath.resolve("input_empty");
        PropsManager.setCsvReadProps(csvStorage);

        assertThrows(FileNotFoundException.class, () -> {
            productReceiptService.readProductReceiptsFrom(inputPath, csvStorage, mapper);
        });
    }

    @Test
    void shouldThrowExceptionIfDirectoryNotExist() {
        Path inputPath = dataPath.resolve("non_exist_directory");
        PropsManager.setCsvReadProps(csvStorage);

        assertThrows(DirectoryNotFoundException.class, () -> {
            productReceiptService.readProductReceiptsFrom(inputPath, csvStorage, mapper);
        });
    }

    @Test
    void shouldWriteProductReceipts() {
        Path outputPath = dataPath.resolve(Paths.get("output", "output.csv"));
        List<ProductReceipt> expected = ExpectedManager.getExpectedFromOrder1();
        PropsManager.setCsvWriteProps(csvStorage);

        assertDoesNotThrow(() -> {
            productReceiptService.writeProductReceiptsFrom(outputPath, csvStorage, mapper, expected);

            Mockito.verify(csvStorage, Mockito.times(1)).write(
                    any(), any(), any());
        });

        PropsManager.setCsvReadProps(csvStorage);
        List<ProductReceipt> actual = new ArrayList<>();
        assertDoesNotThrow(() -> {
            actual.addAll(csvStorage.read(outputPath, mapper::mapToProductReceipt));
        });

        assertEquals(expected, actual);
    }
}