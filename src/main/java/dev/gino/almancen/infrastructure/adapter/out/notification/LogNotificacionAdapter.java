package dev.gino.almancen.infrastructure.adapter.out.notification;

import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.out.NotificacionServicePort;
import org.springframework.stereotype.Component;

@Component
public class LogNotificacionAdapter implements NotificacionServicePort {

    @Override
    public void notificarStockBajo(Producto producto) {
        if(producto.getStockActual() < 10) {
            System.out.println("ALERTA: El producto con SKU " + producto.getSku() + " tiene un stock bajo: " + producto.getStockActual());
        }
    }
}
