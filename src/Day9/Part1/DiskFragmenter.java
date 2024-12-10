package Day9.Part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DiskFragmenter {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day9\\input.txt"));

        String str = br.readLine();

        List<Character> disk = initialize(str);

        System.out.println(calculate(disk));
    }

    public static List<Character> initialize(String str) {
        List<Integer> files = new ArrayList<>();
        List<Integer> freeSpaces = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            int value = str.charAt(i) - '0';
            if (i % 2 == 0) {
                files.add(value);
            } else {
                freeSpaces.add(value);
            }
        }

        List<Character> disk = new ArrayList<>();
        int fileId = 0;
        for (int i = 0; i < files.size(); i++) {
            for (int j = 0; j < files.get(i); j++) {
                disk.add((char) ('0' + fileId));
            }
            if (i < freeSpaces.size()) {
                for (int j = 0; j < freeSpaces.get(i); j++) {
                    disk.add('.');
                }
            }
            fileId++;
        }

        compact(disk);

        return disk;
    }

    public static void compact(List<Character> disk) {
        while (true) {
            int rightmostFileIndex = -1;
            int leftmostFreeSpaceIndex = -1;

            for (int i = disk.size() - 1; i >= 0; i--) {
                if (disk.get(i) != '.') {
                    rightmostFileIndex = i;
                    break;
                }
            }

            for (int i = 0; i < disk.size(); i++) {
                if (disk.get(i) == '.') {
                    leftmostFreeSpaceIndex = i;
                    break;
                }
            }

            if (rightmostFileIndex == -1 || leftmostFreeSpaceIndex == -1 || rightmostFileIndex < leftmostFreeSpaceIndex) {
                break;
            }

            disk.set(leftmostFreeSpaceIndex, disk.get(rightmostFileIndex));
            disk.set(rightmostFileIndex, '.');
        }
    }

    public static long calculate(List<Character> disk) {
        long checksum = 0;
        for (int i = 0; i < disk.size(); i++) {
            char c = disk.get(i);
            if (c != '.') {
                checksum += (long) i * (c - '0');
            }
        }

        return checksum;
    }
}
