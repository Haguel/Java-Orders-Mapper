package dev.haguel.io_service;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;

@AllArgsConstructor
@Getter
@Setter
public class CsvStorage implements Storage {
    private CsvStorageProps props;

    public CsvStorage() {
        props = new CsvStorageProps();
    }

    @Override
    public <T> List<T> read(BufferedReader bufferedReader, Function<List<String>, T> mapper) throws IOException {
        List<T> result = new ArrayList<>();
        String valuesDelimiter = props.getValuesDelimiter();
        boolean headerLine = props.isHeaderLine();

        if(headerLine) bufferedReader.readLine();

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            List<String> values = Splitter.on(valuesDelimiter).splitToList(line);

            result.add(mapper.apply(values));
        }

        return result;
    }

    @Override
    public <T> List<T> read(Path source, Function<List<String>, T> mapper) throws IOException {
        List<T> result = new ArrayList<>();
        String valuesDelimiter = props.getValuesDelimiter();
        boolean headerLine = props.isHeaderLine();
        BufferedReader bufferedReader = Files.newBufferedReader(source);

        if(headerLine) bufferedReader.readLine();

        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            List<String> values = Splitter.on(valuesDelimiter).splitToList(line);

            result.add(mapper.apply(values));
        }

        bufferedReader.close();

        return result;
    }

    @Override
    public <T> void write(Path dest, List<T> values, Function<T, List<String>> mapper) throws IOException {
        String valuesDelimiter = props.getValuesDelimiter();
        String headerLine = props.getHeaderLine();
        BufferedWriter bufferedWriter = Files.newBufferedWriter(dest);

        bufferedWriter.write(headerLine + "\n");

        ListIterator<T> valuesIterator = values.listIterator();
        while (valuesIterator.hasNext()) {
            List<String> dataList = mapper.apply(valuesIterator.next());
            String data = Joiner.on(valuesDelimiter).join(dataList) + ";";

            if(valuesIterator.hasNext()) data += "\n";

            bufferedWriter.write(data);
        }

        bufferedWriter.close();
    }
}
