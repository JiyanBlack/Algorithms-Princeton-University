# Dynamic Connectivity
* Design efficient data structure for union-find problem. "is connected" is Reflexive, symmetric, transitive
* Connected components: maximal sets of objects that are connected

## Quick Find
* Data structure: an integer array from 0 to n, indexes represent the ith point, the points that are connected share the same value.
* Find connectivity: check if p and q index have the same id value.
* Implementation:
  1. union(p,q) --> change the first one to match the second one. Change p's value to q.
