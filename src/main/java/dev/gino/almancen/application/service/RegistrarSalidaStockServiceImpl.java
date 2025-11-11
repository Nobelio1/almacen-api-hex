package dev.gino.almancen.application.service;

import dev.gino.almancen.domain.exception.NoExisteProductoException;
import dev.gino.almancen.domain.exception.RetiroNoValidoException;
import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.in.RegistrarSalidaStockUseCase;
import dev.gino.almancen.domain.port.out.NotificacionServicePort;
import dev.gino.almancen.domain.port.out.ProductoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class RegistrarSalidaStockServiceImpl implements RegistrarSalidaStockUseCase {

    private final ProductoRepositoryPort productoRepositoryPort;
    private final NotificacionServicePort notificacionServicePort;

    public RegistrarSalidaStockServiceImpl(
            ProductoRepositoryPort productoRepositoryPort,
            NotificacionServicePort notificacionServicePort
    ) {
        this.productoRepositoryPort = productoRepositoryPort;
        this.notificacionServicePort = notificacionServicePort;
    }


    @Override
    public Producto registrarSalida(Long id, int cantidad) {
        Producto producto = productoRepositoryPort.buscarPorId(id).orElseThrow(
                () -> new NoExisteProductoException("No existe el producto con id: " + id)
        );

        if (!producto.retiroValidoStock(cantidad)) {
            throw new RetiroNoValidoException("La cantidad a retirar no es valida");
        }

        producto.retirarStock(cantidad);

        notificacionServicePort.notificarStockBajo(producto);

        return productoRepositoryPort.actualizarStock(producto);
    }
}
