package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Seller;

public class SellerDTO {

    private Long id;
    private String name;

    public SellerDTO() {
    }

    public SellerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public SellerDTO(Seller entity) {
        id = entity.getId();
        name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
