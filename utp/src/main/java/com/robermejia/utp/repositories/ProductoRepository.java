package com.robermejia.utp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.robermejia.utp.entities.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    @Query(
        value = "select count(*) as countCategoria from productos where categoria_id = :categoria_id",
        nativeQuery = true
    )
    long countCategoria(Integer categoria_id);
}