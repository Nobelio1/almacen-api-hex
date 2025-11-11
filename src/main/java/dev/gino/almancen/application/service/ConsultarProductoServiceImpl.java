package dev.gino.almancen.application.service;

import dev.gino.almancen.domain.exception.NoExisteProductoException;
import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.in.ConsultarProductoUseCase;
import dev.gino.almancen.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class ConsultarProductoServiceImpl implements ConsultarProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public ConsultarProductoServiceImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Override
    public Producto consultarPorId(Long id) {
        Producto producto = productoRepositoryPort.buscarPorId(id).orElseThrow(
                () -> new NoExisteProductoException("No existe el producto con id: " + id)
        );

        return new Producto(
                producto.getId(),
                producto.getNombre(),
                producto.getSku(),
                producto.getStockActual()
        );
    }
}
