package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


    /*
    Дан список из строк. Выбрать и вернуть в виде нового списка те строки, в которых
    содержится заданная подстрока. Исходный список поменяться не должен (т.е. для нового
    списка надо создать новые элементы списка).
     */


public class Main {
    public static void main(String[] args) {
        // создаем список из строк
        Scanner in = new Scanner(System.in);
        List<String> list = new ArrayList<String>();
        System.out.println("Введите список строк (для завершения введите пустую строку)");
        String str;
        do {
            str = in.nextLine();
            if (!str.isEmpty()) {
                list.add(str);
            }
        } while (!str.isEmpty());

        System.out.println("Введите подстроку для поиска (можно оставить пустое значение):");
        String substr = in.nextLine();

        List<String> nlist = findSubstr(list, substr);

        System.out.println("Новый список: " + nlist);
    }

    // метод, создающий новый список
    public static List<String> findSubstr(List<String> list, String substr) {
        List<String> nlist = new ArrayList<String>(); // новый список

        for (String str : list) {
            if (str.contains(substr)) {
                nlist.add(str);
            }
        }
        return nlist;
    }
}
