package com.company;

public class Main {

    public static void main(String[] args) {
        // созадем объект-генератор
        RandomGenerator randomGenerator = new RandomGenerator();

        // генерируем 10 псевдослучайных целых чисел
        for (int i = 0; i < 10; i++) {
            System.out.print(randomGenerator.nextInt() + " ");
        }
        System.out.println();

        // генерируем 10 псевдослучайных вещественных чисел
        for (int i = 0; i < 10; i++) {
            System.out.printf("%.2f ", randomGenerator.nextDouble());
        }
        System.out.println();

        // генерируем 10 псевдослучайных целых чисел в диапазоне -99 до 99
        for (int i = 0; i < 10; i++) {
            System.out.print(randomGenerator.nextInt(-99, 99) + " ");
        }
        System.out.println();
    }



    // класс генератор псевдослучайных чисел
    public static class RandomGenerator {
        private long next; // сгенерированное число
        // параметры формулы Xn+1 = (aXn + c) mod m
        private long a = 1664525; // множитель
        private long c = 1013904223; // приращение
        private long m = 0x100000000L; // модуль

        // конструктор по умолчанию
        RandomGenerator() {
            // в качестве начала последовательности используем текущее время
            setSeed(System.currentTimeMillis());
        }

        // констурктор с параметром
        RandomGenerator(long seed) {
            setSeed(seed);
        }

        // получение следующего случайного числа
        private void getNext() {
            next = (next * a + c) % m;
        }

        // установка начала последовательности
        public void setSeed(long seed) {
            next = seed;
        }

        // получение целого числа
        public int nextInt() {
            getNext();
            return (int)(next % (Integer.MAX_VALUE + 1));
        }

        // получение целого числа в диапазоне
        public int nextInt(int min, int max) {
            return nextInt() % (max - min + 1) + min;
        }

        // получение вещественного числа
        public double nextDouble() {
            getNext();
            return (double)next/m;
        }
    }


}
