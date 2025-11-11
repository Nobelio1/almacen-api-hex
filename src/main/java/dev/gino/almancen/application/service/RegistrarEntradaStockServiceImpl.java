package dev.gino.almancen.application.service;

import dev.gino.almancen.domain.exception.NoExisteProductoException;
import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.in.RegistrarEntradaStockUseCase;
import dev.gino.almancen.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegistrarEntradaStockServiceImpl implements RegistrarEntradaStockUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public RegistrarEntradaStockServiceImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto registrarEntrada(Long id, int cantidad) {
        Producto producto = productoRepositoryPort.buscarPorId(id).orElseThrow(
                () -> new NoExisteProductoException("No existe el producto con id: " + id)
        );

        producto.agregarStock(cantidad);
        return productoRepositoryPort.actualizarStock(producto);
    }
}
