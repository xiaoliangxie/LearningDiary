package com.rfchina.community.excel.admin.service.mq.consumer;

/**
 * @author xiexiaoliang
 * @description
 * @date 2020/7/22 15:15
 */
public class RedBlackTree {
    private static final boolean BLACK = true;
    private static final boolean RED = false;

    Node root;

    public void remove(int value) {
    }

    public void print() {
        if (root != null) {
            recursive(root);
        }
    }

    private void recursive(Node node) {
        if (node.left != null) {
            recursive(node.left);
        }
        System.out.println(node.value + ":" + node.color);
        if (node.right != null) {
            recursive(node.right);
        }
    }

    public Node get(int value) {
        if (root == null) {
            return null;
        }
        Node current = root;
        do {
            if (current.value < value) {
                current = current.right;
            } else if (root.value > value) {
                current = current.left;
            } else {
                return current;
            }
        } while (current != null);
        return null;
    }

    public static class Node {
        //0红色;1黑色
        int color = 1;
        int value;
        Node parent;
        Node left;
        Node right;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
        }
    }

    public void add(int value) {
        Node current;
        Node parent;
        boolean insertFlag = false;
        if (root == null) {
            //根节点
            root = new Node(value, null);
        } else {
            current = root;
            do {
                parent = current;
                if (current.value > value) {
                    current = current.left;
                } else if (current.value < value) {
                    current = current.right;
                    insertFlag = true;
                } else {
                    return;
                }
            } while (current != null);
            Node node = new Node(value, parent);
            if (insertFlag) {
                parent.right = node;
            } else {
                parent.left = node;
            }
            fixAfterInsertion(node);
        }
    }

    private void fixAfterInsertion(Node node) {
        setColor(node, RED);
        //父节点是红色
        while (node != null && node != root && colorOf(node.parent) == RED) {
            //父节点是爷节点的左子树
            if (parentOf(node) == grandFatherOf(node).left) {
                //叔叔节点为红色
                if (colorOf(grandFatherOf(node).right) == RED) {
                    //父节点黑色
                    setColor(parentOf(node), BLACK);
                    //爷爷节点红色
                    setColor(grandFatherOf(node), RED);
                    //叔叔节点黑色
                    setColor(grandFatherOf(node).right, BLACK);
                    //将祖父节点设置为x递归处理
                    node = grandFatherOf(node);
                } else {
                    if (parentOf(node).right == node) {
                        node = parentOf(node);
                        rotateLeft(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(grandFatherOf(node), RED);
                    rotateRight(node);
                }
            } else {
                //变色
                if (colorOf(grandFatherOf(node).left) == RED) {
                    setColor(parentOf(node), BLACK);
                    setColor(grandFatherOf(node), RED);
                    setColor(grandFatherOf(node), RED);
                } else {
                    if (parentOf(node).left == node) {
                        node = parentOf(node);
                        rotateRight(node);
                    }
                    setColor(parentOf(node), BLACK);
                    setColor(grandFatherOf(node), RED);
                    rotateLeft(grandFatherOf(node));
                }
            }
        }
        //根节点位黑色
        setColor(root, BLACK);
    }

    private void rotateRight(Node node) {
        if (node != null) {
            Node s = node.left;
            Node e = s.right;
            node.left = e;
            if (e != null) {
                e.parent = node;
            }
            s.parent = parentOf(node);
            if (parentOf(node) == null) {
                root = s;
            } else if (parentOf(node).left == node) {
                node.parent.left = s;
            } else {
                node.parent.right = s;
            }

            s.right = node;
            node.parent = s;

        }
    }

    private void rotateLeft(Node node) {
        if (node != null) {
            Node s = node.right;
            Node e = s.left;
            node.right = e;
            if (e != null) {
                e.parent = node;
            }
            s.parent = parentOf(e);
            if (parentOf(e) == null) {
                root = node;
            } else if (parentOf(node).left == node) {
                node.parent.left = node;
            } else {
                node.parent.right = node;
            }
            s.left = node;
            node.parent = s;
        }
    }

    private Node grandFatherOf(Node node) {
        return parentOf(parentOf(node));
    }

    private Node parentOf(Node node) {
        return node == null ? null : node.parent;
    }

    private boolean colorOf(Node node) {
        return node == null || node.color == 1;
    }

    private void setColor(Node root, boolean black) {
        root.color = (black ? 1 : 0);
    }
}
