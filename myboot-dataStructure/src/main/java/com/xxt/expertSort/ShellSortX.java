package com.xxt.expertSort;


import java.util.ArrayList;
import java.util.List;


/**
 * 希尔排序
  */
public class ShellSortX {
    private long[] theArray;
    private int nElement;

    public ShellSortX(int max){
        theArray=new long[max];
        nElement=0;
    }

    public void insert(long value){
        theArray[nElement]=value;
        nElement++;
    }

    public void display(){
        for (int i = 0; i < nElement; i++) {
            System.out.println(theArray[i]);
        }
    }

    public void shellSort(){
        int inner,outer;
        long temp;

        int h=1;
        while (h<nElement){
            h=h*3+1;
        }

        while (h>0){
            for (outer=h;outer<nElement;outer++){
                temp=theArray[outer];
                inner=outer;
                while (inner>h-1&&theArray[inner-h]>=temp){
                    theArray[inner]=theArray[inner-h];
                    inner-=h;
                }
                theArray[inner]=temp;
            }
            h=(h-1)/3;
        }

    }


    /**
     * 获取一个有效的间隔序列
     * @param n
     * @return
     */
    public static List knuthSequence(int n){
        List<Integer> knuthSeq=new ArrayList<Integer>();
        for (int h=1;h<n;h=h*3+1){
            knuthSeq.add(h);

        }
        return knuthSeq;
    }

    public static void main(String[] args) {
        ShellSortX shellSortX=new ShellSortX(100);
        for (int i = 0; i < 100; i++) {
            int value= (int) (Math.random()*99);
            shellSortX.insert(value);
        }
        shellSortX.display();
        shellSortX.shellSort();
        System.out.println("============================================================");
        shellSortX.display();
    }

}
