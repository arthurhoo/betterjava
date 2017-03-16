package com.huhao.code.advancejava.datastructure;

/**
 * Created by huhao on 2017/3/2.
 */
public class BinaryTree<E> {

    private BTNode<E> root = null;

     public BinaryTree(){}

    public BTNode<E> insertNode(E item){
        BTNode<E> insertNode = new BTNode<E>(null,item,null);
        return insertNode(this.root,insertNode);
    }

    protected BTNode<E> insertNode(BTNode<E> rootNode, BTNode<E> node){
        if(node == null){
            return null;
        }

        if(rootNode == null){
            rootNode =  node;
        }else if(node.compareTo(rootNode.item) < 0){
            rootNode.left = insertNode(rootNode.left,node);
        }else{
            rootNode.right = insertNode(rootNode.right,node);
        }
        return rootNode;

    }

    /**
     * 中序遍历
     */

    public void inOrderTravel(){
        inOrder(this.root);
    }

    private void inOrder(BTNode<E> rootNode){
        if(rootNode != null){
            inOrder(rootNode.left);
            System.out.println(rootNode.item);
            inOrder(rootNode.right);
        }
    }


    /**
     * 查找
     * @param
     */

    private BTNode<E> searchNode(BTNode<E> rootNode, E item){
        if(rootNode == null){
            return null;
        }
        if(rootNode.compareTo(item) > 0){
            return searchNode(rootNode.left,item);
        }else if(rootNode.compareTo(item) < 0){
            return searchNode(rootNode.right,item);
        }else {
            return rootNode;
        }

    }

    public BTNode<E> search(E item){
        return searchNode(this.root,item);
    }


    private static class BTNode<E> implements Comparable<E>{
        E item;
        BTNode<E> left;
        BTNode<E> right;

        BTNode(BTNode<E> left, E item, BTNode<E> right){
            this.item = item;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(E o) {
            return 0;
        }
    }
}
