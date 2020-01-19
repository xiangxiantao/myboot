package com.xxt.letcode;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Solution {

    /**
     * 求最大重复字串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
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
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length == 0) {
            return nums2.length % 2 == 0 ? new BigDecimal(nums2[nums2.length / 2 - 1] + nums2[nums2.length / 2]).divide(new BigDecimal("2")).doubleValue() : nums2[nums2.length / 2];
        } else if (nums2.length == 0) {
            return nums1.length % 2 == 0 ? new BigDecimal(nums1[nums1.length / 2 - 1] + nums1[nums1.length / 2]).divide(new BigDecimal("2")).doubleValue() : nums1[nums1.length / 2];
        }
        int[] aux = new int[nums1.length + nums2.length];
        int i = 0, j = 0;
        for (int k = 0; k < aux.length; k++) {
            if (i >= nums1.length) aux[k] = nums2[j++];
            else if (j >= nums2.length) aux[k] = nums1[i++];
            else if (nums1[i] > nums2[j]) aux[k] = nums2[j++];
            else aux[k] = nums1[i++];
        }

        BigDecimal bigDecimal = new BigDecimal(aux[aux.length / 2 - 1] + aux[aux.length / 2]);
        if (aux.length % 2 == 0) return bigDecimal.divide(new BigDecimal("2")).doubleValue();
        else return aux[aux.length / 2];
    }

    /**
     * 5.最长回文字串
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        String longestPalindromeStr = null;
        if (s.length() == 0) return s;
        for (int i = 0; i < s.length(); i++) {
            //当剩余字符串长度已经小于或等于回文长度了，就可以停止遍历，节约时间
            if (longestPalindromeStr != null && s.substring(i).length() <= longestPalindromeStr.length()) break;
            String temp = null;
            for (int j = s.length(); j > i; j--) {
                if (isPalindrome(s.substring(i, j))) {
                    temp = s.substring(i, j);
                    break;
                }
            }
            if (longestPalindromeStr == null || temp.length() > longestPalindromeStr.length()) {
                longestPalindromeStr = temp;
            }
        }

        return longestPalindromeStr;
    }

    /**
     * 判断字符串是否是回文
     *
     * @param s
     * @return
     */
    private static boolean isPalindrome(String s) {
        int length = s.length();
        if (s == null || s.length() == 0) return false;
        for (int i = 0; i < length - 1; i++) {
            if (s.charAt(i) != s.charAt(length - i - 1)) return false;
        }
        return true;
    }

    /**
     * 11.盛水最多的容器
     *
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int maxArea = 0;
        int head = 0;
        int front = height.length - 1;
        while (head < front) {
            maxArea = Math.max(maxArea, Math.min(height[head], height[front]) * (front - head));
            if (height[head] > height[front]) front--;
            else head++;
        }
        return maxArea;
    }

    /**
     * 三数之和
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len < 3) return result;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[L] + nums[R] + nums[i];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[R], nums[L]));
                    while (L < R && nums[L] == nums[L + 1]) L++; // 去重
                    while (L < R && nums[R] == nums[R - 1]) R--; // 去重
                    L++;
                    R--;

                } else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return result;

    }


    /**
     * 17电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        String[] tabs = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> result = new LinkedList<>();
        for (int i = 0; i < digits.length(); i++) {
            String tab = tabs[Integer.parseInt(digits.substring(i, i + 1))];
            int size = result.size();
            if (size > 0) {
                for (int j = 0; j < size; j++) {
                    //取出队列的第一个元素
                    String first = result.removeFirst();
                    for (int k = 0; k < tab.length(); k++) {
                        result.addLast(first + tab.charAt(k));
                    }
                }
            } else {
                for (int k = 0; k < tab.length(); k++) {
                    result.addLast(String.valueOf(tab.charAt(k)));
                }
            }

        }
        return result;
    }

    /**
     * 删除单链表的倒数第N个结点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        int size = 1;
        ListNode current = head;
        while (current.next != null) {
            size++;
            current = current.next;
        }

        int deleteIndex = size - n + 1;
        if (deleteIndex == 1) {
            head = head.next;
        } else {
            ListNode privoursOfDeleteNode = head;
            for (int i = 1; i < deleteIndex - 1; i++) {
                privoursOfDeleteNode = privoursOfDeleteNode.next;
            }
            privoursOfDeleteNode.next = privoursOfDeleteNode.next.next;
        }
        return head;
    }

    /**
     * 删除单链表的倒数第N个结点
     * 解法2：双指针法
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dimy=new ListNode(0);
        dimy.next=head;
        ListNode preOfDelete=dimy;
        ListNode guide=dimy;
        int i=1;
        while (i<=n+1){
            guide=guide.next;
        }


        while (guide.next!=null){
            guide=guide.next;
            preOfDelete=preOfDelete.next;
        }

        preOfDelete.next=preOfDelete.next.next;
        return head;

    }


    /**
     * 有效的括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if ((s.length() & 1)==1);
        boolean valid=true;
        if (s==null||s.trim().equals("")) return valid;
        Deque<String> stack=new LinkedList<String>();
        Map<String,String> couple=new HashMap();
        couple.put("(",")");
        couple.put("[","]");
        couple.put("{","}");
        for (int i = 0; i < s.length(); i++) {
            if (s.substring(i,i+1).equals("(")||s.substring(i,i+1).equals("[")||s.substring(i,i+1).equals("{")){
                stack.push(s.substring(i,i+1));

            }else if (s.substring(i,i+1).equals(")")||s.substring(i,i+1).equals("]")||s.substring(i,i+1).equals("}")){
                if (stack.size()==0) {
                    return false;
                }
                String pop = stack.pop();
                if (!s.substring(i,i+1).equals(couple.get(pop))){
                    valid=false;
                }
            }
        }

        if (stack.size()>0) valid=false;
        return valid;
    }

    /**
     * 20 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        return null;
    }


    public static void main(String[] args) {
        System.out.println(isValid("([{)}])"));

    }


}
