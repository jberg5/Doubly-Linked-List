// Implementation of lists, using doubly linked elements, and dummy nodes.
// Starter class for List-based lab.
/*
THOUGHT QUESTIONS
----------------
1) We can be sure that no corner cases exist; head and tail are already accounted for. The new element is a "middle" element. Previous and next exist for sure; we don't need to check anymore.
2) Using contains() to write indexOf() would be impossible because contains just returns a boolean, which is not useful information for indexOf().
3) Removing simply unlinks the node at a given position in the list. It wouldn't make much sense to specify before or after in this case. If you want to remove the element before the node at position three, that's just the node at position 2, which you can specify just as easily. In contrast, adding an element can't reference a position that doesn't exist yet, so it must be either before or after an existing entry.
4) Handling inserting at the beginning or end guarantees that we don't accidentally create a new head or tail. 
5) the original
---------------
 */
import structure5.Assert;
import structure5.DoublyLinkedList;
import structure5.DoublyLinkedNode;
import structure5.DoublyLinkedListIterator;
import java.util.Iterator;

/*
A doubly linked list with dummy heads and tails. Supports the usual list interface. "MODIFIED" is for me.
- Jonathan Berg
 */
public class LinkedList<E> extends DoublyLinkedList<E>
{
    /**
     * Number of elements within the list.
     */
    protected int count;
    /**
     * Reference to head of the list.
     */
    protected DoublyLinkedNode<E> head;
    /**
     * Reference to tail of the list.
     */
    protected DoublyLinkedNode<E> tail;

    /**
     * Constructs an empty list.
     *
     * @post constructs an empty list
     * 
     */
    public LinkedList()
    {
	// Students: modify this code.
	//MODIFIED
	head = new DoublyLinkedNode<E>(null);
	tail = new DoublyLinkedNode<E>(null);
	head.setNext(tail);
	tail.setPrevious(head);
	count = 0;
    }

    /**
     * Determine the number of elements in the list.
     *
     * @post returns the number of elements in list
     * 
     * @return The number of elements found in the list.
     */
    public int size()
    {
	return count;
    }

    /**
     * Determine if the list is empty.
     *
     * @post returns true iff the list has no elements.
     * 
     * @return True iff list has no values.
     */
    public boolean isEmpty()
    {
	return size() == 0;
    }

    /**
     * Remove all values from list.
     *
     * @post removes all the elements from the list
     */
    public void clear()
    {
	// Students: modify this code
	//MODIFIED
        head.setNext(tail); 
	tail.setPrevious(head); 
	count = 0;
    }

    /**
     * A private routine to add an element after a node.
     * @param value the value to be added
     * @param previous the node the come before the one holding value
     * @pre previous is non null
     * @post list contains a node following previous that contains value
     */
    protected void insertAfter(E value, DoublyLinkedNode<E> previous)
    {
	assert areYouOk() : "insertAfter is broken!";
	// Students: write this code.
	//MODIFIED
	DoublyLinkedNode<E> newElement = new DoublyLinkedNode<E>(value);
	DoublyLinkedNode<E> nextElement = previous.next();

	previous.setNext(newElement);
	nextElement.setPrevious(newElement);
	newElement.setPrevious(previous);
	newElement.setNext(nextElement);

	count++;
    }

    /**
     * A private routine to remove a node.
     * @param node the node to be removed
     * @pre node is non null
     * @post node node is removed from the list
     * @return the value of the node removed
     */
    protected E remove(DoublyLinkedNode<E> node)
    {
	assert areYouOk() : "remove is broken";
	// Students: write this code
	// MODIFIED
	DoublyLinkedNode<E> prevElement = node.previous();
	DoublyLinkedNode<E> nextElement = node.next();

	prevElement.setNext(nextElement);
	nextElement.setPrevious(prevElement);

	count--;
	
	return node.value();
    }

    
    /**
     * Add a value to the head of the list.
     *
     * @pre value is not null
     * @post adds element to head of list
     * 
     * @param value The value to be added.
     */
    public void addFirst(E value)
    {
	// Students: modify this code.
	// MODIFIED
	insertAfter(value, head);
	
    }

    /**
     * Add a value to the tail of the list.
     *
     * @pre value is not null
     * @post adds new value to tail of list
     * 
     * @param value The value to be added.
     */
    public void addLast(E value)
    {
	// Students: modify this code.
	// MODIFIED
	insertAfter(value,tail.previous());
	
    }

    /**
     * Remove a value from the head of the list.
     * Value is returned.
     *
     * @pre list is not empty
     * @post removes first value from list
     * 
     * @return The value removed from the list.
     */
    public E removeFirst()
    {
	// Students: modify this code.
	// MODIFIED
	return remove(head.next());
    }

    /**
     * Remove a value from the tail of the list.
     *
     * @pre list is not empty
     * @post removes value from tail of list
     * 
     * @return The value removed from the list.
     */
    public E removeLast()
    {
	// Students: modify this code.
	// MODIFIED
	return remove(tail.previous());
    }

    /**
     * Get a copy of the first value found in the list.
     *
     * @pre list is not empty
     * @post returns first value in list.
     * 
     * @return A reference to first value in list.
     */
    public E getFirst()
    {
	// Students: modify this code.
	// MODIFIED
	return head.next().value();
    }

    /**
     * Get a copy of the last value found in the list.
     *
     * @pre list is not empty
     * @post returns last value in list.
     * 
     * @return A reference to the last value in the list.
     */
    public E getLast()
    {
	// Students: modify this code.
	// MODIFIED
	return tail.previous().value();
    }

