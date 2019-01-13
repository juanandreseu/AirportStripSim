public interface PriorityQueueInterface<T extends Comparable<T>>{
    /** Adds a new entry to this priority queue

     * @param newEntry An object to be added. */

    void add(T newEntry);
    T remove();

    /** Retrieves the entry having the highest priority.

     * @return Either the object having the highest priority or, if the priority

    queue is empty, null. */

    T peek();

    /** Detects whether this priority queue is empty.

     * @return True if the priority queue is empty, or false otherwise. */

    boolean isEmpty();

    /** Gets the size of this priority queue

     * @return The number of entries currently in the priority queue. */

    int getSize();

    /** Removes all entries from this priority queue. */

    void clear();
}
