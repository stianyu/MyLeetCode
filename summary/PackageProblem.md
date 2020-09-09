# 背包9讲

来自up主雪菜分享的背包9讲视频<https://www.bilibili.com/video/BV1qt411Z7nE?from=search&seid=7504417937795305163>

练习网站：www.acwing.com

![Alt text](F:\MarkDownMaterials\背包九讲\image-20200801114211592.png)

## 01背包问题

> 有 NN 件物品和一个容量是 VV 的背包。每件物品只能使用一次。
>
> 第 ii 件物品的体积是 vivi，价值是 wiwi。
>
> 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
> 输出最大价值。
>
> #### 输入格式
>
> 第一行两个整数，N，VN，V，用空格隔开，分别表示物品数量和背包容积。
>
> 接下来有 NN 行，每行两个整数 vi,wivi,wi，用空格隔开，分别表示第 ii 件物品的体积和价值。
>
> #### 输出格式
>
> 输出一个整数，表示最大价值。
>
> #### 数据范围
>
> 0<N,V≤10000<N,V≤1000
> 0<vi,wi≤10000<vi,wi≤1000
>
> #### 输入样例
>
> ```
> 4 5
> 1 2
> 2 4
> 3 4
> 4 5
> ```
>
> #### 输出样例：
>
> ```
> 8
> ```

### 使用二维数组

```java
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] v = new int[n+1];
            int[] w = new int[n+1];
            for(int i = 1; i <= n; i++) {  // 从i=1开始思考
                v[i] = in.nextInt();
                w[i] = in.nextInt();
            }
            System.out.println(solve(n, m, v, w));
        }
    }
    
    /*
        f[i][j]是选前i个物品，总体积为j的情况下，总价值是多少
        
        result = max(f[n][0~v])
        
        状态转移方程：
        1. 不选第i个物品，则f[i][j] = f[i-1][j];
        2. 选第i个物品，则f[i][j] = f[i-1][j-v[i]] + w[i]
        f[i][j] = max(1, 2)
        
        f[0][0] = 0;
        
    */
    public static int solve(int n, int m, int[] v, int[] w) {
        int[][] f = new int[n+1][m+1];  
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                f[i][j] = f[i-1][j];
                if(v[i] <= j) {
                    f[i][j] = Math.max(f[i][j], f[i-1][j-v[i]] + w[i]);
                }
            }
        }
        
        int res = 0;
        for(int j = 0; j <= m; j++) {
            res = Math.max(res, f[n][j]);
        }
        
        return res;
    }
}
```

### 二维数组压缩到一维

因为上述二维 dp 中状态转移的时候只参考了 i-1 层的情况，所以可以将二维降维到一维，如下：

`f[j] = Math.max(f[j], f[j - v[i]] + w[i]);` 如果直接改这里 ( j 从1递增到m) 会覆盖旧值，让下一行参考时参考的是覆盖的新值，状态转移就出问题了。因此如果从从后往前填表，就能保证当前 j 行参考的是 j-1 的旧值。

最后也不需要遍历 0~m 来获得最大值。

这代码真优美啊~

```java
import java.util.*;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] f = new int[m+1];
            for(int i = 1; i <= n; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                for(int j = m; j >= v; j--) {
                    f[j] = Math.max(f[j], f[j-v] + w);
                }
            }
            System.out.println(f[m]);
        }
    }
}
```

## 完全背包问题

> 有 NN 种物品和一个容量是 VV 的背包，每种物品都有无限件可用。
>
> 第 ii 种物品的体积是 vivi，价值是 wiwi。
>
> 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。
> 输出最大价值。
>
> #### 输入格式
>
> 第一行两个整数，N，VN，V，用空格隔开，分别表示物品种数和背包容积。
>
> 接下来有 NN 行，每行两个整数 vi,wivi,wi，用空格隔开，分别表示第 ii 种物品的体积和价值。
>
> #### 输出格式
>
> 输出一个整数，表示最大价值。
>
> #### 数据范围
>
> 0<N,V≤10000<N,V≤1000
> 0<vi,wi≤10000<vi,wi≤1000
>
> #### 输入样例
>
> ```
> 4 5
> 1 2
> 2 4
> 3 4
> 4 5
> ```
>
> #### 输出样例：
>
> ```
> 10
> ```



代码：

```java
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(in.hasNextInt()) {
            int n = in.nextInt();
            int m = in.nextInt();
            int[] f = new int[1010];
            for( int i = 0; i < n; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                for(int j = v; j <= m; j++) {
                    f[j] = Math.max(f[j], f[j-v] + w);    
                }
            }
            System.out.println(f[m]);
        }
    }
}
```



