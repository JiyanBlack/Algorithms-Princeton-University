# Priority Queue
* PQ maintain the elements' order in terms of its quantitative value.
* deque will give the max/min element.

* Binary heap: In computer science, a heap is a specialized tree-based data structure that satisfies the heap property: If A is a parent node of B then the key (the value) of node A is ordered with respect to the key of node B with the same ordering applying across the heap.

* the parent of node k is k/2
* the child of element k are 2k, 2k+1

* Implementation:
  1. Swim: if an element is bigger than it's parent, swap it with the parent. Recursively run this process.
  2. Insert(Key x): set pq[++N] = x; swim(N);
