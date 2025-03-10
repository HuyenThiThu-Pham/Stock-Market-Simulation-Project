package unisa.dse.a2.students;

import java.util.Iterator;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric<T> {

    public NodeGeneric<T> head; // Reference to the first node in the list
    private NodeGeneric<T> tail; // Reference to the last node in the list
    private int size; // Size of the list

    // Blank constructor
    public DSEListGeneric() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
	
	// Constructor accepting one Node, setting head and tail to the same Node
	public DSEListGeneric(NodeGeneric<T> head) {
		this.head = head;
        this.tail = head;
        this.size = calculateSize();
	}
	
	//Takes a list then adds each element into a new list
	private int calculateSize() {
		int count = 0;
        NodeGeneric<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
	}

	// Constructor accepting a DSEListGeneric
	public DSEListGeneric(DSEListGeneric<T> other){ // Copy constructor. 
		this(); // Call the blank constructor to initialize
        if (other.head != null) {
            NodeGeneric<T> current = other.head;
            while (current != null) {
                add(current.get());
                current = current.next;
            }
        }
	}

	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	//remove and return the item at the parameter's index
	public T remove(int index) {
		//Bound check
		if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(" Index:" + index + ",Size:" + size);
        }
		
		NodeGeneric<T> current = head; // traversing the list from the beginning.
        for (int i = 0; i < index; i++) {
            current = current.next; //update the current pointer to the next node of the list
        }

        T toReturn = current.get(); 

        if (current == head) { //removed node is the head of the list
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null; 
            }
        } else if (current == tail) { //removed node is the tail of the list
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null; 
            }
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--; // Decrease the size of the list
        return toReturn;

	}

	// Returns the index of the parameter, or -1 if not found
	public int indexOf(T obj) {
		NodeGeneric<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.get().equals(obj)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
	}
	
	//returns item at parameter's index
	public T get(int index) {
		if (index < 0 || index >= size) { // Check for out of bounds
			return null;
        }
		
		NodeGeneric<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.get();
	}

	//checks if there is a list
	@Override
	public boolean isEmpty() {
		 return head == null;
	}

	//return the size of the list
	@Override
	public int size() {
		return size;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        NodeGeneric<T> current = head;
        while (current != null) {
            sb.append(current.get());
            if (current.next != null) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
	}

	//add the parameter item at of the end of the list
	@Override
	public boolean add(T obj) {
		if (obj == null) { // Check for null.
            throw new NullPointerException();
        }
		
        // Create a new node.
		NodeGeneric<T> newNode = new NodeGeneric<>(null, null, obj);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++; // Increase the size of the list
        return true;
	}

	//add item at parameter's index
	public boolean add(int index, T obj) {
		if (obj == null) { // Check for null.
            throw new NullPointerException();
        }
        if (index < 0 || index > size) { // Check for out of bounds.
            throw new IndexOutOfBoundsException();
        }
        
        // Create a new node.
		NodeGeneric<T> newNode = new NodeGeneric<>(null, null, obj);
        if (index == 0) { // Add to the front.
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) { // If the list was empty, set tail to the new node
                tail = newNode;
            }
        } else {
            NodeGeneric<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            if (current.next != null) {
                current.next.prev = newNode;
            } else {
                tail = newNode; // If inserting at the end, update the tail
            }
            newNode.prev = current;
            current.next = newNode;
        }
        size++; // Increase the size of the list
        return true;
	}

	//searches list for parameter's String return true if found
	public boolean contains(T obj) {
		if (obj == null) { // Check for null to avoid NullPointerException
            throw new NullPointerException("The search object cannot be null");
        }
        NodeGeneric<T> current = head; // Start at the head of the list.
        while (current != null) { // While there are nodes to check...
            if (obj.equals(current.get())) { 
                return true;
            }
            current = current.next;
        }
        return false;
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
		
		if (obj == null) { // Check for null
            throw new NullPointerException();
        }
		
		NodeGeneric<T> current = head; // Start at the head of the list.
        while (current != null) { // While there are nodes to check...
            if (current.get().equals(obj)) {
                if (current == head) {
                    head = head.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null; // The list is now empty
                    }
                } else if (current == tail) {
                    tail = tail.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null; // The list is now empty
                    }
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                size--; // Decrease the size of the list
                return true; // Return true after the node is removed
            }
            current = current.next;
        }
        return false; // Return false if the object was not found
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
        NodeGeneric<T> current = head;
        while (current != null) {
            hash += current.get().hashCode();
            current = current.next;
        }
        return hash;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DSEListGeneric<?> otherList = (DSEListGeneric<?>) other;
        if (size() != otherList.size()) {
            return false;
        }
        NodeGeneric<T> current = head;
        NodeGeneric<?> otherCurrent = otherList.head;
        while (current != null) {
            if (!current.get().equals(otherCurrent.get())) {
                return false;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
        return true;
	}
	
	// Implement iterator to provide iteration capability over the list
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private NodeGeneric<T> current = head; // Start with the head of the list

            // Check if there is a next element in the list
            @Override
            public boolean hasNext() {
                return current != null;
            }

            // Return the next element in the list and move the pointer forward
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                T data = current.get();
                current = current.next;
                return data;
            }
        };
    }
	
}
