# Stacks and queues
* Stack: LIFO
* Queue: FIFO
* Bag: add into a collect, the order does not matter.
* Seperate interface and implementation.

1. Linked list stack implementation: fast, take more space.
2. Array stack implementation: defect is that you have to declare the size of array, stack overflow.
   * Underflow: if stack is empty, pop() raise an error;
   * Overflow: use resizing method.
   * Loitering: set removed item to null
   * Resizing: 
      1. Ensure that array resizing happens infrequently
      2. Repeated doubling: double size when array is full, halving size when array is one quarter full. So the array is always 
      between 25%-100% full.
3. Linked list Queue impelementation: same as stack implementation
4. Array queue implementation: need to reset the first element to 0th position whenever resizing it.

# Generics into different datatypes
* Solution one: create different types of stacks for each type.
* Better solution -- Generic: 
  public class stack<Item> {...}, Item becomes the type of the data type.
 
