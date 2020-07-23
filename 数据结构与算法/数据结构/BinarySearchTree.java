package com.rfchina.community.excel.admin.service.mq.consumer;


import java.util.ArrayList;
import java.util.List;

/**
 * @author xiexiaoliang
 * @description
 * @date 2020/7/22 10:57
 */
public class BinarySearchTree<E extends Comparable<E>> {
    private Node root;

    /**
     * y
     * / \
     * /    \
     * x       \
     * /         \
     * /            \
     *
     * @param value
     */
    public void add(E value) {
        if (root == null) {
            root = new Node(value, null, null, null);
        } else {
            Node current = root;
            Node parent;
            boolean leftRightFlag;
            do {
                parent = current;
                leftRightFlag = value.compareTo(current.value) < 0;
                if (leftRightFlag) {
                    current = parent.left;
                } else {
                    current = parent.right;
                }
            } while (current != null);
            Node node = new Node(value, parent, null, null);
            if (leftRightFlag) {
                parent.left = node;
            } else {
                parent.right = node;
            }
        }
    }

    public void remove(E value) {
        Node deleteNode = get(value);
        while (deleteNode != null) {
            if (deleteNode.left == null && deleteNode.right == null) {
                if (deleteNode == root) {
                    root = null;
                } else if (deleteNode.parent != null) {
                    if (deleteNode.parent.left == deleteNode) {
                        deleteNode.parent.left = null;
                    } else {
                        deleteNode.parent.right = null;
                    }
                }
                deleteNode = null;
            } else if (deleteNode.left != null && deleteNode.right == null) {
                if (deleteNode == root) {
                    //根节点
                    root = deleteNode.left;
                    root.parent = null;
                } else {
                    //非根节点
                    deleteNode.left.parent = deleteNode.parent;
                    deleteNode.parent.left = deleteNode.left;
                }
                deleteNode = null;
            } else if (deleteNode.left == null) {
                if (deleteNode == root) {
                    //根节点
                    root = deleteNode.right;
                    root.parent = null;
                    deleteNode.right = null;
                } else {
                    //非根节点
                    deleteNode.parent.right = deleteNode.right;
                    deleteNode.right.parent = deleteNode.parent;
                }
                deleteNode = null;
            } else {
                //左右子树都不为空
                Node leftMaxNode = deleteNode.left;
                while (leftMaxNode.right != null) {
                    leftMaxNode = leftMaxNode.right;
                }
                deleteNode.value = leftMaxNode.value;
                deleteNode = leftMaxNode;
            }
        }
    }

    public Node get(E value) {
        Node p = root;
        while (p != null && value != null) {
            if (value.compareTo(root.value) < 0) {
                p = p.left;
            } else if (value.compareTo(root.value) > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    public void print() {
        List<Node> nodes = new ArrayList<>();
        if (root != null) {
            recursive(root, nodes);
        }
        for (Node node : nodes) {
            System.out.println(node.value);
        }
    }

    private void recursive(Node root, List<Node> nodes) {
        if (root.left != null) {
            recursive(root.left, nodes);
        }
        nodes.add(root);
        if (root.right != null) {
            recursive(root.right, nodes);
        }
    }


    class Node {
        E value;
        Node parent;
        Node left;
        Node right;

        public Node(E value, Node parent, Node left, Node right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
