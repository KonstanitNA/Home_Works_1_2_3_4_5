package Program;

public class SortInfo { // класс для хранения инфомрации о сортировке массива
    private int cmpCount; // кол-во сравнений
    private int chgCount; // кол-во обменов

    public SortInfo(int cmpCount, int chgCount) {
        this.cmpCount = cmpCount;
        this.chgCount = chgCount;
    }

    public int getCmpCount() {
        return cmpCount;
    }

    public int getChgCount() {
        return chgCount;
    }
}
