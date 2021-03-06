# Priority Queue
* PQ maintain the elements' order in terms of its quantitative value.
* deque will give the max/min element.

* Binary heap: In computer science, a heap is a specialized tree-based data structure that satisfies the heap property: If A is a parent node of B then the key (the value) of node A is ordered with respect to the key of node B with the same ordering applying across the heap.

* the parent of node k is k/2
* the child of element k are 2k, 2k+1

* Implementation of max heap:
  1. Swim: if an element is bigger than it's parent, swap it with the parent. Recursively run this process.
  2. Insert(Key x): set pq[++N] = x; swim(N);
  3.sink: eliminate violation. if parent is smaller than its two children, exchange it with the larger child. Recursively until no violation.
  4. delMax(): exchange N and 1; N--; set N+1 = null; return max.
  
* use resize technique to enable resizing array size for storing the heap.

* Other types of the heap data structure:
  1. binary heap: insert logN, delMax logN, max 1;
  2. d-ary heap: logd N, delMax d logd N, mx 1
  3. Fibonacci: 1, logN, 1
  
* Immutability of keys: assume clients do not change keys. Best practice: use immutable keys.
  * Immutable datatype(like int, char): put the world final before class name, private class variables.
  * Benefits: improve stability, bring confidence to programmers.
* Underflow and overflow. Underflow: throw empty exception. Overflow: resize array.

