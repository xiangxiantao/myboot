package com.xxt.tree;

public interface TreeX {
    public Node find(int key);

    public void insert(Node node);

    public boolean delete(int key);

    public void inOrder(Node node);

    public void preOrder(Node node);

    public void postOrder(Node node);

    public Node minimum();

    public Node max();

}
