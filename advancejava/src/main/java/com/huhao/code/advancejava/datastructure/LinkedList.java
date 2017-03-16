package com.huhao.code.advancejava.datastructure;

/**
 * Created by huhao on 2017/3/2.
 */
public class LinkedList<E> {

    private int size = 0;

    transient private Node<E> first;

    transient private Node<E> last;

    public LinkedList(){

    }

    public boolean add(E e){
        final Node<E> lastNode = last;
        final Node<E> currentNode = new Node<E>(lastNode,e,null);
        last = currentNode;
        if(lastNode == null){
            first = currentNode;
        }else{
            lastNode.next = currentNode;
        }
        size++;
        return true;
    }

    public boolean remove(Object o){
        if(o == null){
            for(Node<E> node = first;node != null;node = node.next){
                if(node.item == null){
                    removeElement(node);
                    return true;
                }
            }
        }else{
            for(Node<E> node = first;node != null;node = node.next){
                if(node.item.equals(o)){
                    removeElement(node);
                    return true;
                }
            }
        }
        return false;
    }

    private E removeElement(Node<E> e){
        final Node<E> prev = e.prev;
        final Node<E> next = e.next;
        final E element = e.item;
        if(prev == null){
            first = next;

        }else{
            prev.next = next;
            e.prev = null;
        }

        if(next == null){
            last = prev;
        }else{
            next.prev = prev;
            next.next = null;
        }

        size--;
        e.item = null;
        return element;

    }




    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


}
