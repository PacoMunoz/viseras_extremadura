package es.pmg.viseras.service.impl;

import es.pmg.viseras.service.DonanteService;
import es.pmg.viseras.domain.Donante;
import es.pmg.viseras.repository.DonanteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Donante}.
 */
@Service
@Transactional
public class DonanteServiceImpl implements DonanteService {

    private final Logger log = LoggerFactory.getLogger(DonanteServiceImpl.class);

    private final DonanteRepository donanteRepository;

    public DonanteServiceImpl(DonanteRepository donanteRepository) {
        this.donanteRepository = donanteRepository;
    }

    /**
     * Save a donante.
     *
     * @param donante the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Donante save(Donante donante) {
        log.debug("Request to save Donante : {}", donante);
        return donanteRepository.save(donante);
    }

    /**
     * Get all the donantes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Donante> findAll(Pageable pageable) {
        log.debug("Request to get all Donantes");
        return donanteRepository.findAll(pageable);
    }

    /**
     * Get one donante by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Donante> findOne(Long id) {
        log.debug("Request to get Donante : {}", id);
        return donanteRepository.findById(id);
    }

    /**
     * Delete the donante by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Donante : {}", id);
        donanteRepository.deleteById(id);
    }
}
