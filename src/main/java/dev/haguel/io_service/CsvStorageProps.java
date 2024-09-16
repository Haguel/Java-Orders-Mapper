package dev.haguel.io_service;

import com.google.common.base.Joiner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
public class CsvStorageProps {
    private String encoding;
    private String valuesDelimiter;
    private boolean isHeaderLine;
    private List<String> headerLines;

    public CsvStorageProps() {
        encoding = "utf-8";
        valuesDelimiter = ";";
        isHeaderLine = true;
        headerLines = null;
    }

    public String getHeaderLine() {
        return Joiner.on(valuesDelimiter).join(headerLines);
    }

    public boolean isHeaderLine() {
        return isHeaderLine;
    }
}
