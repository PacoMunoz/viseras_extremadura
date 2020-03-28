package es.pmg.viseras.service.impl;

import es.pmg.viseras.service.SolicitanteService;
import es.pmg.viseras.domain.Solicitante;
import es.pmg.viseras.repository.SolicitanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Solicitante}.
 */
@Service
@Transactional
public class SolicitanteServiceImpl implements SolicitanteService {

    private final Logger log = LoggerFactory.getLogger(SolicitanteServiceImpl.class);

    private final SolicitanteRepository solicitanteRepository;

    public SolicitanteServiceImpl(SolicitanteRepository solicitanteRepository) {
        this.solicitanteRepository = solicitanteRepository;
    }

    /**
     * Save a solicitante.
     *
     * @param solicitante the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Solicitante save(Solicitante solicitante) {
        log.debug("Request to save Solicitante : {}", solicitante);
        return solicitanteRepository.save(solicitante);
    }

    /**
     * Get all the solicitantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Solicitante> findAll(Pageable pageable) {
        log.debug("Request to get all Solicitantes");
        return solicitanteRepository.findAll(pageable);
    }

    /**
     * Get one solicitante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Solicitante> findOne(Long id) {
        log.debug("Request to get Solicitante : {}", id);
        return solicitanteRepository.findById(id);
    }

    /**
     * Delete the solicitante by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Solicitante : {}", id);
        solicitanteRepository.deleteById(id);
    }
}
