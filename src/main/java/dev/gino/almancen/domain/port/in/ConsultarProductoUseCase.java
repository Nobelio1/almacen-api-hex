package dev.gino.almancen.domain.port.in;

import dev.gino.almancen.domain.model.Producto;

public interface ConsultarProductoUseCase {
    Producto consultarPorId(Long id);
}
