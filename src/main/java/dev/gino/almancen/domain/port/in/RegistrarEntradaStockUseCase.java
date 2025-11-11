package dev.gino.almancen.domain.port.in;

import dev.gino.almancen.domain.model.Producto;

public interface RegistrarEntradaStockUseCase {
    Producto registrarEntrada(Long id, int cantidad);
}
