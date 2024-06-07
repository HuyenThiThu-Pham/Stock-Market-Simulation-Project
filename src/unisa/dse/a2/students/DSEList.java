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
		if (index < 0 || index >= size()) { // Check for out of bounds
			return null;
		}
		// Start traversing the list from the head node
		Node current = head;
		// Traverse the list until reaching the node at the parameter's index
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current.getString(); //return the String value of the node at the index
	}

	//checks if there is a list
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	//return the size of the list
	@Override
	public int size() {
		int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
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
		
		if (obj == null) { // Check for null.
			throw new NullPointerException();
		}
		// Create a new node.
		Node newNode = new Node(null,null,obj);
		if (head == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		return true;
	}
		
		

	//add String at parameter's index
	public boolean add(int index, String obj) {
		if (obj == null) { // Check for null.
			throw new NullPointerException();
		}
		if (index < 0 || index > size()) { // Check for out of bounds.
			throw new IndexOutOfBoundsException(index);
		}
		
		Node newNode = new Node(null,null,obj);
		if (index == 0) { // Add to the front.
			// Make next of new node as head and previous as NULL
			newNode.next = head;
			newNode.prev = null;
			//change previous of head node to new node
		    if (head != null) {
		        head.prev = newNode;
		    }
		    //move the head to point to the new node.
		    head = newNode;
		    if (tail == null) { // If the list was empty, set tail to the new node
		        tail = newNode;
		    }
		} else {
			Node current = head;
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
			return true;
		
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
		
		if (obj == null) { // Check for null
	        throw new NullPointerException();
	    }
		
		Node current = head; // Start at the head of the list.
		while (current != null) { // While there are nodes to check...
			if (current.getString().equals(obj)) {
	            if (current == head) { // removing head
	                head = head.next;
	                if (head != null) {
	                    head.prev = null;
	                } else {
	                    tail = null; // The list is now empty
	                }
	            } else if (current == tail) { //removing tail
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
	            return true; // Return true after the node is removed
	        }
	        current = current.next;
		}
		return false; // Return false if the object was not found
		
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
		
		if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        DSEList otherList = (DSEList) other;
        if (size() != otherList.size()) {
            return false;
        }
        
        Node current = head;
        Node otherCurrent = otherList.head;
        while (current != null) {
            if (!current.getString().equals(otherCurrent.getString())) {
                return false;
            }
            current = current.next;
            otherCurrent = otherCurrent.next;
        }
		return true;
	}
	
}
