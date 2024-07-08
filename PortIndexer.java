package com.example.portindexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PortIndexer {

    public static List<List<Integer>> parseIndexes(String[] indexes) {
        List<List<Integer>> result = new ArrayList<>();
        for (String index : indexes) {
            List<Integer> sequence = new ArrayList<>();
            String[] parts = index.split(",");
            for (String part : parts) {
                if (part.contains("-")) {
                    String[] range = part.split("-");
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);
                    for (int i = start; i <= end; i++) {
                        sequence.add(i);
                    }
                } else {
                    sequence.add(Integer.parseInt(part));
                }
            }
            result.add(sequence);
        }
        return result;
    }

    public static Set<List<Integer>> generateUniqueCombinations(List<List<Integer>> sequences) {
        Set<List<Integer>> combinations = new HashSet<>();
        generateCombinations(sequences, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private static void generateCombinations(List<List<Integer>> sequences, int depth, List<Integer> current, Set<List<Integer>> combinations) {
        if (depth == sequences.size()) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (Integer number : sequences.get(depth)) {
            current.add(number);
            generateCombinations(sequences, depth + 1, current, combinations);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        String[] indexes = {"1,3-5", "2", "3-4"};
        List<List<Integer>> parsedIndexes = parseIndexes(indexes);
        Set<List<Integer>> uniqueCombinations = generateUniqueCombinations(parsedIndexes);

        for (List<Integer> combination : uniqueCombinations) {
            System.out.println(combination);
        }
    }
}
