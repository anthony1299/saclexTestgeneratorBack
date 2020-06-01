package org.saclex.demo.repositories;

import org.saclex.demo.entities.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {

}
