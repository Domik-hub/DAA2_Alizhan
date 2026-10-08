# Report

Alizhan Alikhanov


---

## 1. Objective
The objective of this assignment is to implement fundamental data structures (DynamicArray, MyLinkedList, MinHeap) from scratch without relying on built-in Java collections, collect precise low-level performance metrics (steps, moves, comparisons, time_ms), and conduct a comparative analysis of their asymptotic complexity under various workloads.

---

## 2. Implemented Data Structures

1. **DynamicArray:**
   * Stores data contiguously in a primitive int[] array.
   * Implements amortized capacity doubling (resize) in O(n) time when capacity is exceeded.
   * Provides O(1) random access via get(index), but requires shifting elements in O(n) time during insertions or deletions in the middle or beginning.

2. **MyLinkedList:**
   * Built from Node elements containing integer values and bidirectional references (next and prev).
   * Features optimized bidirectional traversal (getNode): traversal starts from either head or tail depending on which side is closer to the target index.
   * Provides O(1) insertions and deletions at the endpoints, while index lookup and contains() require O(n) linear traversal.

3. **MinHeap:**
   * Implemented over an array representing a complete binary tree where parent nodes are smaller than or equal to their children.
   * Uses bubbleUp during insertions and bubbleDown during minimum extraction (extractMin).
   * Guarantees O(1) peek time (peekMin) and O(log n) time for insertions and extractions.

---

## 3. Workload Scenarios

The benchmark evaluates structures across dataset sizes of n in {100, 1000, 10000, 100000} with JVM warm-up execution followed by 5 measured runs:
* **Workload 1 (Random Access):** 10,000 random get() queries across arbitrary indices.
* **Workload 2 (Search / Contains):** 1,000 search queries (50% existing elements, 50% non-existing).
* **Workload 3 (Insert & Remove):** 1,000 pairs of insertion and deletion operations (add + remove) at the head and middle positions.
* **Workload 4 (Min-Heap Extraction):** Complete heap depletion via sequential extractMin operations n times.

---

## 4. Results and Hardware Trade-offs

### Cache Locality and Execution Time
* **Random Access (W1):** DynamicArray exhibits flat O(1) execution time regardless of n due to spatial locality in contiguous memory, allowing efficient CPU cache utilization. Conversely, MyLinkedList shows linear O(n) growth caused by pointer chasing and frequent CPU cache misses.
* **Insertions (W3):** MyLinkedList performs head insertions in O(1) pointer updates, whereas DynamicArray incurs high overhead from shifting memory blocks via movement loops (moves).

### Heap Performance (MinHeap - W4)
* Workload 4 tests confirm the expected O(log n) logarithmic complexity for heap operations, ensuring stable and predictable execution times through efficient tree balancing.

---

## 5. Conclusion
This assignment successfully reinforced low-level data structure design, benchmark implementation with JVM warm-up protection against JIT optimization anomalies, and performance evaluation considering modern hardware architecture constraints. The empirical results fully align with theoretical Big-O complexity bounds.
