package com.practice.interview;

public class LongestIncreasingSubArraywith1DeletionAllowed {
    public static void main(String[] args) {
        System.out.println("Test 1: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 0}));

        System.out.println("Test 2: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 5, 0, -1, 7, 1}));

        System.out.println("Test 3: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 3, 0, 4, 5}));

        System.out.println("Test 4: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 5, 3, 4, 6}));

        System.out.println("Test 5: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{5, 4, 3, 2, 1}));

        System.out.println("Test 6: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Test 7: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 10, 3, 4, 5, 6}));

        System.out.println("Test 8: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 3, 10, 4, 5, 6}));

        System.out.println("Test 9: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 5, 2, 3, 4}));

        System.out.println("Test 10: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 5, 3, 4, 0, 6}));

        System.out.println("Test 11: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 3, 2, 3, 4}));

        System.out.println("Test 12: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{3, 4, 2, 5, 6, 7}));

        System.out.println("Test 13: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 2, 100, 3, 4, 5, 6, 7}));

        System.out.println("Test 14: " +
                longestIncreasingSubArraywith1DeletionAllowed(new int[]{1, 100, 2, 3, 4, 5}));
    }

    private static int longestIncreasingSubArraywith1DeletionAllowed(int[] nums) {
        int len=1,left=0,deletedIndex=-1,lastCOmp=0,right=1,n=nums.length;
        while (right<n){
          if(nums[right]<nums[lastCOmp]){

              if(deletedIndex!=-1){
                  System.out.println("jumping left::"+left+",deletedIndex::"+deletedIndex+",lastCOmp"+lastCOmp);
                  left=lastCOmp+1;
                  if(left==deletedIndex)
                      left++;

                  deletedIndex=-1;

                  continue;
              }
              if(right>1 && nums[right]>nums[right-2]){
                  deletedIndex=right-1;
                  lastCOmp=right;
              }else{
                  deletedIndex=right;
                  lastCOmp=right-1;
              }
          }else{
              lastCOmp++;
              if(lastCOmp==deletedIndex)
                  lastCOmp++;

              if(deletedIndex!=-1 && deletedIndex>=left && deletedIndex<=right)
                  len = Math.max(len,right-left);
              else
                  len = Math.max(len,right-left+1);
          }

          right++;
        }

return len;
    }
}
