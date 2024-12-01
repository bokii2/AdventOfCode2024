package Day1.Part2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class HistorianHysteria {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty())
                break;

            String[] parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < left.size(); i++) {
            int count = 0;
            for (int j = 0; j < right.size(); j++) {
                if (left.get(i).equals(right.get(j)))
                    count++;
            }
            result.add(left.get(i) * count);
        }

        int sum = result.stream().mapToInt(i -> i).sum();

        System.out.println(sum);
    }
}
