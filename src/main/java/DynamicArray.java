public class DynamicArray {
    private int[] data;
    private int size;
    public long steps;
    public long moves;
    public long comparisons;

    public DynamicArray() {
        data = new int[10];
        size = 0;
        resetCounters();
    }

    public void resetCounters() {
        steps = 0;
        moves = 0;
        comparisons = 0;
    }

    private void resize() {
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
            moves++;
            steps++;
        }
        data = newData;
    }

    public void add(int x) {
        if (size == data.length) {
            resize();
        }
        data[size] = x;
        size++;
        moves++;
        steps++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == data.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
            moves++;
            steps++;
        }
        data[index] = x;
        size++;
        moves++;
        steps++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        int removed = data[index];
        steps++;
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
            moves++;
            steps++;
        }
        size--;
        return removed;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        steps++;
        return data[index];
    }

    public boolean contains(int x) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            steps++;
            if (data[i] == x) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }
}