package _21.dp.ClassicalProblem._322_CoinChange;

import java.util.Arrays;

/**
 * 322. 零钱兑换
 *      给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 *      输入: coins = [1, 2, 5], amount = 11
 *      输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 *      输入: coins = [2], amount = 3
 *      输出: -1
 * 说明:
 *      你可以认为每种硬币的数量是无限的。
 */
public class Solution {
    public int coinChange(int[] coins, int amount) {
        // 状态定义为: dp[i]：凑齐总价值 i 需要的最少硬币数
        int[] dp = new int[amount + 1];

        // 因为要设置为最小值，所以这里设置一个不可能的大值为初值
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if(i >= coin) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }

        return dp[amount] == amount + 1? -1 : dp[amount];
    }
}
