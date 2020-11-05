package _18.bfs.SearchMinDepth;

import java.util.*;

/**
 * 从T出发遍历X
 */
public class BFS2 {

    static int[][] positions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            // 1. 处理输入
            String[] strings = in.nextLine().split(" ");
            int m = Integer.parseInt(strings[0]);
            int n = Integer.parseInt(strings[1]);

            char[][] grid = new char[m][n];
            int tx = 0;
            int ty = 0;

            List<int[]> lists = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                grid[i] = in.nextLine().toCharArray();
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 'T') {    // 找到T的位置，并记录 bfs 开始的起始点
                        tx = i;
                        ty = j;
                    } else if (grid[i][j] == 'X') {  // 找到X的位置，并赋值为0，加入到lists中供最后比较各X的最短路径
                        lists.add(new int[]{i, j});
                        grid[i][j] = '0';
                    }
                }
            }

            // 2. 从 T 开始bfs，只遍历一次即可
            Queue<int[]> queue = new LinkedList<>();
            // 初始化一个表示bfs层数的数组为-1，表示从当前位置i，j开始走到tx，ty的路径长度，最终返回deep[tx][ty]即是最小路径
            int[][] shortestPath = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    shortestPath[i][j] = -1;
                }
            }

            queue.add(new int[]{tx, ty});
            shortestPath[tx][ty] = 0;

            while (!queue.isEmpty()) {
                int[] top = queue.poll();

                for (int[] pos : positions) {
                    int newX = top[0] + pos[0];
                    int newY = top[1] + pos[1];

                    // 只有在grid为0,且deep不为-1的时候入队
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == '0' && shortestPath[newX][newY] == -1) {
                        shortestPath[newX][newY] = shortestPath[top[0]][top[1]] + 1;
                        queue.offer(new int[]{newX, newY});
                    }
                }
            }

            // 3. 遍历各 X 位置的路径值，找到最短的路径并记录坐标
            int minDeep = Integer.MAX_VALUE;
            List<int[]> res = new ArrayList<>();
            for (int i = 0; i < lists.size(); i++) {
                int[] start = lists.get(i);
                int temp = shortestPath[start[0]][start[1]];
                if (temp == -1) {
                    continue;
                }
                if (temp < minDeep) {
                    minDeep = temp;
                    res.clear();
                    res.add(start);
                } else if (temp == minDeep) {  // 出现和当前最短路径相等的路径则加入坐标
                    res.add(start);
                }
            }

            // 4.打印结果
            if (minDeep == Integer.MAX_VALUE) {
                System.out.println(-1);
            } else {
                System.out.println(minDeep);
                for (int i = 0; i < res.size(); i++) {
                    if (i != res.size() - 1) {
                        System.out.print(res.get(i)[0] + " " + res.get(i)[1] + " ");
                    } else {
                        System.out.println(res.get(i)[0] + " " + res.get(i)[1]);
                    }
                }
            }
        }
    }
}
/*
input:
5 6
X00100
00000X
01T000
0X1010
00000X

output:
4
0 0 1 5

input:
5 6
X00100
10100X
11T000
0X1010
00000X

output:
4
1 5
 */

