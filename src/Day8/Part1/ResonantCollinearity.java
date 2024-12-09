package Day8.Part1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ResonantCollinearity {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day8\\input.txt"));

        List<String> list = br.lines().toList();

        br.close();

        int count = countUniqueAntinodes(list);
        System.out.println(count);
    }

    public static int countUniqueAntinodes(List<String> grid) {
        int rows = grid.size();
        int cols = grid.getFirst().length();
        Map<Character, List<int[]>> antennas = new HashMap<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char cell = grid.get(i).charAt(j);
                if (Character.isLetterOrDigit(cell)) {
                    antennas
                            .computeIfAbsent(cell, k -> new ArrayList<>())
                            .add(new int[]{i, j});
                }
            }
        }

        Set<String> antinodes = new HashSet<>();

        for (Map.Entry<Character, List<int[]>> entry : antennas.entrySet()) {
            List<int[]> positions = entry.getValue();
            if (positions.size() >= 2) {
                for (int i = 0; i < positions.size(); i++) {
                    int[] pos1 = positions.get(i);
                    for (int j = i + 1; j < positions.size(); j++) {
                        int[] pos2 = positions.get(j);

                        int deltaY = pos1[0] - pos2[0];
                        int deltaX = pos1[1] - pos2[1];

                        if (cordInMap(pos1[0] + deltaY, pos1[1] + deltaX, rows, cols)) {
                            antinodes.add((pos1[0] + deltaY) + "," + (pos1[1] + deltaX));
                        }
                        if (cordInMap(pos2[0] - deltaY, pos2[1] - deltaX, rows, cols)) {
                            antinodes.add((pos2[0] - deltaY) + "," + (pos2[1] - deltaX));
                        }
                    }
                }
            }
        }

        return antinodes.size();
    }

    public static boolean cordInMap(int y, int x, int height, int width) {
        return y >= 0 && y < height && x >= 0 && x < width;
    }
}
