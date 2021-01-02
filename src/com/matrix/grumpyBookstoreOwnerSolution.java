package com.matrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author boyuangong created on 10/13/19 at 21:44
 */
public class grumpyBookstoreOwnerSolution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int[] sum = new int[customers.length];
        Arrays.fill(sum, 0);

        for (int i=0; i<X; i++) {
            sum[X-1] += customers[i] * grumpy[i];
        }

        int index = X-1;
        int max = sum[X-1];
        for (int i=X; i<customers.length; i++) {
            sum[i] = sum[i-1] + customers[i] * grumpy[i] - customers[i - X] * grumpy[i - X];

            if (sum[i] > max) {
                max = sum[i];
                System.out.println(i);
                index = i;
            }

            System.out.println(Arrays.toString(sum));
        }

        for (int i=index; i>index-X; i--) {
            grumpy[i] = 0;
        }
        System.out.println(index);

        int ans = 0;
        for (int i=0; i<customers.length; i++) {
            ans += customers[i] * (1-grumpy[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        grumpyBookstoreOwnerSolution solution = new grumpyBookstoreOwnerSolution();

        int ans = solution.maxSatisfied(new int[]{4,2,1,2,8,9,1}, new int[]{0,1,1,1,1,0,0}, 4);
        System.out.println(ans);
    }
}
