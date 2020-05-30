package org.saclex.demo.repositories;

import org.saclex.demo.entities.Forfait;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForfaitRepository extends JpaRepository<Forfait,Long> {
}
