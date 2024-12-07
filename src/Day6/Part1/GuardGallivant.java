package Day6.Part1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GuardGallivant {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fakultet\\AdventOfCode2024\\src\\Day6\\input.txt"));
        List<char[]> inputMap = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            char[] lineArray = new char[line.length() + 2];
            Arrays.fill(lineArray, 'E');
            for (int i = 0; i < line.length(); i++) {
                lineArray[i + 1] = line.charAt(i);
            }
            inputMap.add(lineArray);
        }
        br.close();

        char[] emptyLine = new char[inputMap.getFirst().length];
        Arrays.fill(emptyLine, 'E');
        inputMap.addFirst(emptyLine);
        inputMap.add(emptyLine);

        char[][] map = inputMap.toArray(new char[0][]);

        int guardY = -1, guardX = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '^') {
                    guardY = i;
                    guardX = j;
                    break;
                }
            }
            if (guardY != -1) break;
        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int dirIndex = 0;
        int counter = 1;

        while (true) {
            int nextY = guardY + directions[dirIndex][0];
            int nextX = guardX + directions[dirIndex][1];

            if (map[nextY][nextX] == '.') {
                map[nextY][nextX] = 'X';
                guardY = nextY;
                guardX = nextX;
                counter++;
            } else if (map[nextY][nextX] == '#') {
                dirIndex = (dirIndex + 1) % directions.length;
            } else if (map[nextY][nextX] == 'E') {
                break;
            } else {
                guardY = nextY;
                guardX = nextX;
            }
        }

        System.out.println(counter);
    }

}
