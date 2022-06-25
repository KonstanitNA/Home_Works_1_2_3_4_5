package com.company;

import java.util.*;

public class MyMap<K extends Comparable<K>, V> implements Map<K, V> {
    // собственная реализация Map на основе бинарного дерева поиска

    // узел дерева
    private class Node {
        private K key; // ключ
        private V value; // значение
        private Node left, right; // левый и правый потомки

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    Node root; // корень дерева

    // конструктор
    public MyMap() {
        root = null;
    }

    // рекурсивная функция добавления элемента в дерево
    private Node insert(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
        }
        else {
            int c = key.compareTo(node.getKey());
            if (c < 0) {
                node.setLeft(insert(node.getLeft(), key, value));
            }
            else if (c > 0) {
                node.setRight(insert(node.getRight(), key, value));
            }
            else {
                node.setValue(value);
            }
        }
        return node;
    }

    // рекурсивная функция поиска элемента в дереве
    private Node find(Node node, K key) {
        if (node == null || node.getKey().equals(key))
            return node;
        if (key.compareTo(node.getKey()) < 0)
            return find(node.getLeft(), key);
        else
            return find(node.getRight(), key);
    }

    // рекурсивная функция удаления элемента из дерева
    private Node removeKey(Node node, K key) {
        if (node == null)
            return null;

        int c = key.compareTo(node.getKey());
        if (c < 0)
            node.setLeft(removeKey(node.getLeft(), key));
        else if (c > 0)
            node.setRight(removeKey(node.getRight(), key));
        else {
            if (node.getRight() != null && node.getLeft() != null) {
                Node tmp = node.getRight();
                while (tmp != null && tmp.getLeft() != null)
                    tmp = tmp.getLeft();
                node.setValue(tmp.getValue());
                node.setRight(removeKey(node.getRight(), tmp.getKey()));
            }
            else {
                if (node.getLeft() == null)
                    return node.getRight();
                else
                    return node.getLeft();
            }
        }
        return node;
    }

    // обход дерева
    private void inorder(Set<Entry<K, V>> set, Node node) {
        if (node != null) {
            inorder(set, node.getLeft());
            set.add(new AbstractMap.SimpleEntry<>(node.getKey(), node.getValue()));
            inorder(set, node.getRight());
        }
    }

    // реализация методов Map

    // получение размера
    @Override
    public int size() {
        return entrySet().size();
    }

    // проверка на пустоту
    @Override
    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    // проверка наличия ключа
    @Override
    public boolean containsKey(Object key) {
        if (root == null || !key.getClass().isInstance(root.getKey()))
            return false;
        return find(root, (K) key) != null;
    }

    // проверка наличия значения
    @Override
    public boolean containsValue(Object value) {
        return values().contains(value);
    }

    // получение значения по ключу
    @Override
    public V get(Object key) {
        if (root == null || !key.getClass().isInstance(root.getKey()))
            return null;
        Node node = find(root, (K) key);
        return node != null ? node.getValue() : null;
    }

    // добавление элемента
    @Override
    public V put(K key, V value) {
        root = insert(root, key, value);
        return get(key);
    }

    // удаление ключа
    @Override
    public V remove(Object key) {
        if (root == null || !key.getClass().isInstance(root.getKey()))
            return null;
        V value = get(key);
        if (value != null) {
            root = removeKey(root, (K) key);
        }
        return value;
    }

    // добавление map
    @Override
    public void putAll(Map<? extends K,? extends V> m) {
        for (Entry<? extends K,? extends V> entry: m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    // удаление всех элементов
    @Override
    public void clear() {
        root = null;
    }

    // получение множества ключей
    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        Set<Entry<K, V>> set = entrySet();
        for (Entry<K, V> entry : set) {
            keys.add(entry.getKey());
        }
        return keys;
    }

    // получение списка значений
    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<>();
        Set<Entry<K, V>> set = entrySet();
        for (Entry<K, V> entry : set) {
            values.add(entry.getValue());
        }
        return values;
    }

    // получение множества пар ключ-значение
    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        inorder(set, root);
        return set;
    }
}
