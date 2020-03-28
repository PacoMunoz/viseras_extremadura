package es.pmg.viseras.service;

import es.pmg.viseras.domain.Solicitante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Solicitante}.
 */
public interface SolicitanteService {

    /**
     * Save a solicitante.
     *
     * @param solicitante the entity to save.
     * @return the persisted entity.
     */
    Solicitante save(Solicitante solicitante);

    /**
     * Get all the solicitantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Solicitante> findAll(Pageable pageable);

    /**
     * Get the "id" solicitante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Solicitante> findOne(Long id);

    /**
     * Delete the "id" solicitante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
