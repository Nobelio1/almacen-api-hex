package dev.gino.almancen.domain.port.out;

import dev.gino.almancen.domain.model.Producto;

import java.util.Optional;

public interface ProductoRepositoryPort {

    Producto guardar(Producto producto);

    Optional<Producto> buscarPorId(Long id);

    boolean existePorSku(String sku);

    Producto actualizarStock(Producto producto);

}