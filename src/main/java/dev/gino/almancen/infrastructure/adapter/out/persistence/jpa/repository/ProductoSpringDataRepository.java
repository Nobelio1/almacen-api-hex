package dev.gino.almancen.infrastructure.adapter.out.persistence.jpa.repository;

import dev.gino.almancen.infrastructure.adapter.out.persistence.jpa.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoSpringDataRepository extends JpaRepository<ProductoEntity, Long> {

    boolean existsBySku(String sku);
}
