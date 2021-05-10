package com.zhn.demo.basic.other;

import java.util.LinkedList;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        LinkedList<Integer> list = new LinkedList<>();

    }

    public int[] twoSum(int[] nums, int target) {
        int[] results = new int[2];
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            int one = nums[i];
            for (int j = i + 1; j < len; j++) {
                int two = nums[j];
                if (target == one + two) {
                    results[0] = i;
                    results[1] = j;
                    break;
                }
            }
        }
        return results;
    }
}
