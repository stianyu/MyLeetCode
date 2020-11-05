package _18.bfs.SearchMinDepth;

import java.util.*;

public class Solution {
    static int[][] positions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int m;
    static int n;
    static int tx;
    static int ty;
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String[] strings = in.nextLine().split(" ");
            m = Integer.parseInt(strings[0]);
            n = Integer.parseInt(strings[1]);
            char[][] map = new char[m][n];
            for (int i = 0; i < m; i++) {
                map[i] = in.nextLine().toCharArray();
            }

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 'T') {
                        tx = i;
                        ty = j;
                        map[i][j] = '0';
                    }
                }
            }

            int minDeep = Integer.MAX_VALUE;
            List<int[]> lists = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 'X') {
                        int temp = bfs(map, i, j);
                        if (temp < minDeep) {
                            minDeep = temp;
                            lists.clear();
                            lists.add(new int[]{i, j});
                        } else if (temp == minDeep) {
                            lists.add(new int[]{i, j});
                        }
                    }
                }
            }

            System.out.println(minDeep);
            for (int i = 0; i < lists.size(); i++) {
                if (i != lists.size() - 1) {
                    System.out.print(lists.get(i)[0] + " " + lists.get(i)[1] + " ");
                } else {
                    System.out.println(lists.get(i)[0] + " " + lists.get(i)[1]);
                }
            }
        }
    }

    private static int bfs(char[][] map, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        int[][] shortestPath = new int[m][n];
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++) {
                shortestPath[k][l] = -1;
            }
        }

        queue.add(new int[]{i, j});
        shortestPath[i][j] = 0;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            for (int[] position : positions) {
                int newX = pos[0] + position[0];
                int newY = pos[1] + position[1];
                if (newX >= 0 && newX < m && newY >= 0 && newY < n) {
                    if (map[newX][newY] == 'T') {
                        shortestPath[newX][newY] = shortestPath[pos[0]][pos[1]] + 1;
                    } else if (map[newX][newY] == '0' && shortestPath[newX][newY] == -1) {
                        shortestPath[newX][newY] = shortestPath[pos[0]][pos[1]] + 1;
                        queue.offer(new int[]{newX, newY});
                    }
                }
//                if (newX >= 0 && newX < m && newY >= 0 && newY < n && map[newX][newY] == '1') {
//                    continue;
//                }
//                if (newX >= 0 && newX < m && newY >= 0 && newY < n && map[newX][newY] == '0' && shortestPath[newX][newY] == -1) {
//                    shortestPath[newX][newY] = shortestPath[pos[0]][pos[1]] + 1;
//                    queue.offer(new int[]{newX, newY});
//                }
            }
        }

        return shortestPath[tx][ty] == -1 ? Integer.MAX_VALUE : shortestPath[tx][ty];
    }
}
/*
5 6
X00100
00000X
01T000
0X1010
00000X

5 6
X00100
10100X
11T000
0X1010
00000X
 */

