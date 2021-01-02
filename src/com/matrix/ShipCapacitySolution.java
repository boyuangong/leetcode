package com.matrix;

/**
 * @author boyuangong created on 10/23/19 at 22:40
 */
public class ShipCapacitySolution {
    // leetcode 1011 https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
    private int[] weights;
    private int D;

    public int shipWithinDays(int[] weights, int D) {
        this.weights = weights;
        this.D = D;
        // The capacity will between max(weights) and sum(weights)
        int sum = 0;
        int max = 0;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(weight, max);
        }
        int left = max;
        int right = sum;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            int flag = testMid(mid);
            if (flag >= 0) {
                right = mid;
            } else {
                left = mid;
            }
        }

        if (testMid(left) >= 0) {
            return left;
        } else {
            return right;
        }
    }

    public int testMid(int mid) {
        int idx = 0;
        int sum = 0;
        for (int i=0; i<weights.length; i++) {
            sum += weights[i];
            if (sum >= mid) {
                idx++;
                sum = sum == mid? 0: weights[i];

            }
        }

        if (sum > 0) {
            idx ++;
        }

        if (idx > D) {
            return -1;
        }

        if (idx < D) {
            return 1;
        }
        return 0;
    }

    public int shipWithinDays2(int[] weights, int D) {
        int start = 0, end = 50000;
        while(start + 1 < end) {
            int mid = (start + end) / 2;
            if(isValid(mid, weights, D)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        System.out.println(isValid(7, weights, D));
        if(isValid(start, weights, D))
            return start;
        return end;
    }

    private boolean isValid(int mid, int[] weights, int D) {
        int days = 0;
        int index = 0;
        while(index < weights.length) {
            days++;
            if(weights[index] > mid) return false;
            int max = mid;
            if(weights[index] < max) {
                while(index < weights.length && weights[index] <= max) {
                    max = max - weights[index];
                    index++;
                }
            } else {
                index++;
            }
        }

        return days <= D;
    }

    public static void main(String[] args) {
        ShipCapacitySolution solution = new ShipCapacitySolution();
        System.out.println(solution.shipWithinDays(new int[]{1,2,3,1,1}, 4));
//        System.out.println(solution.shipWithinDays2(new int[]{1,2,3,1,1}, 4));

    }
}
