package edu.stanislav.bigdata.lab1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Lab1 {

    public static void main(String[] args) throws IOException {
        Map<String, List<Integer>> result = shuffle(read(Arrays.asList("file1.txt", "file2.txt")));
        result.forEach((k, v) -> v.forEach((vv) -> System.out.println(k + " " + vv)));
    }

    private static List<String> read(List<String> fileNames) throws IOException {
        List<String> all = new ArrayList<>();
        for (String fileName: fileNames)
            all.addAll(Files.readAllLines(Paths.get(fileName)));
        return all;
    }

    private static Map<String, List<Integer>> shuffle(List<String> input) {
        return input
                .stream()
                .collect(
                        Collectors.groupingBy(
                                s -> s.split(" ")[0],
                                TreeMap::new,
                                Collectors.mapping(
                                        s -> Integer.parseInt(s.split(" ")[1]),
                                        Collectors.toList()
                                )
                        )
                );
    }
}
