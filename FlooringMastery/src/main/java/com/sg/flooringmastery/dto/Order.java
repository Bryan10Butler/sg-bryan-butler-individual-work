package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Order {

    private String orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private Tax taxObject;
    private Product productObject;
    private BigDecimal area;
    private BigDecimal totalMaterialCost;
    private BigDecimal totalLaborCost;
    private BigDecimal totalTax;
    private BigDecimal totalCost;

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getTaxObject() {
        return taxObject;
    }

    public void setTaxObject(Tax taxObject) {
        this.taxObject = taxObject;
    }

    public Product getProductObject() {
        return productObject;
    }

    public void setProductObject(Product productObject) {
        this.productObject = productObject;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getTotalMaterialCost() {
        return totalMaterialCost;
    }

    public void setTotalMaterialCost(BigDecimal totalMaterialCost) {
        this.totalMaterialCost = totalMaterialCost;
    }

    public BigDecimal getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(BigDecimal totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public BigDecimal getTotalTax() {
        return totalTax;
    }

    public void setTotalTax(BigDecimal totalTax) {
        this.totalTax = totalTax;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderNumber() == order.getOrderNumber() &&
                Objects.equals(getOrderDate(), order.getOrderDate()) &&
                Objects.equals(getCustomerName(), order.getCustomerName()) &&
                Objects.equals(getTaxObject(), order.getTaxObject()) &&
                Objects.equals(getProductObject(), order.getProductObject()) &&
                Objects.equals(getArea(), order.getArea()) &&
                Objects.equals(getTotalMaterialCost(), order.getTotalMaterialCost()) &&
                Objects.equals(getTotalLaborCost(), order.getTotalLaborCost()) &&
                Objects.equals(getTotalTax(), order.getTotalTax()) &&
                Objects.equals(getTotalCost(), order.getTotalCost());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getOrderNumber(), getOrderDate(), getCustomerName(), getTaxObject(), getProductObject(), getArea(), getTotalMaterialCost(), getTotalLaborCost(), getTotalTax(), getTotalCost());
    }
}
