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

import es.pmg.viseras.domain.Solicitante;
import es.pmg.viseras.domain.*; // for static metamodels
import es.pmg.viseras.repository.SolicitanteRepository;
import es.pmg.viseras.service.dto.SolicitanteCriteria;

/**
 * Service for executing complex queries for {@link Solicitante} entities in the database.
 * The main input is a {@link SolicitanteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link Solicitante} or a {@link Page} of {@link Solicitante} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SolicitanteQueryService extends QueryService<Solicitante> {

    private final Logger log = LoggerFactory.getLogger(SolicitanteQueryService.class);

    private final SolicitanteRepository solicitanteRepository;

    public SolicitanteQueryService(SolicitanteRepository solicitanteRepository) {
        this.solicitanteRepository = solicitanteRepository;
    }

    /**
     * Return a {@link List} of {@link Solicitante} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<Solicitante> findByCriteria(SolicitanteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Solicitante> specification = createSpecification(criteria);
        return solicitanteRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link Solicitante} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<Solicitante> findByCriteria(SolicitanteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Solicitante> specification = createSpecification(criteria);
        return solicitanteRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SolicitanteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Solicitante> specification = createSpecification(criteria);
        return solicitanteRepository.count(specification);
    }

    /**
     * Function to convert {@link SolicitanteCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Solicitante> createSpecification(SolicitanteCriteria criteria) {
        Specification<Solicitante> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Solicitante_.id));
            }
            if (criteria.getNombreCentro() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNombreCentro(), Solicitante_.nombreCentro));
            }
            if (criteria.getPersonaContacto() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonaContacto(), Solicitante_.personaContacto));
            }
            if (criteria.getTelefono() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelefono(), Solicitante_.telefono));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), Solicitante_.email));
            }
            if (criteria.getLocalidad() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLocalidad(), Solicitante_.localidad));
            }
            if (criteria.getDireccion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDireccion(), Solicitante_.direccion));
            }
            if (criteria.getCodigoPostal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCodigoPostal(), Solicitante_.codigoPostal));
            }
            if (criteria.getAceptaNoHomologado() != null) {
                specification = specification.and(buildSpecification(criteria.getAceptaNoHomologado(), Solicitante_.aceptaNoHomologado));
            }
            if (criteria.getNecesidad() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNecesidad(), Solicitante_.necesidad));
            }
            if (criteria.getHorariosEntrega() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHorariosEntrega(), Solicitante_.horariosEntrega));
            }
            if (criteria.getComentarios() != null) {
                specification = specification.and(buildStringSpecification(criteria.getComentarios(), Solicitante_.comentarios));
            }
            if (criteria.getConsentimiento() != null) {
                specification = specification.and(buildSpecification(criteria.getConsentimiento(), Solicitante_.consentimiento));
            }
        }
        return specification;
    }
}
