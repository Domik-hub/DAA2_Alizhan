public class MinHeap {
    private int[] data;
    private int size;
    public long steps;
    public long moves;
    public long comparisons;

    public MinHeap(int capacity) {
        data = new int[capacity];
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

    public void insert(int x) {
        if (size == data.length) {
            resize();
        }
        data[size] = x;
        size++;
        moves++;
        steps++;
        bubbleUp(size - 1);
    }

    private void bubbleUp(int index) {
        int curr = index;
        while (curr > 0) {
            int parent = (curr - 1) / 2;
            comparisons++;
            steps++;
            if (data[curr] < data[parent]) {
                int temp = data[curr];
                data[curr] = data[parent];
                data[parent] = temp;
                moves += 3;
                steps++;
                curr = parent;
            } else {
                break;
            }
        }
    }

    public int peekMin() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        steps++;
        return data[0];
    }

    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException();
        }
        int min = data[0];
        steps++;
        data[0] = data[size - 1];
        moves++;
        size--;
        if (size > 0) {
            bubbleDown(0);
        }
        return min;
    }

    private void bubbleDown(int index) {
        int curr = index;
        while (2 * curr + 1 < size) {
            int left = 2 * curr + 1;
            int right = 2 * curr + 2;
            int smallest = curr;

            comparisons++;
            steps++;
            if (data[left] < data[smallest]) {
                smallest = left;
            }

            if (right < size) {
                comparisons++;
                steps++;
                if (data[right] < data[smallest]) {
                    smallest = right;
                }
            }

            if (smallest != curr) {
                int temp = data[curr];
                data[curr] = data[smallest];
                data[smallest] = temp;
                moves += 3;
                steps++;
                curr = smallest;
            } else {
                break;
            }
        }
    }

    public int size() {
        return size;
    }
}