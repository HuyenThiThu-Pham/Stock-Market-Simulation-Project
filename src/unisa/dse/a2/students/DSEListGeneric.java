package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.ListGeneric;

/**
 * @author simont
 *
 */
public class DSEListGeneric<T> implements ListGeneric {
	
	public NodeGeneric<T> head; // Reference to the first node in the list
	private NodeGeneric<T> tail;// Reference to the last node in the list
	private int size; // Size of the list

	//Blank constructor
	public DSEListGeneric() {
		this.head = null;
        this.tail = null;
        this.size = 0;
		
	}
	
	// Constructor accepting one Node, setting head and tail to the same Node
	public DSEListGeneric(NodeGeneric<T> head_) {
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

	//remove and return the item at the parameter's index
	public T remove(int index) {
		
		NodeGeneric<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T toReturn = current.get();

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
        return toReturn;

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		return size;
	}
	
	//returns item at parameter's index
	public void get(int index) {
	}

	//checks if there is a list
	public boolean isEmpty() {
		return false;
	}

	//return the size of the list
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
	public boolean add(Object obj) {
	}

	//add item at parameter's index
	public boolean add(int index, Object obj) {
	}

	//searches list for parameter's String return true if found
	public boolean contains(Object obj) {
	}

	//removes the parameter's item form the list
	public boolean remove(Object obj) {
	}
	
	@Override
	public int hashCode() {
		return 0;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
