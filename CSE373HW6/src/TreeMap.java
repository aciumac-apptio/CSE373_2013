// CSE 373: HW6 (3/6/17)

public class TreeMap<K extends Comparable<K>, V> extends AbstractTreeMap<K, V> {

    // Constructs an empty map
    public TreeMap() {
        super();
    }

    // Returns text representation of the map
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        str = toString(super.root, str);
        if (str.length() > 1) {
            str.setLength(str.length()-2);
        }
        str.append("}");

        return str.toString();
    }

    // Private helper that traverses the tree in-order
    private StringBuilder toString(TreeNode node, StringBuilder str) {
        if (node == null) {
            return str;
        } else {
            toString(node.left, str);
            str.append(node.key + "=" + node.value + ", ");
            toString(node.right, str);
        }
        return str;
    }

    // Adds a new key/value pair to map
    // If the key/value exists for a given key, then value is replaced with a new one
    protected TreeNode put(TreeNode node, K key, V value) {
        TreeNode result = super.put(node, key, value);
        result = reBalance(result);
        return result;
    }

    // If the AVL tree is unbalanced, performs required rotations
    private TreeNode reBalance(TreeNode n) {
        if (n != null) {
            if (n.right == null && n.left == null) {
                return n;
            }

            if (balanceFactor(n) < -1) {
                if (balanceFactor(n.left) <= 0) {
                    // R rotation
                    n = rotateRight(n);
                } else if (balanceFactor(n.left) > 0) {
                    // LR rotation
                    n.left = rotateLeft(n.left);
                    n = rotateRight(n);
                }
            } else if (balanceFactor(n) > 1) {
                if (balanceFactor(n.right) >= 0) {
                    // L rotation
                    n = rotateLeft(n);
                } else if (balanceFactor(n.right) < 0) {
                    // RL rotation
                    n.right = rotateRight(n.right);
                    n = rotateLeft(n);
                }
            } else {
                // Check children nodes
                n.left = reBalance(n.left);
                n.right = reBalance(n.right);
            }
        }
        return n;
    }

    // Performs a single right rotation
    private TreeNode rotateRight(TreeNode oldParent) {
        // Detach right subtree of left node
        TreeNode orphan = oldParent.left.right;
        // Left child becomes new parent
        TreeNode newParent = oldParent.left;
        // Attach the "orphan" as LEFT subtree of old parent node
        oldParent.left = orphan;
        // Attach old parent as a right subtree of new parent
        newParent.right = oldParent;

        oldParent.height = super.computeHeight(oldParent);
        newParent.height = super.computeHeight(newParent);
        return newParent;
    }

    // Performs a single left rotation
    private TreeNode rotateLeft(TreeNode oldParent) {
        // Detach left subtree of right node
        TreeNode orphan = oldParent.right.left;
        // Right child becomes new parent
        TreeNode newParent = oldParent.right;
        // Attach the "orphan" as RIGHT subtree of old parent node
        oldParent.right = orphan;
        // Attach the old parent as a left subtree of new parent
        newParent.left = oldParent;

        oldParent.height = super.computeHeight(oldParent);
        newParent.height = super.computeHeight(newParent);
        return newParent;
    }

    // Calculates balance factor
    private int balanceFactor(TreeNode n) {
        // If right branch is longer balance factor is positive
        if (n.right != null && n.left != null) {
            return super.computeHeight(n.right) - super.computeHeight(n.left);
        }
        // Negative balance factor -> tree is left heavy
        return (n.right == null) ? -1 * super.computeHeight(n.left) : super.computeHeight(n.right);
        //return factor;
    }

    // Removes a given key/pair from the map
    protected TreeNode remove(TreeNode node, K key) {
        TreeNode result = super.remove(node, key);
        result = reBalance(result);
        return result;
    }
}