    /**
     * Insert the value at location.
     *
     * @pre 0 <= i <= size()
     * @post adds the ith entry of the list to value o
     * @param i the index of this new value
     * @param o the the value to be stored
     */
    public void add(int i, E o)
    {
	// Students: modify this code.
	Assert.pre((0 <= i) &&
		   (i <= size()), "Index in range."); 
	assert areYouOk() : "add is broken";
	
	DoublyLinkedNode<E> before = head;
	DoublyLinkedNode<E> after = head.next();

	while (i > 0) {
	    before = after;
	    after = after.next();
	    i--;
	}

	insertAfter(o,after);
	
    }

    /**
     * Remove and return the value at location i.
     *
     * @pre 0 <= i < size()
     * @post removes and returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E remove(int i)
    {
	// Students: modify this code.
	// MODIFIED
	Assert.pre((0 <= i) && (i < size()), "Index in range.");
	assert areYouOk() : "remove is broken";
	
	DoublyLinkedNode<E> before = head;
	DoublyLinkedNode<E> after = head.next();

	while (i > 0) {
	    before = after;
	    after = after.next();
	    i--;
	}

	return remove(after);
    }

    /**
     * Get the value at location i.
     *
     * @pre 0 <= i < size()
     * @post returns the object found at that location.
     *
     * @param i the position of the value to be retrieved.
     * @return the value retrieved from location i (returns null if i invalid)
     */
    public E get(int i)
    {
	// Students: modify this code.
	// MODIFIED
	DoublyLinkedNode<E> finger = head.next();
	// search for the ith element or end of list
	while (i > 0)
	{
	    finger = finger.next();
	    i--;
	}
	// not end of list, return the value found
	return finger.value();
    }

    /**
     * Set the value stored at location i to object o, returning the old value.
     *
     * @pre 0 <= i < size()
     * @post sets the ith entry of the list to value o, returns the old value.
     * @param i the location of the entry to be changed.
     * @param o the new value
     * @return the former value of the ith entry of the list.
     */
    public E set(int i, E o)
    {
	// Students: modify this code.
	// MODIFIED Question: This and get() required barely any changes, did I screw up?
 	DoublyLinkedNode<E> finger = head.next();
	// search for the ith element or the end of the list
	while (i > 0)
	{
	    finger = finger.next();
	    i--;
	}
	// get old value, update new value
	E result = finger.value();
	finger.setValue(o);
	return result;
    }

    /**
     * Determine the first location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     * 
     * @param value The value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int indexOf(E value)
    {
	// Students: modify this code.
	// MODIFIED
	int i = 0;
	DoublyLinkedNode<E> finger = head.next();
	// search for value or end of list, counting along the way
	while (i < size()-1 && !finger.value().equals(value))
	{
	    finger = finger.next();
	    i++;
	}
	//value not found, return -1
	if (i == size()-1) {
	    return -1;
	} else { // value found, return position
	    return i;
	}
    }

    /**
     * Determine the last location of a value in the list.
     *
     * @pre value is not null
     * @post returns the (0-origin) index of the value,
     *   or -1 if the value is not found
     * 
     * @param value the value sought.
     * @return the index (0 is the first element) of the value, or -1
     */
    public int lastIndexOf(E value)
    {
	// Students: modify this code.
	// MODIFIED
	int i = size()-1;
	DoublyLinkedNode<E> finger = tail.previous();
	// search for the last matching value, result is desired index
	while (i > 0 && !finger.value().equals(value))
	{
	    finger = finger.previous();
	    i--;
	}
	if (i == 0)
	{   // value not found, return indicator
	    return -1;
	} else {
	    // value found, return index
	    return i;
	}
    }

    /**
     * Check to see if a value is within the list.
     *
     * @pre value not null
     * @post returns true iff value is in the list
     * 
     * @param value A value to be found in the list.
     * @return True if value is in list.
     */
    public boolean contains(E value)
    {
	// Students: modify this code.
	// MODIFIED
	return (indexOf(value) != -1);
    }

    /**
     * Remove a value from the list.  At most one value is removed.
     * Any duplicates remain.  Because comparison is done with "equals,"
     * the actual value removed is returned for inspection.
     *
     * @pre value is not null.  List can be empty.
     * @post first element matching value is removed from list
     * 
     * @param value The value to be removed.
     * @return The value actually removed.
     */
    public E remove(E value)
    {
	// Students: modify this code.
	DoublyLinkedNode<E> finger = head.next();
	int i = 0;
	while (i <= size() + 1 && !finger.value().equals(value))
	{
	    finger = finger.next();
	}
	if (i != size() + 1) {
	    remove(finger);
	    return finger.value();
	} else {
	    return null;
	}
    }

    /**
     * Construct an iterator to traverse the list.
     *
     * @post returns iterator that allows the traversal of list.
     * 
     * @return An iterator that traverses the list from head to tail.
     */
    public Iterator<E> iterator()
    {
	// Students: This code should not be modified
	return new DoublyLinkedListIterator<E>(head,tail);
    }

    /**
     * Construct a string representation of the list.
     *
     * @post returns a string representing list
     * 
     * @return A string representing the elements of the list.
     */
    public String toString()
    {
	StringBuffer s = new StringBuffer();
	s.append("<LinkedList:");
	Iterator<E> li = iterator();
	while (li.hasNext())
	{
	    s.append(" "+li.next());
	}
	s.append(">");
	return s.toString();
    }

    public boolean areYouOk() {
	DoublyLinkedNode<E> hand = head.next();
	for(int i = 0; i < count; ++i) {
	    //Previous node's next must be current node, next node's previous must be current node
	    if((hand.previous().next() != hand) || (hand.next().previous() != hand)) {
		System.out.println("The link is broken somewhere");
		return false;
	    }
	}
	    //Checks integrity of head and tail
        if((head.previous() != null) || (tail.next() != null)) {
	    System.out.println("Head and tail are compromised");
	    return false;
	}
	return true;
    }
    
}


