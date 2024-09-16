package dev.haguel.io_service;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public interface Storage {
    <T> List<T> read(BufferedReader bufferedReader, Function<List<String>, T> mapper) throws IOException;
    <T> List<T> read(Path source, Function<List<String>, T> mapper) throws IOException;
    <T> void write(Path dest, List<T> value, Function<T, List<String>> mapper) throws IOException;
}
