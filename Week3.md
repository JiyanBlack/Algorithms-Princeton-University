# Sort
## Merge sort
* nlgn, divide and conquer.

## Comparator
* multiple orders

## Stability
* Elements with equal keys perserve their relative order after sorting.
* Insertion sort and merge sort are stable. (Merge sort are stable as long as the merge step is stable)
* Selection sort and shell sort are not. 

## Quick sort
* Qsort with large equal elements can fall into quadratic running time.
* Three way partitioning, divide the array into three parts: less than landmark, equal to landmark, bigger than landmark.
* Steps:
  1. select a landmark.
  2. from i: 0 to n:
    * a[i] < landmark: exchange a[less] with a[i], increment less and i.
    * a[i] > landmark: exchange a[greater] with a[i], decrement greater.
    * a[i] == v: increment i.
  3. sort two parts that are not equal to v.

## System sort
* Application:
  1. sort files/music
  2. find the median
  3. find duplicates
  4. data compression
  5. computer graphics
  6. load balancing
  
* Java use Arrays.sort(): it uses two quicksort for primitives data, two merge sort for objects.
* Tukey's Ninther, Median of Median: choose three groups of 3 entries, the median of their medians are the new landmark.
























