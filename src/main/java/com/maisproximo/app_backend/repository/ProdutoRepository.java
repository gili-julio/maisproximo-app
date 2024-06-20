package com.maisproximo.app_backend.repository;

import com.maisproximo.app_backend.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
