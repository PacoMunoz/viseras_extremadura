package es.pmg.viseras.repository;

import es.pmg.viseras.domain.Solicitante;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Solicitante entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>, JpaSpecificationExecutor<Solicitante> {
}
