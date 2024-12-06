package Day5.Part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintQueue {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day5\\input.txt"));
        BufferedReader br2 = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day5\\input2.txt"));

        Map<String, Set<String>> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        List<List<String>> list = new ArrayList<>();

        br.lines().forEach(l -> {
            String[] parts = l.split("\\|");

            map.putIfAbsent(parts[0], new HashSet<>());
            map.putIfAbsent(parts[1], new HashSet<>());

            map.get(parts[0]).add(parts[1]);
            set.add(parts[0]);
            set.add(parts[1]);
        });

        br2.lines().forEach(l -> {
            String[] parts = l.split(",");
            list.add(Arrays.asList(parts));
        });

        AtomicInteger correctedTotal = new AtomicInteger();

        list.forEach(l -> {
            if (!check(l, map)) {
                List<String> correctOrder = getCorrectOrder(l, map);
                correctedTotal.addAndGet(getMiddlePage(correctOrder));
            }
        });

        System.out.println(correctedTotal);
    }

    public static boolean check(List<String> list, Map<String, Set<String>> map) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                String before = list.get(i);
                String after = list.get(j);

                if (map.containsKey(before) && map.get(before).contains(after))
                    continue;
                else if (map.containsKey(after) && map.get(after).contains(before))
                    return false;
            }
        }

        return true;
    }

    public static int getMiddlePage(List<String> list) {
        return Integer.parseInt(list.get(list.size() / 2));
    }

    public static List<String> getCorrectOrder(List<String> list, Map<String, Set<String>> map) {
        List<String> sortedPages = new ArrayList<>(list);

        for (int i = 0; i < sortedPages.size(); i++) {
            for (int j = i + 1; j < sortedPages.size(); j++) {
                String before = sortedPages.get(i);
                String after = sortedPages.get(j);

                if (map.containsKey(after) && map.get(after).contains(before)) {
                    sortedPages.set(i, after);
                    sortedPages.set(j, before);
                }
            }
        }

        return sortedPages;
    }
}
