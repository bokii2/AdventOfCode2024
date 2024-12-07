package Day7.Part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class BridgeRepair {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day7\\input.txt"));
        Map<Long, List<Long>> map = new HashMap<>();

        br.lines().forEach(l -> {
            String[] parts = l.split(":");
            List<Long> subparts = Arrays
                    .stream(parts[1].trim().split("\\s+"))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            map.put(Long.parseLong(parts[0]), subparts);
        });

        System.out.println(calculate(map));
    }

    public static long calculate(Map<Long, List<Long>> map){
        long total = 0;

        for (Map.Entry<Long, List<Long>> entry : map.entrySet()) {
            List<Long> numbers = entry.getValue();

            int numOperators = numbers.size() - 1;
            List<String[]> operatorCombinations = generateOperatorCombinations(numOperators);

            boolean matches = operatorCombinations.stream()
                    .anyMatch(operators -> evaluateExpression(numbers, operators) == entry.getKey());

            if (matches) {
                total += entry.getKey();
            }
        }
        return total;
    }

    public static List<String[]> generateOperatorCombinations(long numOperators) {
        List<String[]> combinations = new ArrayList<>();
        int totalCombinations = (int) Math.pow(2, numOperators);

        for (int i = 0; i < totalCombinations; i++) {
            String[] operators = new String[(int) numOperators];
            for (int j = 0; j < numOperators; j++) {
                operators[j] = (i & (1 << j)) == 0 ? "+" : "*";
            }
            combinations.add(operators);
        }

        return combinations;
    }

    public static long evaluateExpression(List<Long> numbers, String[] operators) {
        long result = numbers.getFirst();

        for (int i = 0; i < operators.length; i++) {
            String operator = operators[i];
            if ("+".equals(operator)) {
                result += numbers.get(i + 1);
            } else if ("*".equals(operator)) {
                result *= numbers.get(i + 1);
            }
        }

        return result;
    }
}
