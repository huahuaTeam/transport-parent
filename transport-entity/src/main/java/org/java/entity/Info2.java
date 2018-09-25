package org.java.entity;

public class Info2 {
	private Integer id;
	private String name;
	private Integer age;
	private String city;
	public Info2(){
		
	}
	
	public Info2(Integer id, String name, Integer age, String city) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.city = city;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Info2 [id=" + id + ", name=" + name + ", age=" + age
				+ ", city=" + city + "]";
	}
	
	
}
