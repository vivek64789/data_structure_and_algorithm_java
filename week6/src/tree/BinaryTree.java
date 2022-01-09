package tree;


public class BinaryTree {
    public static boolean checkBalancedTreeNode(int[] head, int[] root) {

        boolean lastValue = true;
        for(int i =0; i< head.length;i++) {
            boolean currentCheck = doesChildNodeExists(root, getIndex(root, head[i]));

            lastValue = lastValue && currentCheck;
        }

        return lastValue;
    }


    public static boolean doesChildNodeExists(int[] root, int idx) {

        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        try {
            return root[left] != -1 && root[right] != -1;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public static int getIndex(int[] arrayData, int t) {
        if (arrayData == null) {
            return -1;
        }
        int len = arrayData.length;
        int i = 0;
        while (i < len) {
            if (arrayData[i] == t) {
                return i;
            } else {
                i = i + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] head = { 5, 9 };
        int[] root = {1,4,5,-1,3,2,9,-1,-1,-1,-1,-1,-1,8,7};

        System.out.println(checkBalancedTreeNode(head, root));
    }

}
