package comp249_A3;
//---------------------------------------------------------
//Assignment 3
//
//Written by: Tony Awaad 40236201
//---------------------------------------------------------
public class DoublyLinkedList<T> {
	// implenting a Node:
		private static class Node<T> {
			private T element;
			private Node<T> next;
			private Node<T> previous;
			

			public Node(T element, Node<T> next,  Node<T> previous) {
				this.element = element;
				this.next = next;
				this.previous = previous;
			}

			public T getElement() {
				return element;
			}

			public void setElement(T element) {
				this.element = element;
			}

			public Node<T> getNext() {
				return next;
			}

			public void setNext(Node<T> next) {
				this.next = next;
			}
			public Node<T> getPrevious() {
				return previous;
			}

			public void setPrevious(Node<T> previous) {
				this.previous = previous;
			}

		}

		// List Implementation:
		private Node<T> head = null;
		private Node<T> tail = null;
		private int size = 0;

		public DoublyLinkedList() {

		}

		public void add(T element) {
			// creates new node and sets the next element to null
			Node<T> newNode = new Node<>(element, null,null);

			// if our list is empty we make our head the new node
			if (isEmpty()) {
				head = newNode;
				tail = newNode;
			} else {
				tail.setNext(newNode);
				newNode.setPrevious(tail);
				tail = newNode;

			}
			size++;

		}

		public void addAtStart(T element) {
			// creates new node and sets the next element to null
			Node<T> newNode = new Node<>(element, head,null);
			head = newNode;
			size++;
		}

		public void addAt(T element, int index) {
			// creates new node and sets the next element to null
			Node<T> newNode = new Node<>(element, null,null);
			// if they put the index at 0
			if (index == 0) {
				addAtStart(element);
				return;
			}
			// we go to the node that is befoer the index and add the pointer to the next
			// element
			// then chaing the pointer of the previous node to the new node
			Node<T> n = head;
			for (int i = 0; i < index - 1; i++) {
				n = n.next;
				if (n.next == null) {
					System.out.println("invalid Index");
					return;
				}
			}
			newNode.setNext(n.next);
			newNode.setPrevious(n.previous);
			n.next = newNode;
			size++;

		}

		public void deleteAt(int index) {
			if (index == 0) {
				head = head.getNext();
				size--;
				return;
			}
			Node<T> n = head;
			Node<T> n1 = null;
			// we loop and reach the posiition right before the index then
			// we set the position at the index to n1
			// and we set the position of n to the element after the index
			for (int i = 0; i < index - 1; i++) {
				n = n.next;
				if (n.next == null) {
					System.out.println("invalid Index");
					return;
				}
			}
			n1 = n.next;
			n.next = n1.next;
			n1 = null; // deleting the element
			size--;
		}

		public int size() {
			return size;
		}

		public boolean isEmpty() {
			return size == 0;
		}

		// getting first element in list
		public T getFirst() {
			if (isEmpty())
				return null;
			return head.getElement();
		}

		public T getLast() {
			if (isEmpty())
				return null;
			return tail.getElement();
		}

		public void printElements() {
			if (isEmpty()) {
				System.out.print("null");
				return;}
			Node<T> node = head;
			while (node.next != null) {
				System.out.println(node.element);
				node = node.next;
			}
			System.out.println(node.element);

		}
		
		public  T getElement(int i) {
			if (i==0) {
				return head.element;}
			if (i==size) {
				return tail.element;
			}
			else {
				int index =0;
				Node<T> node = head;
				while (index<i && node.next!=null) {
					//System.out.println(node);

					node= node.next;
					index++;
					}
				return node.getElement();
			}
			
		}

		
}
