public class MyLinkedList {
    private static class Node {
        int val;
        Node next;
        Node prev;
        Node(int val) {
            this.val = val;
        }
    }

    private Node head;
    private Node tail;
    private int size;
    public long steps;
    public long moves;
    public long comparisons;

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
        resetCounters();
    }

    public void resetCounters() {
        steps = 0;
        moves = 0;
        comparisons = 0;
    }

    private Node getNode(int index) {
        Node curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
                steps++;
            }
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
                steps++;
            }
        }
        steps++;
        return curr;
    }

    public void add(int x) {
        Node newNode = new Node(x);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            moves++;
        }
        size++;
        moves++;
        steps++;
    }

    public void add(int index, int x) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            add(x);
            return;
        }
        if (index == 0) {
            Node newNode = new Node(x);
            if (size == 0) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
                moves++;
            }
            size++;
            moves++;
            steps++;
            return;
        }
        Node succ = getNode(index);
        Node pred = succ.prev;
        Node newNode = new Node(x);
        newNode.prev = pred;
        newNode.next = succ;
        pred.next = newNode;
        succ.prev = newNode;
        size++;
        moves += 3;
        steps++;
    }

    public int remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node target = getNode(index);
        int val = target.val;
        steps++;

        if (size == 1) {
            head = null;
            tail = null;
        } else if (target == head) {
            head = head.next;
            head.prev = null;
            moves++;
        } else if (target == tail) {
            tail = tail.prev;
            tail.next = null;
            moves++;
        } else {
            target.prev.next = target.next;
            target.next.prev = target.prev;
            moves += 2;
        }
        size--;
        return val;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node node = getNode(index);
        return node.val;
    }

    public boolean contains(int x) {
        Node curr = head;
        while (curr != null) {
            comparisons++;
            steps++;
            if (curr.val == x) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    public int size() {
        return size;
    }
}