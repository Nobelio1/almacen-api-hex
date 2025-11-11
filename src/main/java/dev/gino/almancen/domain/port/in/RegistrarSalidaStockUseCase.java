package dev.gino.almancen.domain.port.in;

import dev.gino.almancen.domain.model.Producto;

public interface RegistrarSalidaStockUseCase {
    Producto registrarSalida(Long id, int cantidad);
}
