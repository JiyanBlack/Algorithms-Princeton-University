# Dynamic Connectivity
* Design efficient data structure for union-find problem. "is connected" is Reflexive, symmetric, transitive
* Connected components: maximal sets of objects that are connected

## Quick Find
* Data structure: an integer array from 0 to n, indexes represent the ith point, the points that are connected share the same value.
* Find connectivity: check if p and q index have the same id value.
* Implementation:
  1. union(p,q) --> change the all elements in the p group to match the second one. Change p's value to q.
  2. connected(p,q) --> return id[p] == id[p]

## Quick Union
* Data structure: integer array id[] of N elements. Relation: id[i] is the parent of i.
* Find: check if p and q have the same root;
* Union: union(p,q) --> set p's root to q's root; Take the first's root as the child of the second item's root.()

## Quick-union improvement
### Improvement 1
* Weighted algorithm: Modify quick-union to avoid tall trees.
  * keep track of size of each tree.
  * Link the root of smaller tree to the root of larger tree.
* Much better performance than simple quick-union.
* Garantee that the maxium depth of this tree is lgN.
* Union and Find operation is at most logN.

### Improvement 2
* set the root of each node to ultimate root. Just add id[i] = id[id[i]] in the finding-root loop. 
* Keep the tree almost completely flat.

## Conclusion
* with quick-union with path compression algorithm, we can solve problem with in N + lg*N time.
* lg*N is almost always very small.(result is the iterative times that lgN becomes one)

# Application - Monte Carlo simulation of percolation
* Percolation: a path connect the top of sites to the bottom.
* A site is enableb at probability p.
* To check the system is percolate or not, create virtual top/bottom nodes, and check those two nodes.
* To calculate precise result, simulations are running many times, so the algorithm is very important.

# Algorithm Analysis
## 3-sum algorithm analysis
* brute-force algorithm, 3 nested-loops will lead to n^3 trials.
* log-log plot, plot log(time) - log(N), the slop is the power.
