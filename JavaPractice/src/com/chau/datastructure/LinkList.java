package com.chau.datastructure;

class Link<E>{
	public E data;
	public Link<E> nextLink;
	
	public Link(E data){
		this.data = data;
	}
	
	
}

public class LinkList<E> {
	private Link<E> first;
	
	public LinkList(){
		first = null;
	}
	
	public boolean isEmpty() {
		return first == null;
	}
	
	public void insert(E data){
		Link<E> link = new Link<E>(data);
		link.nextLink = first;
		first = link;
	}
	
	public Link<E> delete() {
		Link<E> temp = first;
		first = first.nextLink;
		return temp;
	}
	

}
