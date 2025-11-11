package dev.gino.almancen.infrastructure.adapter.in.rest.controller;

import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.in.CrearProductoUseCase;
import dev.gino.almancen.domain.port.in.RegistrarEntradaStockUseCase;
import dev.gino.almancen.domain.port.in.RegistrarSalidaStockUseCase;
import dev.gino.almancen.infrastructure.adapter.in.rest.dto.ActualizarProductoRequest;
import dev.gino.almancen.infrastructure.adapter.in.rest.dto.ProductoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final CrearProductoUseCase crearProductoUseCase;
    private final RegistrarEntradaStockUseCase registrarEntradaStockUseCase;
    private final RegistrarSalidaStockUseCase registrarSalidaStockUseCase;

    public ProductoController(
            CrearProductoUseCase crearProductoUseCase,
            RegistrarEntradaStockUseCase registrarEntradaStockUseCase,
            RegistrarSalidaStockUseCase registrarSalidaStockUseCase
    ) {
        this.crearProductoUseCase = crearProductoUseCase;
        this.registrarEntradaStockUseCase = registrarEntradaStockUseCase;
        this.registrarSalidaStockUseCase = registrarSalidaStockUseCase;
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoRequest request) {
        Producto productoDominio = new Producto(request.getSku(), request.getNombre());
        Producto productoCreado = crearProductoUseCase.crearProducto(productoDominio);
        return ResponseEntity.ok(productoCreado);
    }

    @PatchMapping("/{id}/agregar-stock")
    public ResponseEntity<Producto> agregarStock(
            @PathVariable Long id,
            @RequestBody ActualizarProductoRequest request
    ) {
        Producto productoActualizado = registrarEntradaStockUseCase.registrarEntrada(id, request.getCantidad());
        return ResponseEntity.ok(productoActualizado);
    }

    @PatchMapping("/{id}/retirar-stock")
    public ResponseEntity<Producto> retirarStock(
            @PathVariable Long id,
            @RequestBody ActualizarProductoRequest request
    ) {
        Producto productoActualizado = registrarSalidaStockUseCase.registrarSalida(id, request.getCantidad());
        return ResponseEntity.ok(productoActualizado);
    }

}