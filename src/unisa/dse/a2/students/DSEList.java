package unisa.dse.a2.students;

import unisa.dse.a2.interfaces.List;

/**
 * @author simont
 *
 */
public class DSEList implements List {
	
	public Node head;
	private Node tail;

	public DSEList() {
		this.head = null;
		this.tail = null;
		
	}
	public DSEList(Node head_) {
		this.head = head;
		this.tail = head;
	}
	
	//Takes a list then adds each element into a new list
	public DSEList(DSEList other) { // Copy constructor. 
		this();
        if (other.head != null) {
            Node current = other.head;
            while (current != null) {
                add(current.getString());// append elements
                current = current.next;
            }
		}
	}

	//remove the String at the parameter's index
	public String remove(int index) {
		if (index < 0 || index >= size()) {  // Check for out of bounds
			throw new IndexOutOfBoundsException(index); 
		}
		Node current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		
		String toReturn = current.getString();
		
		if (current == head) {
			head = head.next;
			if (head != null) {
	            head.prev = null;
	        } else {
	            tail = null; 
	        }
	    } else if (current == tail) {
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
		
		return toReturn;
		

	}

	//returns the index of the String parameter 
	public int indexOf(String obj) {
		Node current = head;
		int index = 0;
		while (current != null) {
			if (current.getString().equals(obj)) {
				return index;
			}
			current = current.next;
			index++;
		}
		return -1;
	}
	
	//returns String at parameter's index
	public String get(int index) {
		return null;
	}

	//checks if there is a list
	public boolean isEmpty() {
		return false;
	}

	//return the size of the list
	public int size() {
		return 0;
	}
	
	//Take each element of the list a writes them to a string 
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
        	sb.append(current.getString());
            if (current.next != null) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
	}

	//add the parameter String at of the end of the list
	public boolean add(String obj) {
		return false;
	}

	//add String at parameter's index
	public boolean add(int index, String obj) {
		return false;
	}

	//searches list for parameter's String return true if found
	public boolean contains(String obj) {
		if (obj == null) { // Check for null to avoid NullPointerException
	        throw new NullPointerException("The search object cannot be null");
	    }
		Node current = head; // Start at the head of the list.
		while (current != null) { // While there are nodes to check...
			if (obj.equals(current.getString())) { 
	            return true;
	        }
	        current = current.next;
	    }
		return false;
	}

	//removes the parameter's String form the list
	public boolean remove(String obj) {
		return false;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
        Node current = head;
        while (current != null) {
            hash += current.getString().hashCode();
            current = current.next;
        }
        return hash;
	}

	@Override
	public boolean equals(Object other) {
		return true;
	}
	
}
