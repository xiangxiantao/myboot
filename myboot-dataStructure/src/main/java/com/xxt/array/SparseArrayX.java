package com.xxt.array;

import java.util.ServiceLoader;

/**
 * 重复数据过多的二维数组可以压缩成稀疏数组，如五子棋棋盘
 */
public class SparseArrayX {

    public static int[][] toSparse(){
        int[][] checkerboard=new int[11][11];
        checkerboard[3][4]=1;
        checkerboard[5][7]=1;
        checkerboard[6][9]=1;

        int num =0;
        //打印普通棋盘
        for (int[] row:checkerboard){
            for (int value:row){
                System.out.printf("%d\t",value);
                if (value!=0)
                    num++;
            }
            System.out.println("");

        }

        int[][] sparseArray=new int[num+1][3];
        sparseArray[0][0]=11;
        sparseArray[0][1]=11;
        sparseArray[0][2]=num;
        int row=1;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (checkerboard[i][j]!=0){
                    sparseArray[row][0]=i;
                    sparseArray[row][1]=j;
                    sparseArray[row][2]=checkerboard[i][j];
                    row++;
                }
            }
        }

        System.out.println("=====================================================");
        //打印稀疏数组
        for (int[] index:sparseArray){
            for (int value:index){
                System.out.printf("%d\t",value);
            }
            System.out.println("");

        }

        return sparseArray;
    }


    public static int[][] fromSparseArray(){
        int[][] sparseArray=SparseArrayX.toSparse();
        int[][] checkerboard=new int[sparseArray[0][0]][sparseArray[0][1]];
        for (int[] row:sparseArray){
            if (row[0]>10)
                continue;
            checkerboard[row[0]][row[1]]=row[2];
        }

        //打印普通棋盘
        for (int[] row:checkerboard){
            for (int value:row){
                System.out.printf("%d\t",value);
            }
            System.out.println("");

        }

        return checkerboard;
    }

    public static void main(String[] args) {
        //SparseArrayX.toSparse();
        SparseArrayX.fromSparseArray();

    }

}
