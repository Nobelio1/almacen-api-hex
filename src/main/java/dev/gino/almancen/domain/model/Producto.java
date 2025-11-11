package dev.gino.almancen.domain.model;

public class Producto {

    private Long id;
    private String sku;
    private String nombre;
    private int stockActual;

    public Producto(String sku, String nombre) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("El SKU no puede estar vacío.");
        }
        this.sku = sku;
        this.nombre = nombre;
        this.stockActual = 0;
    }

    public Producto(Long id, String sku, String nombre, int stockActual) {
        this.id = id;
        this.sku = sku;
        this.nombre = nombre;
        this.stockActual = stockActual;
    }

    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.stockActual += cantidad;
        }
    }

    public void retirarStock(int cantidad) {
        if (cantidad > 0) {
            this.stockActual -= cantidad;
        }
    }

    public boolean retiroValidoStock(int cantidad) {
        return this.stockActual - cantidad >= 0;
    }

    public Long getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStockActual() {
        return stockActual;
    }
}
