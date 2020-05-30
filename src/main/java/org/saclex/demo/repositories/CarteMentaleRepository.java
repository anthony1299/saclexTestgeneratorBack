package org.saclex.demo.repositories;

import org.saclex.demo.entities.CarteMentale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteMentaleRepository extends JpaRepository<CarteMentale,Long> {
}
