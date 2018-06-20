package com.sg.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private String productType;
    private BigDecimal materialCostPerSquareFoot;
    private BigDecimal labCostPerSquareFoot;

    public Product(String productType, BigDecimal materialCostPerSquareFoot, BigDecimal labCostPerSquareFoot) {
        this.productType = productType;
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
        this.labCostPerSquareFoot = labCostPerSquareFoot;
    }

    public Product(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMaterialCostPerSquareFoot() {
        return materialCostPerSquareFoot;
    }

    public void setMaterialCostPerSquareFoot(BigDecimal materialCostPerSquareFoot) {
        this.materialCostPerSquareFoot = materialCostPerSquareFoot;
    }

    public BigDecimal getLabCostPerSquareFoot() {
        return labCostPerSquareFoot;
    }

    public void setLabCostPerSquareFoot(BigDecimal labCostPerSquareFoot) {
        this.labCostPerSquareFoot = labCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getProductType(), product.getProductType()) &&
                Objects.equals(getMaterialCostPerSquareFoot(), product.getMaterialCostPerSquareFoot()) &&
                Objects.equals(getLabCostPerSquareFoot(), product.getLabCostPerSquareFoot());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getProductType(), getMaterialCostPerSquareFoot(), getLabCostPerSquareFoot());
    }
}
