package com.bookstore.utils;

import java.util.Collection;
import java.util.Vector;

import com.bookstore.entity.Admin;

public class TestFactory {
	public static Collection<?> generateCollection() {
		Vector collection = new Vector<>();
		collection.add(new Admin("chengjian", "123456"));
		collection.add(new Admin("admin", "123456"));
		collection.add(new Admin("admin2", "123456"));
		collection.add(new Admin("chengjian", "123456"));
		collection.add(new Admin("admin", "123456"));
		collection.add(new Admin("admin2", "123456"));
		return collection;
	}
}
