package Day1.Part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.abs;

public class HistorianHysteria {
    public static void main(String[] args) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.isEmpty())
                break;

            String[] parts = line.split("\\s+");
            left.add(Integer.parseInt(parts[0]));
            right.add(Integer.parseInt(parts[1]));
        }

        left.sort(Comparator.naturalOrder());
        right.sort(Comparator.naturalOrder());

        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < left.size() && i < right.size(); i++) {
            result.add(abs(right.get(i) - left.get(i)));
        }

        int sum = result.stream().mapToInt(i -> i).sum();

        System.out.println(sum);
    }
}
