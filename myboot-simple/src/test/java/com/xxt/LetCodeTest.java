package com.xxt;


import java.util.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class LetCodeTest {
}

class Solution {
    public static void main(String[] args) {
        Solution solution=new Solution();
        solution.lengthOfLongestSubstring("qwertasdfqwesdfghjxcvbnm,wqes");
    }

    public int lengthOfLongestSubstring(String s) {
        char[] str=s.toCharArray();
        List containChars=new ArrayList();
        Map<String,List<Integer>> indexMap=new HashMap<>();
        for (int i = 0; i < str.length; i++) {
            if (indexMap.containsKey(String.valueOf(str[i]))){
                indexMap.get(String.valueOf(str[i])).add(i);
            }else {
                List<Integer> indexs=new LinkedList<>();
                indexs.add(i);
                indexMap.put(String.valueOf(str[i]),indexs);
            }
        }
        List<Point> points=new LinkedList<>();
        for (List<Integer> indexs:indexMap.values()){
            for (int i = 0; i < indexs.size(); i++) {
                if (i==0){
                    Point point=new Point();
                    point.setStartIndex(0);
                    point.setEndIndex(indexs.get(i));
                    point.setLength(indexs.get(i));
                    points.add(point);
                    points.add(point);
                }else {
                    Point point=new Point();
                    point.setStartIndex(indexs.get(i-1));
                    point.setEndIndex(indexs.get(i));
                    point.setLength(indexs.get(i)-indexs.get(i-1));
                    points.add(point);
                }
            }
        }

        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o2.getLength()-o1.getLength();
            }
        });


        return 0;
    }

    /**
     * 判断B是否为A的内聚坐标
     */
    public boolean isCoherence(Point A,Point B){
        return A.startIndex<=B.startIndex&&A.endIndex>=B.endIndex;
    }

    class Point{
        int startIndex;
        int endIndex;
        int length;

        public int getStartIndex() {
            return startIndex;
        }

        public void setStartIndex(int startIndex) {
            this.startIndex = startIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        public int getLength() {
            return length;
        }

        public void setLength(int length) {
            this.length = length;
        }
    }
}