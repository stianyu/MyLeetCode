package _18.bfs.SearchMinDepth;

import java.util.*;

/**
 * 从X出发多次bfs遍历到T
 */
public class BFS1 {

    static int[][] positions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int m;
    static int n;
    static int tx;
    static int ty;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            // 1. 处理输入
            String[] strings = in.nextLine().split(" ");
            m = Integer.parseInt(strings[0]);
            n = Integer.parseInt(strings[1]);
            char[][] grid = new char[m][n];
            for (int i = 0; i < m; i++) {
                grid[i] = in.nextLine().toCharArray();
                // 找到T的位置，并赋值为0
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 'T') {
                        tx = i;
                        ty = j;
                        grid[i][j] = '0';
                    }
                }
            }

            // 2. 从 X 开始bfs，需遍历 X 的个数次
            int minDeep = Integer.MAX_VALUE;
            List<int[]> lists = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 'X') {  // 出现最短路径则更新路径值和坐标
                        int temp = bfs(grid, i, j);
                        if (temp < minDeep) {
                            minDeep = temp;
                            lists.clear();
                            lists.add(new int[]{i, j});
                        } else if (temp == minDeep) {  // 出现和当前最短路径相等的路径则加入坐标
                            lists.add(new int[]{i, j});
                        }
                    }
                }
            }

            // 3.打印结果
            if (minDeep == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
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
    }

    private static int bfs(char[][] map, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();

        // 初始化一个表示bfs层数的数组为-1，表示从当前位置i，j开始走到tx，ty的路径长度，最终返回deep[tx][ty]即是最小路径
        int[][] shortestPath = new int[m][n];
        for (int k = 0; k < m; k++) {
            for (int l = 0; l < n; l++) {
                shortestPath[k][l] = -1;
            }
        }

        queue.add(new int[]{i, j});
        shortestPath[i][j] = 0;

        while (!queue.isEmpty()) {
            int[] top = queue.poll();

            for (int[] pos : positions) {
                int newX = top[0] + pos[0];
                int newY = top[1] + pos[1];

                // 只有在grid为0,且deep不为-1的时候入队
                if (newX >= 0 && newX < m && newY >= 0 && newY < n && map[newX][newY] == '0' && shortestPath[newX][newY] == -1) {
                    shortestPath[newX][newY] = shortestPath[top[0]][top[1]] + 1;
                    // 遍历到位置为"T"时提前终止
                    if (newX == tx && newY == ty) {
                        break;
                    }
                    queue.offer(new int[]{newX, newY});
                }
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

