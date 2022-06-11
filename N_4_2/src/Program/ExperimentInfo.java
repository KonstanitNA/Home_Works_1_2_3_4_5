package Program;

public class ExperimentInfo { // класс для хранения информации о результатах эсперимента
    // кроме конструктора, геттеров и сеттеров здесь ничего нет
    private SortInfo[] insertionSortResults;
    private SortInfo[] binaryInsertionSortResults;

    public ExperimentInfo(int size) {
        insertionSortResults = new SortInfo[size];
        binaryInsertionSortResults = new SortInfo[size];
    }
    public int getSize() {
        return insertionSortResults.length;
    }

    public SortInfo getInsertionSortResults(int index) {
        return insertionSortResults[index];
    }

    public void setInsertionSortResults(int index, SortInfo si) throws Exception {
        if (index < 0 || index >= insertionSortResults.length)
            throw new Exception("Incorrect Size");
        this.insertionSortResults[index] = si;
    }

    public SortInfo getBinaryInsertionSortResults(int index) {
        return binaryInsertionSortResults[index];
    }

    public void setBinaryInsertionSortResults(int index, SortInfo si) throws Exception {
        if (index < 0 || index >= binaryInsertionSortResults.length)
            throw new Exception("Incorrect Size");
        this.binaryInsertionSortResults[index] = si;
    }
}
