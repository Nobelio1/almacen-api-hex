package dev.gino.almancen.domain.port.out;

import dev.gino.almancen.domain.model.Producto;

public interface NotificacionServicePort {

    void notificarStockBajo(Producto producto);
}
