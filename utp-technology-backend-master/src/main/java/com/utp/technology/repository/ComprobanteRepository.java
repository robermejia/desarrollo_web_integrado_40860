package com.utp.technology.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utp.technology.model.Comprobante;

@Repository
public interface ComprobanteRepository extends JpaRepository<Comprobante, Integer> {
}
