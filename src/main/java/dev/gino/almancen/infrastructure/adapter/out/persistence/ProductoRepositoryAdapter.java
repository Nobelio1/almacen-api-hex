package dev.gino.almancen.infrastructure.adapter.out.persistence;

import dev.gino.almancen.domain.model.Producto;
import dev.gino.almancen.domain.port.out.ProductoRepositoryPort;
import dev.gino.almancen.infrastructure.adapter.out.persistence.jpa.entity.ProductoEntity;
import dev.gino.almancen.infrastructure.adapter.out.persistence.jpa.repository.ProductoSpringDataRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class ProductoRepositoryAdapter implements ProductoRepositoryPort {

    private final ProductoSpringDataRepository springDataRepository;

    public ProductoRepositoryAdapter(ProductoSpringDataRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Producto guardar(Producto producto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setSku(producto.getSku());
        entity.setNombre(producto.getNombre());
        entity.setStockActual(producto.getStockActual());

        ProductoEntity entidadGuardada = springDataRepository.save(entity);

        return new Producto(
                entidadGuardada.getId(),
                entidadGuardada.getSku(),
                entidadGuardada.getNombre(),
                entidadGuardada.getStockActual()
        );
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Producto> buscarPorId(Long id) {
        Optional<ProductoEntity> optionalEntity = springDataRepository.findById(id);

        return optionalEntity.map(entity -> new Producto(
                entity.getId(),
                entity.getSku(),
                entity.getNombre(),
                entity.getStockActual()
        ));
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existePorSku(String sku) {
        return springDataRepository.existsBySku(sku);
    }

    @Override
    public Producto actualizarStock(Producto producto) {
        ProductoEntity entity = new ProductoEntity();
        entity.setId(producto.getId());
        entity.setSku(producto.getSku());
        entity.setNombre(producto.getNombre());
        entity.setStockActual(producto.getStockActual());

        ProductoEntity entidadGuardada = springDataRepository.save(entity);
        return new Producto(
                entidadGuardada.getId(),
                entidadGuardada.getSku(),
                entidadGuardada.getNombre(),
                entidadGuardada.getStockActual()
        );
    }
}
