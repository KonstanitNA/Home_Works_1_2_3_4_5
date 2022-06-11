package ru.vsu.cs.course1.tree;

public class Test {
    public static void updateTree(BinaryTree<Integer> tree) {
        updateTree(tree, tree.root);
    }

    public static boolean updateTree(BinaryTree<Integer> tree, BinaryTree<Integer>.SimpleTreeNode node) {
        if (node != null) {
            // проверяем левый и правый узел на пустоту
            // (если оба узлы устые, то текущий узел - листок)
            boolean leftSonIsNull = updateTree(tree, node.left);
            boolean rightSonIsNull = updateTree(tree, node.right);
            if (leftSonIsNull && rightSonIsNull) {
                // если текущий узел - листок, то добавляем новый узел
                if (node.getValue() % 2 == 0) {
                    tree.addNode(node, true, 1);
                } else {
                    tree.addNode(node, false, -1);
                }
            }

            return false;
        } else {
            // если текущий узел пустой
            return true;
        }
    }
}