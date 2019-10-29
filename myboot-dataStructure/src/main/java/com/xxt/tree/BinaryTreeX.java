package com.xxt.tree;

/**
 * 二叉树
 * 优点：1.查找数据的速度和有序数组一样快
 *      2.插入，删除数据项的速度和链表一样快
 * 问题：如果树中的数据是有序插入的，树最坏的情况下会退化为链表
 * 搜索二叉树最常用的遍历方式是中序遍历
 */
public class BinaryTreeX implements TreeX{

    private Node root;

    @Override
    public Node find(int key) {
        Node current=root;
        while (current.getIData()!=key){
            if (key<current.getIData()){
                current=current.getLNode();
            }else {
                current=current.getRNode();
            }
            if (current==null)
                return null;
        }
        return current;
    }

    @Override
    public void insert(Node node) {
        if (root==null){
            root=node;
        }
        Node current=root;
        Node parent;
        while (true){
            parent=current;
            if (node.getIData()<parent.getIData()){
                current=current.getLNode();
                if (current==null){
                    parent.setLNode(node);
                    return;
                }
            }else {
                current=current.getRNode();
                if (current==null){
                    parent.setRNode(node);
                    return;
                }
            }
        }
    }

    /**
     * 删除结点的第一步和查找一样，是找到结点
     * 找到结点之后分三种情况处理：
     * 1.该节点是叶子结点：直接删除
     * 2.该节点有一个子节点：用该节点的子结点代替该节点
     * 3.该节点有两个子节点：用该结点左子树最右边的叶子节点代替该节点(中序遍历的后继)
     *
     * 删除结点时，在找到该节点同时需要保留该节点的父节点，并判断待删除的结点是父节点的左结点还是右结点
     * 然后通过将父节点的左/右结点置为null来实现删除动作
     * @param key
     * @return
     */
    @Override
    public boolean delete(int key) {
        Node current=root;
        Node parent=root;
        boolean isLeftNode=false;
        while (current.getIData()!=key){
            parent=current;
            if (key<current.getIData()){
                current=current.getLNode();
                isLeftNode=true;
            }else {
                current=current.getRNode();
                isLeftNode=false;
            }
            if (current==null)
                return false;
        }

        if (current.getLNode()==null&&current.getRNode()==null){//没有子结点
            if (current==root){
                root=null;
            } else if (isLeftNode){
                parent.setLNode(null);
            } else{
                parent.setRNode(null);
            }
        }else if (current.getLNode()==null){//左结点为空
            if (current==root){
                root=current.getRNode();
            } else if (isLeftNode){
                parent.setLNode(current.getRNode());
            }else{
                parent.setRNode(current.getRNode());
            }
        }else if (current.getRNode()==null){//右结点为空
            if (current==root){
                root=current.getLNode();
            } else if (isLeftNode){
                parent.setLNode(current.getLNode());
            }else{
                parent.setRNode(current.getLNode());
            }
        }else {//都不为空
            Node successor=getSuccessor(current);
            if (current==root){
                root=successor;
            }else if (isLeftNode){
                parent.setLNode(successor);
            }else {
                parent.setRNode(successor);
            }
            successor.setLNode(current.getLNode());
        }

        return true;
    }

    //寻找一个结点的后继结点（右子树最左下的结点）
    private Node getSuccessor(Node delNode) {
        Node successorParent=delNode;
        Node successor=delNode;
        Node current=delNode.getRNode();
        while (current!=null){
            successorParent=successor;
            successor=current;
            current=current.getLNode();
        }
        if (successor!=delNode.getRNode()){
            successorParent.setLNode(successor.getRNode());
            successor.setRNode(delNode.getRNode());
        }
        return successor;
    }

    @Override
    public void inOrder(Node node) {
        if (node!=null){
            inOrder(node.getLNode());
            node.show();
            inOrder(node.getRNode());
        }
    }

    @Override
    public void preOrder(Node node) {
        if (node!=null){
            node.show();
            inOrder(node.getLNode());
            inOrder(node.getRNode());
        }
    }

    @Override
    public void postOrder(Node node) {
        if (node!=null){
            inOrder(node.getLNode());
            inOrder(node.getRNode());
            node.show();
        }
    }

    @Override
    public Node minimum() {
        Node currunt,last=null;
        currunt=root;
        while (currunt!=null){
            last=currunt;
            currunt=currunt.getLNode();
        }
        return last;
    }

    @Override
    public Node max() {
        Node currunt,last=null;
        currunt=root;
        while (currunt!=null){
            last=currunt;
            currunt=currunt.getRNode();
        }
        return last;
    }

}


