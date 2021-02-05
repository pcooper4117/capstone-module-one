package com.techelevator;

public class Item {
private String name;
private String slotLocation;
private Double price;
private int itemsLeft;
private String type;

public Item(String slotLocation, String name, Double price, String type) {
	this.name = name;
	this.slotLocation = slotLocation;
	this.price = price;
	this.type = type;
	itemsLeft = 5;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSlotLocation() {
	return slotLocation;
}
public void setSlotLocation(String slotLocation) {
	this.slotLocation = slotLocation;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public int getItemsLeft() {
	return itemsLeft;
}
public void setItemsLeft(int itemsLeft) {
	this.itemsLeft = itemsLeft;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

}
