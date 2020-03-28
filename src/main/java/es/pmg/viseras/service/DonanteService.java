package es.pmg.viseras.service;

import es.pmg.viseras.domain.Donante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Donante}.
 */
public interface DonanteService {

    /**
     * Save a donante.
     *
     * @param donante the entity to save.
     * @return the persisted entity.
     */
    Donante save(Donante donante);

    /**
     * Get all the donantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Donante> findAll(Pageable pageable);

    /**
     * Get the "id" donante.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Donante> findOne(Long id);

    /**
     * Delete the "id" donante.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
