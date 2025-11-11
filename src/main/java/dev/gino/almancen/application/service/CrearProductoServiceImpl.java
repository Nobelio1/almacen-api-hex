package dev.gino.almancen.application.service;

import dev.gino.almancen.domain.exception.SkuDuplicadoException;
import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.in.CrearProductoUseCase;
import dev.gino.almancen.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrearProductoServiceImpl implements CrearProductoUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;

    public CrearProductoServiceImpl(ProductoRepositoryPort productoRepositoryPort) {
        this.productoRepositoryPort = productoRepositoryPort;
    }

    @Transactional
    @Override
    public Producto crearProducto(Producto producto) {

        if(productoRepositoryPort.existePorSku(producto.getSku())){
            throw new SkuDuplicadoException("El SKU ya existe");
        }

        return productoRepositoryPort.guardar(producto);
    }
}
