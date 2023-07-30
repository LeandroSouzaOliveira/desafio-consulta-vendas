package com.devsuperior.dsmeta.dto;

public class SellerMinDTO {

    private String name;
    private Double sales;

    public SellerMinDTO(String name, Double sales) {
        this.name = name;
        this.sales = sales;
    }

    public String getName() {
        return name;
    }

    public Double getSales() { return sales; }
}
