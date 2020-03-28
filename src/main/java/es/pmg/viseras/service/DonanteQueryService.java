package es.pmg.viseras.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import es.pmg.viseras.domain.Donante;
import es.pmg.viseras.domain.*; // for static metamodels
import es.pmg.viseras.repository.DonanteRepository;
import es.pmg.viseras.service.dto.DonanteCriteria;

/**
 * Service for executing complex queries for {@link Donante} entities in the database.
 * The main input is a {@link DonanteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Donante} or a {@link Page} of {@link Donante} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DonanteQueryService extends QueryService<Donante> {

    private final Logger log = LoggerFactory.getLogger(DonanteQueryService.class);

    private final DonanteRepository donanteRepository;

    public DonanteQueryService(DonanteRepository donanteRepository) {
        this.donanteRepository = donanteRepository;
    }

    /**
     * Return a {@link List} of {@link Donante} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Donante> findByCriteria(DonanteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Donante> specification = createSpecification(criteria);
        return donanteRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Donante} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Donante> findByCriteria(DonanteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Donante> specification = createSpecification(criteria);
        return donanteRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DonanteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Donante> specification = createSpecification(criteria);
        return donanteRepository.count(specification);
    }

    /**
     * Function to convert {@link DonanteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Donante> createSpecification(DonanteCriteria criteria) {
        Specification<Donante> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Donante_.id));
            }
            if (criteria.getNombreEmpresaOParticular() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreEmpresaOParticular(), Donante_.nombreEmpresaOParticular));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Donante_.email));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), Donante_.telefono));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), Donante_.direccion));
            }
            if (criteria.getLocalidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocalidad(), Donante_.localidad));
            }
            if (criteria.getCodigoPostal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigoPostal(), Donante_.codigoPostal));
            }
            if (criteria.getAportacion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAportacion(), Donante_.aportacion));
            }
            if (criteria.getCuando() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCuando(), Donante_.cuando));
            }
        }
        return specification;
    }
}
