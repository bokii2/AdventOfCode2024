package Day4.Part2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

public class CeresSearch {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day4\\input.txt"));
        List<String> grid = br.lines().toList();
        System.out.println(countXShapeOccurrences(grid));
    }

    private static int countXShapeOccurrences(List<String> grid) {
        int rows = grid.size();
        int cols = grid.getFirst().length();
        int count = 0;

        for (int row = 1; row < rows - 1; row++) {
            for (int col = 1; col < cols - 1; col++) {
                if (matchesXShape(grid, row, col)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static boolean matchesXShape(List<String> grid, int centerRow, int centerCol) {
        int topLeftRow = centerRow - 1;
        int topLeftCol = centerCol - 1;
        int topRightRow = centerRow - 1;
        int topRightCol = centerCol + 1;
        int bottomLeftRow = centerRow + 1;
        int bottomLeftCol = centerCol - 1;
        int bottomRightRow = centerRow + 1;
        int bottomRightCol = centerCol + 1;

        String wordOne = "" +
                grid.get(topLeftRow).charAt(topLeftCol) +
                grid.get(centerRow).charAt(centerCol) +
                grid.get(bottomRightRow).charAt(bottomRightCol);

        String wordTwo = "" +
                grid.get(topRightRow).charAt(topRightCol) +
                grid.get(centerRow).charAt(centerCol) +
                grid.get(bottomLeftRow).charAt(bottomLeftCol);

        return VALID_WORDS.contains(wordOne) && VALID_WORDS.contains(wordTwo);
    }

    private static final List<String> VALID_WORDS = Arrays.asList("MAS", "SAM");

}
