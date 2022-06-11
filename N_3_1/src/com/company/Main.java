package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    // задание:
    // Дана очередь целых чисел.
    // Обработать очередь таким образом, чтобы в очереди остались только четные элементы.

    // с использованием очереди, которая есть в стандартной библиотеке
    public static void onlyEven(Queue<Integer> queue) {
        int n = queue.size(), i = 0;
        while (i < n) {
            Integer element = queue.poll();
            if (element % 2 == 0) {
                queue.add(element);
            }
            i++;
        }
    }

    // с использованием самостоятельно реализованной очереди
    public static void myOnlyEven(MyQueue<Integer> queue) {
        int n = queue.size(), i = 0;
        while (i < n) {
            Integer element = queue.poll();
            if (element % 2 == 0) {
                queue.add(element);
            }
            i++;
        }
    }

    public static void main(String[] args) {
        // создаем очереди - стандартную и собственную (из одинаковых элементов)
        Queue<Integer> queue = new LinkedList<Integer>();
        MyQueue<Integer> myQueue = new MyQueue<>();

        System.out.print("Введите количество элементов: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Введите элементы очереди:");
        for (int i = 0; i < n; i++) {
            int el = scanner.nextInt();
            queue.add(el);
            myQueue.add(el);
        }

        // выполняем задание для каждой очереди
        onlyEven(queue);
        myOnlyEven(myQueue);

        // выводим результат
        System.out.println("Очередь после обработки:");

        System.out.println("Стандартная");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println();

        System.out.println("Реализованная самостоятельно");
        while (!myQueue.isEmpty()) {
            System.out.print(myQueue.poll() + " ");
        }
        System.out.println();
    }




    public static class MyQueue<T> {
        // элемент очереди
        class Node<T> {
            private T value; // значение
            private Node<T> next; // указатель на следующий элемент
            public Node(T val) {
                value = val;
                next = null;
            }
            public T getValue() {
                return value;
            }
            public Node<T> getNext() {
                return next;
            }
            public void setNext(Node<T> n) {
                next = n;
            }
            public void setValue(T val) {
                value = val;
            }
        }

        private Node<T> head; // начало очереди
        private Node<T> tail; // конец очереди
        // конструтор
        public MyQueue() {
            head = null;
            tail = null;
        }

        // конструктор копирования
        public MyQueue(MyQueue<T> list) {
            head = null;
            tail = null;
            Node<T> current = list.head;
            while (current != null) {
                add(current.getValue());
                current = current.getNext();
            }
        }

        // добавления элемента в очередь
        public void add(T value) {
            // если очередь пуста, создаем первый элемент
            if (head == null) {
                head = new Node<T>(value);
                tail = head;
            }
            // иначе добавляем элемент в конец
            else {
                tail.setNext(new Node<T>(value));
                tail = tail.getNext();
            }
        }

        // удаление элемента из очереди
        public T poll() {
            // провреяем на пустоту
            if (head == null) {
                return null;
            }
            else {
                // получаем значение
                T val = head.getValue();
                // удаляем элемент
                head = head.getNext();
                if (head == null) {
                    tail = null;
                }
                // возвращаем значение
                return val;
            }
        }

        // проврека на пустоту
        public boolean isEmpty() {
            return head == null;
        }

        // получение количества элементов в очереди
        public int size() {
            int s = 0;
            Node<T> current = head;
            while (current != null) {
                s++;
                current = current.getNext();
            }
            return s;
        }
    }


}

