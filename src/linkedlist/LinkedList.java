package linkedlist;

public class LinkedList<T> {
	private Node<T> head = null;
	private Node<T> tail = null;
	private int count = 0;

	static class Node<T> {
		T data;
		Node<T> next;
		
		Node(T d) {
			data = d;
			next = null;
		}
	}
	
	public LinkedList<T> addBack(LinkedList<T> list, T data) {
		
		Node<T> new_node = new Node<T>(data);
		
		if (list.head == null) {
			list.head = new_node;
			list.tail = new_node;
		}
		else {
			list.tail.next = new_node;
			list.tail = new_node;
		}
		
		count++;
		
		return list;
	}
	
	public LinkedList<T> addFront(LinkedList<T> list, T data) {
		
		Node<T> new_node = new Node<T>(data);
		
		if (list.head == null) {
			list.head = new_node;
			list.tail = new_node;
		}
		else {
			new_node.next = list.head;
			list.head = new_node;
		}
		
		if (list.head.next == null)
		{
			list.tail = list.head;
		}
		
		count++;
		
		return list;
	}
	
	public LinkedList<T> deleteFront(LinkedList<T> list) {
		
		if (list.count > 0) {
			
			// garbage collector deletes old head?
			
			list.head = list.head.next;
			
			count--;
			
		}
		
		return list;
	}
	
	public LinkedList<T> deleteBack(LinkedList<T> list) {
		
		if (list.count > 0)
		{
			Node<T> temp = list.head;
			
			while (temp.next != list.tail) {
				temp = temp.next;
			}
			
			temp.next = null;
			list.tail = temp;
			
			count--;
		}
		
		return list;
	}
	
	public LinkedList<T> deleteNode(LinkedList<T> list, T data)
	{
		
		
		return list;
	}
	
}
