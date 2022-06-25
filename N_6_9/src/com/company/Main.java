package com.company;

import java.util.*;

public class Main {

    // алгоритм, который по списку элементов строит другой список,
    // в котором значения из переданного списка встречаются ровно по одному разу
    public static <T> List<T> makeList(List<T> list, Map map) {
        MySet<T> set = new MySet<>(map);
        for (T item : list) {
            set.add(item);
        }
        return set.toList();
    }

    public static void main(String[] args) {
        // создаем список
        List<Integer> list = new ArrayList<>();
        System.out.print("Введите количество элементов списка: ");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("Введите элементы списка:");
        for (int i = 0; i < n; i++) {
            list.add(scanner.nextInt());
        }
        // создаем новый список, в котором значения встречаются по одному разу
        List<Integer> uniqueList = makeList(list, new TreeMap());
        List<Integer> uniqueListMy = makeList(list, new MyMap());
        System.out.println("Список, в котором значения встречаются по одному разу");
        System.out.println("С использованием стандартной реализации:");
        for (Integer item : uniqueList) {
            System.out.print(item + " ");
        }
        System.out.println("\nС использованием собственной реализации:");
        for (Integer item : uniqueListMy) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}