package com.xxt.tree;

import lombok.Data;

@Data
public class Node {
    private int iData;
    private double fData;
    private Node lNode;
    private Node rNode;

    public void show(){
        System.out.println(this.iData+":"+this.fData);
    }

}
