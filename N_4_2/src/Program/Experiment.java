package Program;

import org.jfree.data.xy.XYSeries;

import java.util.Arrays;
import java.util.Random;

public class Experiment { // класс эксперимента

    private static Integer[] generate(int size) { // генератор списка случайных чисел от -50 до 50
        Random rnd = new Random();
        int from = -50;
        int len = 99;
        Integer[] array = new Integer[size];
        for (int i = 0; i < size; i++) {
            array[i] = from + rnd.nextInt(len);
        }
        return array;
    }

    public static ExperimentInfo experiment(int maxSize) throws Exception { // проводим эксперимент
        ExperimentInfo ei = new ExperimentInfo(maxSize); // создаём информацию об эксперименте
        // её будем использовать для отображения графиков
        for (int i = 0; i < maxSize; i++) {
            Integer[] arr = generate(i); // генерируем массив размером i
            // сортируем сгенерированный массив матодом вставок и сохраняем в инфу об эксперименте
            ei.setInsertionSortResults(i, Sorts.insertionInfo(Arrays.copyOf(arr, arr.length)));
            // то же самое только методом бинарныъ вставок
            ei.setBinaryInsertionSortResults(i, Sorts.binaryInsertionInfo(arr));
        }
        return ei;
    }

    public static void applyInsertionCmpLine(ExperimentInfo info, XYSeries series) {
        // добавляем график сравнений для метода вставками
        series.clear();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getInsertionSortResults(i).getCmpCount());
        }
    }

    public static void applyInsertionChgLine(ExperimentInfo info, XYSeries series) {
        // добавляем график обменов для метода вставками
        series.clear();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getInsertionSortResults(i).getChgCount());
        }
    }

    public static void applyBinaryInsertionCmpLine(ExperimentInfo info, XYSeries series) {
        // добавляем график сравнений для метода бинарными вставками
        series.clear();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getBinaryInsertionSortResults(i).getCmpCount());
        }
    }

    public static void applyBinaryInsertionChgLine(ExperimentInfo info, XYSeries series) {
        // добавляем график обменов для метода бинарными вставками
        series.clear();
        int size = info.getSize();
        for (int i = 0; i < size; i++) {
            series.add(i, info.getBinaryInsertionSortResults(i).getChgCount());
        }
    }
}
