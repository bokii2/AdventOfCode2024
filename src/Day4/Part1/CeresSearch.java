package Day4.Part1;

import java.io.*;
import java.util.List;

public class CeresSearch {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day4\\input.txt"));
        List<String> grid = br.lines().toList();
        System.out.println(countWordOccurrences(grid));
    }

    private static int countWordOccurrences(List<String> grid) {
        int rows = grid.size();
        int cols = grid.getFirst().length();
        int[][] directions = {
                {0, 1},
                {0, -1},
                {1, 0},
                {1, 1},
                {1, -1},
                {-1, 0},
                {-1, 1},
                {-1, -1}
        };

        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int[] direction : directions) {
                    if (matchesWord(grid, row, col, direction[0], direction[1])) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean matchesWord(List<String> grid, int startRow, int startCol, int dRow, int dCol) {
        String word = "XMAS";
        int rows = grid.size();
        int cols = grid.getFirst().length();

        for (int i = 0; i < word.length(); i++) {
            int newRow = startRow + i * dRow;
            int newCol = startCol + i * dCol;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) return false;
            if (grid.get(newRow).charAt(newCol) != word.charAt(i)) return false;
        }
        return true;
    }
}
