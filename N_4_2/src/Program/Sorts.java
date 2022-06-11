package Program;

public class Sorts { // класс сортировок

    // более подробно о самих алгоритмах здесь
    // https://habr.com/ru/post/415935/
    public static SortInfo insertionInfo(Integer[] array) { // сортировка вставками
        int cmp = 0, chg = 0; // кол-во сравнений и обменов

        for (int j = 0; j < array.length; j++) {
            Integer key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                chg++;
                array[i + 1] = array[i];
                i = i - 1;
                cmp++;
            }
            array[i + 1] = key;
        }

        return new SortInfo(cmp, chg);
    }

    public static SortInfo binaryInsertionInfo(Integer[] array) { // бинарная сортировка вставками
        int cmp = 0, chg = 0;

        for (int i = 1; i < array.length; i++) {
            Integer current = array[i];
            int left = 0, right = i;

            while (left < right) {
                cmp++;
                int mid = left + (right - left) / 2;
                cmp++;
                if (current < array[mid])
                    right = mid;
                else
                    left = mid + 1;
            }
            cmp++;

            for(int j = i; j > left; j--) {
                chg++;
                array[j] = array[j - 1];
            }
            chg++;
            array[left] = current;
        }

        return new SortInfo(cmp, chg);
    }
}
