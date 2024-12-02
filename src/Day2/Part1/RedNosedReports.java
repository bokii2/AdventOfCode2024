package Day2.Part1;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.abs;

public class RedNosedReports {
    public static void main(String[] args) throws FileNotFoundException {
        InputStream is = new FileInputStream("D:\\fakultet\\AdventOfCode2024\\src\\Day2\\input.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        AtomicInteger count = new AtomicInteger();
        br.lines().forEach(l -> {
            if (check(l))
                count.getAndIncrement();
        });

        System.out.println(count);
    }

    public static boolean check(String line) {
        int[] parts = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < parts.length - 1; i++) {
            int diff = parts[i] - parts[i + 1];

            if (abs(diff) > 3)
                return false;

            if (diff >= 0) {
                decreasing = false;
            }

            if (diff <= 0) {
                increasing = false;
            }
        }

        return increasing || decreasing;
    }
}
