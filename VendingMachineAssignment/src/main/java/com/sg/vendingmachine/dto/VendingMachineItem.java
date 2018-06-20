package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class VendingMachineItem {

    private String itemId;
    private String name;
    private int quantity;
    private BigDecimal price;

    //Constructor with read only itemId
    public VendingMachineItem(String itemId) {
        this.itemId = itemId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VendingMachineItem)) return false;
        VendingMachineItem that = (VendingMachineItem) o;
        return getQuantity() == that.getQuantity() &&
                Objects.equals(getItemId(), that.getItemId()) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getItemId(), getName(), getQuantity(), getPrice());
    }

    //easily able to write the string representation of a
    //snack to the audit log
    @Override
    public String toString() {
        return "Item ID: " + itemId + "|Name: " + name
                + "|Quantity: " + quantity + "|Price " + price;
    }
}
