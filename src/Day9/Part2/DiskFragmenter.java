//package Day9.Part2;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DiskFragmenter {
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day9\\input.txt"));
//
//        String str = br.readLine();
//
//        List<Character> disk = initialize(str);
//
//        System.out.println(calculate(disk));
//    }
//
//    public static List<Character> initialize(String str) {
//        List<Integer> files = new ArrayList<>();
//        List<Integer> freeSpaces = new ArrayList<>();
//
//        for (int i = 0; i < str.length(); i++) {
//            int value = str.charAt(i) - '0';
//            if (i % 2 == 0) {
//                files.add(value);
//            } else {
//                freeSpaces.add(value);
//            }
//        }
//
//        List<Character> disk = new ArrayList<>();
//        int fileId = 0;
//        for (int i = 0; i < files.size(); i++) {
//            for (int j = 0; j < files.get(i); j++) {
//                disk.add((char) ('0' + fileId));
//            }
//            if (i < freeSpaces.size()) {
//                for (int j = 0; j < freeSpaces.get(i); j++) {
//                    disk.add('.');
//                }
//            }
//            fileId++;
//        }
//
//        compactWholeFiles(disk);
//        return disk;
//    }
//
//    public static void compactWholeFiles(List<Character> disk) {
//        for (int fileId = getMaxFileId(disk); fileId >= 0; fileId--) {
//            List<Integer> filePositions = findFilePositions(disk, fileId);
//            if (filePositions.isEmpty()) continue;
//
//            int fileLength = filePositions.size();
//            int leftmostPos = findLeftmostFreeSpace(disk, fileLength);
//
//            if (leftmostPos != -1) {
//                for (int i = 0; i < fileLength; i++) {
//                    disk.set(leftmostPos + i, (char) ('0' + fileId));
//                    disk.set(filePositions.get(i), '.');
//                }
//            }
//        }
//    }
//
//    private static int getMaxFileId(List<Character> disk) {
//        return disk.stream()
//                .filter(c -> c != '.')
//                .mapToInt(c -> c - '0')
//                .max()
//                .orElse(-1);
//    }
//
//    private static List<Integer> findFilePositions(List<Character> disk, int fileId) {
//        List<Integer> positions = new ArrayList<>();
//        for (int i = 0; i < disk.size(); i++) {
//            if (disk.get(i) == (char) ('0' + fileId)) {
//                positions.add(i);
//            }
//        }
//        return positions;
//    }
//
//    private static int findLeftmostFreeSpace(List<Character> disk, int requiredLength) {
//        for (int i = 0; i <= disk.size() - requiredLength; i++) {
//            boolean canFit = true;
//            for (int j = 0; j < requiredLength; j++) {
//                if (disk.get(i + j) != '.') {
//                    canFit = false;
//                    break;
//                }
//            }
//            if (canFit) return i;
//        }
//        return -1;
//    }
//
//    public static long calculate(List<Character> disk) {
//        long checksum = 0;
//        for (int i = 0; i < disk.size(); i++) {
//            char c = disk.get(i);
//            if (c != '.') {
//                checksum += (long) i * (c - '0');
//            }
//        }
//
//        return checksum;
//    }
//}

package Day9.Part2;

import java.io.BufferedReader;
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

        compactWholeFiles(disk);
        return disk;
    }

    public static void compactWholeFiles(List<Character> disk) {
        for (int fileId = getMaxFileId(disk); fileId >= 0; fileId--) {
            List<Integer> filePositions = findFilePositions(disk, fileId);
            if (filePositions.isEmpty()) continue;

            int fileLength = filePositions.size();

            int rightmostPos = filePositions.getLast();

            for (int start = 0; start < rightmostPos; start++) {
                boolean canFit = true;
                for (int i = start; i < start + fileLength; i++) {
                    if (i >= disk.size() || disk.get(i) != '.') {
                        canFit = false;
                        break;
                    }
                }

                if (canFit) {
                    for (int i = 0; i < fileLength; i++) {
                        disk.set(start + i, (char) ('0' + fileId));
                        disk.set(filePositions.get(i), '.');
                    }
                    break;
                }
            }
        }
    }

    private static int getMaxFileId(List<Character> disk) {
        return disk.stream()
                .filter(c -> c != '.')
                .mapToInt(c -> c - '0')
                .max()
                .orElse(-1);
    }

    private static List<Integer> findFilePositions(List<Character> disk, int fileId) {
        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i) == (char) ('0' + fileId)) {
                positions.add(i);
            }
        }
        return positions;
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