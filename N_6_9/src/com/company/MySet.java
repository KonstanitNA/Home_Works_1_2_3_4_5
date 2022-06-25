package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// класс Множество, основанный на map (собственной или стандартной реализации)
public class MySet<T> {
    Map<T, T> map;

    // конструктор
    public MySet(Map map) {
        this.map = map;
    }

    // получение размера
    public int size() {
        return map.size();
    }

    // проверка на пустоту
    public boolean isEmpty() {
        return size() == 0;
    }

    // проверка наличия ключа
    public boolean contains(Object key) {
        return map.containsKey(key);
    }

    // добавление элемента
    public boolean add(T key) {
        if (map.containsKey(key)) {
            return false;
        }
        else {
            map.put(key, key);
            return true;
        }
    }

    // удаление элемента
    public boolean remove(Object key) {
        return map.remove(key) != null;
    }

    // очистка
    public void clear() {
        map.clear();
    }

    // преобразование к списку
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (Map.Entry<T, T> entry: map.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }
}
