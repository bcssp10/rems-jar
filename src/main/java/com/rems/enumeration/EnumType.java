package com.rems.enumeration;

public interface EnumType <K,V> {
	
	public K getKey();
	public V getValue();
	public boolean isDeleted();
	
}
