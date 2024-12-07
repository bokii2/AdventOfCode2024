package Day6.Part2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

        int guardStartY = -1, guardStartX = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == '^') {
                    guardStartY = i;
                    guardStartX = j;
                    break;
                }
            }
            if (guardStartY != -1) break;
        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int dirIndex = 0;

        // Part1
        int counter = 1;
        int guardY = guardStartY, guardX = guardStartX;

        while (true) {
            int nextY = guardY + directions[dirIndex][0];
            int nextX = guardX + directions[dirIndex][1];

            if (map[nextY][nextX] == '.') {
                map[nextY][nextX] = 'X';
                guardY = nextY;
                guardX = nextX;
                counter++;
            } else if (map[nextY][nextX] == '#') {
                dirIndex = (dirIndex + 1) % directions.length; // Rotate right
            } else if (map[nextY][nextX] == 'E') {
                break;
            } else { // ^ or X
                guardY = nextY;
                guardX = nextX;
            }
        }

        System.out.println(counter);

        // Part2
        int counter2 = 0;

        for (int i = 1; i < map.length - 1; i++) {
            for (int j = 1; j < map[i].length - 1; j++) {
                if (map[i][j] != 'X') continue;

                char[][] mapCopy = new char[map.length][];
                for (int k = 0; k < map.length; k++) {
                    mapCopy[k] = map[k].clone();
                }
                mapCopy[i][j] = '#';

                guardY = guardStartY;
                guardX = guardStartX;
                dirIndex = 0;

                while (true) {
                    int nextY = guardY + directions[dirIndex][0];
                    int nextX = guardX + directions[dirIndex][1];

                    if (mapCopy[nextY][nextX] == '.' || mapCopy[nextY][nextX] == 'X' || mapCopy[nextY][nextX] == '^') {
                        mapCopy[nextY][nextX] = '1';
                        guardY = nextY;
                        guardX = nextX;
                    } else if (mapCopy[nextY][nextX] == '#') {
                        dirIndex = (dirIndex + 1) % directions.length;
                    } else if (mapCopy[nextY][nextX] == 'E') {
                        break;
                    } else if (mapCopy[nextY][nextX] == '4') {
                        counter2++;
                        break;
                    } else {
                        mapCopy[nextY][nextX]++;
                        guardY = nextY;
                        guardX = nextX;
                    }
                }
            }
        }

        System.out.println(counter2);
    }
}
