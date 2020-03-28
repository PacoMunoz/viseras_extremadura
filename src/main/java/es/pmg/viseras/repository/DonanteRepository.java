package es.pmg.viseras.repository;

import es.pmg.viseras.domain.Donante;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Donante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DonanteRepository extends JpaRepository<Donante, Long>, JpaSpecificationExecutor<Donante> {
}
