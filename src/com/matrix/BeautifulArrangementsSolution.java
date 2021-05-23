package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author boyuangong created on 1/23/21 at 18:47
 */
public class BeautifulArrangementsSolution {
    int count = 0;
    public int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        calculate(N, 1, visited);
        return count;
    }
    public void calculate(int N, int pos, boolean[] visited) {
        if (pos > N)
            count++;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                calculate(N, pos + 1, visited);
                visited[i] = false;
            }
        }
    }

    public int countArrangement2(int N) {
        int[] nums = new int[N];
        for (int i = 1; i <= N; i++)
            nums[i - 1] = i;
        permute(nums, 0);
        return count;
    }
    public void permute(int[] nums, int l) {
        if (l == nums.length - 1) {
            int i;
            for (i = 1; i <= nums.length; i++) {
                if (nums[i - 1] % i != 0 && i % nums[i - 1] != 0)
                    break;
            }
            if (i == nums.length + 1) {
                System.out.println(Arrays.toString(nums));
                count++;
            }
        }
        for (int i = l; i < nums.length; i++) {
            swap(nums, i, l);
            permute(nums, l + 1);
            swap(nums, i, l);
        }
    }
    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }

    public void testswap(int[] test) {
        for (int i=1; i<7; i++) {
            swap(test, 0, i);
            System.out.println(Arrays.toString(test));
            swap(test, i, 0);
        }
    }

    public static void main(String[] args) {
    }
}
