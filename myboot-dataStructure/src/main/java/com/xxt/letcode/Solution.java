package com.xxt.letcode;

import java.math.BigDecimal;

public class Solution {

    /**
     * 求最大重复字串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s==null||s.length()==0) return 0;
        int maxCount = 1;
        for (int i = 0; i < s.length(); i++) {
            int temp = 1;
            for (int j = i + 1; j < s.length(); j++) {
                boolean cf = false;
                for (int k = i; k < j; k++) {
                    if (cf = s.charAt(j) == s.charAt(k)) break;
                }
                if (cf) break;
                temp++;
            }
            maxCount = maxCount > temp ? maxCount : temp;
        }

        return maxCount;
    }

    /**
     * 寻找两个有序数组的中位数
     * 利用归并排序的算法，时间复杂度（n+m）
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length==0){
            return nums2.length%2==0 ? new BigDecimal(nums2[nums2.length/2-1]+nums2[nums2.length/2]).divide(new BigDecimal("2")).doubleValue():nums2[nums2.length/2];
        }else if (nums2.length==0){
            return nums1.length%2==0 ? new BigDecimal(nums1[nums1.length/2-1]+nums1[nums1.length/2]).divide(new BigDecimal("2")).doubleValue():nums1[nums1.length/2];
        }
        int[] aux =new int[nums1.length+nums2.length];
        int i=0,j=0;
        for (int k = 0; k < aux.length; k++) {
            if (i>=nums1.length) aux[k]=nums2[j++];
            else if (j>=nums2.length) aux[k]=nums1[i++];
            else if (nums1[i]>nums2[j]) aux[k]=nums2[j++];
            else aux[k]=nums1[i++];
        }

        BigDecimal bigDecimal=new BigDecimal(aux[aux.length/2-1]+aux[aux.length/2]);
        if (aux.length%2==0) return bigDecimal.divide(new BigDecimal("2")).doubleValue();
        else return aux[aux.length/2];
    }

    /**
     * 5.最长回文字串
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String longestPalindromeStr=null;
        if (s.length()==0) return s;
        for (int i = 0; i < s.length(); i++) {
            //当剩余字符串长度已经小于或等于回文长度了，就可以停止遍历，节约时间
            if (longestPalindromeStr!=null&&s.substring(i).length()<=longestPalindromeStr.length()) break;
            String temp=null;
            for (int j = s.length(); j > i ; j--) {
                if (isPalindrome(s.substring(i,j))){
                    temp=s.substring(i,j);
                    break;
                }
            }
            if (longestPalindromeStr==null||temp.length()>longestPalindromeStr.length()){
                longestPalindromeStr=temp;
            }
        }
        
        return longestPalindromeStr;
    }

    /**
     *判断字符串是否是回文
     * @param s
     * @return
     */
    private static boolean isPalindrome(String s){
        int length=s.length();
        if (s==null||s.length()==0) return false;
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i)!=s.charAt(length-i-1)) return false;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));

    }


}
