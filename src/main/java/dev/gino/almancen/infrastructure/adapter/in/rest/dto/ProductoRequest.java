package dev.gino.almancen.infrastructure.adapter.in.rest.dto;

import lombok.Data;

@Data
public class ProductoRequest {
    private String sku;
    private String nombre;
}