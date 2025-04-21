package com.robermejia.utp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.robermejia.utp.entities.Categoria;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    public List<Categoria> findAllByOrderByIdAsc();
}
