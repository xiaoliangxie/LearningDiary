package com.rfchina.community.excel.admin.service.mq.consumer;

/**
 * @author xiexiaoliang
 * @description
 * @date 2020/7/15 15:20
 */
public class Main {
    static int[] arr = new int[]{1, 2, 3, 4, 5, 6};
    static int[] arr1 = new int[]{7, 8, 9, 10, 11, 12};
    static int[] arr2 = new int[arr.length + arr1.length];

    public static void main(String[] args) {
        int j = 0;
        int k = 0;
        int i = 0;
        while (j < arr.length) {
            arr2[k++] = arr[j++];
        }
        while (i < arr1.length) {
            arr2[k++] = arr1[i++];
        }
        printArr(arr2);
        //bubbleSort(arr2);
        //selectSort(arr2);
        //insertSort(arr2);
        //shellSort(arr2);
        //mergeSort(arr2, 0, arr2.length - 1);
        //quickSort(arr2, 0, arr2.length - 1);
        //heapSort(arr2);
        buildRedBlackTree(arr2);
        //buildBinarySearchTree(arr2);
    }

    /**
     * 二叉搜索树
     *
     * @param arr2
     */
    private static void buildBinarySearchTree(int[] arr2) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        if (arr2.length <= 0) {
            return;
        }
        for (int value : arr2) {
            binarySearchTree.add(value);
        }
        binarySearchTree.remove(arr2[1]);
        binarySearchTree.print();
    }

    private static void buildRedBlackTree(int[] arr2) {
        RedBlackTree redBlackTree = new RedBlackTree();
        for (int value : arr2) {
            redBlackTree.add(value);
        }
        redBlackTree.remove(arr2[0]);
        RedBlackTree.Node node = redBlackTree.get(arr2[0]);
     //   System.out.println(node.value);
        redBlackTree.print();
    }

    public static void heapSort(int[] arr) {
        int len = arr.length;
        int root = (len - 2) / 2;
        // 构建大顶堆，这里其实就是把待排序序列，变成一个大顶堆结构的数组
        for (int i = root; i >= 0; i--) {
            heapify(arr, i, len);
        }

        // 交换堆顶和当前末尾的节点，重置大顶堆
        for (int i = len - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, --len);
        }
    }

    private static void heapify(int[] arr, int i, int len) {
        // 先根据堆性质，找出它左右节点的索引
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        // 默认当前节点（父节点）是最大值。
        int largestIndex = i;
        if (left < len && arr[left] > arr[largestIndex]) {
            // 如果有左节点，并且左节点的值更大，更新最大值的索引
            largestIndex = left;
        }
        if (right < len && arr[right] > arr[largestIndex]) {
            // 如果有右节点，并且右节点的值更大，更新最大值的索引
            largestIndex = right;
        }

        if (largestIndex != i) {
            // 如果最大值不是当前非叶子节点的值，那么就把当前节点和最大值的子节点值互换
            swap(arr, i, largestIndex);
            // 因为互换之后，子节点的值变了，如果该子节点也有自己的子节点，仍需要再次调整。
            heapify(arr, largestIndex, len);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void quickSort(int[] arr2, int start, int end) {
        if (start < end) {
            int temp = qSort(arr2, start, end);
            quickSort(arr2, start, temp - 1);
            quickSort(arr2, temp + 1, end);
        }
    }

    private static int qSort(int[] arr2, int start, int end) {
        int temp = arr2[start];
        while (start < end) {
            while (start < end && arr2[end] > temp) {
                end--;
            }
            if (start < end) {
                arr2[start++] = arr2[end];
            }
            while (start < end && arr2[start] < temp) {
                start++;
            }
            if (start < end) {
                arr2[end--] = arr2[start];
            }
        }
        arr2[start] = temp;
        return start;
    }

    private static void mergeSort(int[] arr2, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(arr2, start, mid);
            mergeSort(arr2, mid + 1, end);
            merge(arr2, start, mid, end);
        }
    }

    private static void merge(int[] arr2, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int i = start;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= end) {
            if (arr2[i] < arr2[j]) {
                temp[k++] = arr2[i++];
            } else {
                temp[k++] = arr2[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = arr2[i++];
        }
        while (j <= end) {
            temp[k++] = arr2[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            arr2[m + start] = temp[m];
        }
    }

    private static void shellSort(int[] arr2) {
        int len = arr2.length;
        int step = len / 2;
        while (step > 0) {
            for (int i = step; i < len; i++) {
                int j = i;
                int target = arr2[j];
                while (j >= step && target < arr2[j - step]) {
                    arr2[j] = arr2[j - step];
                    j = j - step;
                }
                arr2[j] = target;
            }
            step = step / 2;
        }
    }

    private static void printArr(int[] arr) {
        System.out.println();
        for (int value : arr2) {
            System.out.print(value + ",");
        }
    }

    public static void insertSort(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            int temp = arr[j];
            while (j > 0 && temp < arr[j - 1]) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }


    public static void selectSort(int[] arr2) {
        for (int i = 0; i < arr2.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr2.length; j++) {
                if (arr2[minIndex] > arr2[j]) {
                    minIndex = j;
                }
            }
            int temp = arr2[i];
            arr2[i] = arr2[minIndex];
            arr2[minIndex] = temp;
        }
    }
}
