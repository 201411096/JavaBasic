package io.objectstream;

import java.io.Serializable;

// 직렬화(Serializable) : 객체가 스트림을 통과하기 위해서

public class Person implements Serializable{ // 추상메소드가 없는 인터페이스
	private String name;
	private int age;
	private double height;
	private char bloodType;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public char getBloodType() {
		return bloodType;
	}
	public void setBloodType(char bloodType) {
		this.bloodType = bloodType;
	}
	
	
}
