package Day6.Part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GuardGallivant {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        List<String> map = Files.readAllLines(Paths.get("D:\\bojan\\aoc\\src\\Day6\\input.txt"));

        System.out.println(countDirections(map));
    }

    public static int countDirections(List<String> map) {
        int rows = map.size();
        int cols = map.getFirst().length();

        int startRow = -1;
        int startCol = -1;
        int direction = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (map.get(i).charAt(j) == '^') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
            if (startRow != -1)
                break;
        }

        Set<String> visited = new HashSet<>();
        int currRow = startRow;
        int currCol = startCol;

        while (true) {
            visited.add(currRow + "," + currCol);

            int nextRow = currRow + DIRECTIONS[direction][0];
            int nextCol = currCol + DIRECTIONS[direction][1];

            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || map.get(nextRow).charAt(nextCol) == '#') {
                direction = (direction + 1) % 4;

                nextRow = currRow + DIRECTIONS[direction][0];
                nextCol = currCol + DIRECTIONS[direction][1];

                if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || map.get(nextRow).charAt(nextCol) == '#')
                    break;
            }

            currRow = nextRow;
            currCol = nextCol;
        }
        return visited.size();
    }
}
