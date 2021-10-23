package es.pmg.viseras.web.rest;

import es.pmg.viseras.domain.Solicitante;
import es.pmg.viseras.service.SolicitanteService;
import es.pmg.viseras.web.rest.errors.BadRequestAlertException;
import es.pmg.viseras.service.dto.SolicitanteCriteria;
import es.pmg.viseras.service.SolicitanteQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link es.pmg.viseras.domain.Solicitante}.
 */
@RestController
@RequestMapping("/api")
public class SolicitanteResource {

    private final Logger log = LoggerFactory.getLogger(SolicitanteResource.class);

    private static final String ENTITY_NAME = "solicitante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SolicitanteService solicitanteService;

    private final SolicitanteQueryService solicitanteQueryService;

    public SolicitanteResource(SolicitanteService solicitanteService, SolicitanteQueryService solicitanteQueryService) {
        this.solicitanteService = solicitanteService;
        this.solicitanteQueryService = solicitanteQueryService;
    }

    /**
     * {@code POST  /solicitantes} : Create a new solicitante.
     *
     * @param solicitante the solicitante to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new solicitante, or with status {@code 400 (Bad Request)} if the solicitante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/solicitantes")
    public ResponseEntity<Solicitante> createSolicitante(@Valid @RequestBody Solicitante solicitante) throws URISyntaxException {
        log.debug("REST request to save Solicitante : {}", solicitante);
        if (solicitante.getId() != null) {
            throw new BadRequestAlertException("A new solicitante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Solicitante result = solicitanteService.save(solicitante);
        return ResponseEntity.created(new URI("/api/solicitantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME,
                result.getId().toString() + ". Mil gracias, nos pondremos en contacto con usted lo antes posible."))
            .body(result);
    }

    /**
     * {@code PUT  /solicitantes} : Updates an existing solicitante.
     *
     * @param solicitante the solicitante to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated solicitante,
     * or with status {@code 400 (Bad Request)} if the solicitante is not valid,
     * or with status {@code 500 (Internal Server Error)} if the solicitante couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/solicitantes")
    public ResponseEntity<Solicitante> updateSolicitante(@Valid @RequestBody Solicitante solicitante) throws URISyntaxException {
        log.debug("REST request to update Solicitante : {}", solicitante);
        if (solicitante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Solicitante result = solicitanteService.save(solicitante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, solicitante.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /solicitantes} : get all the solicitantes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of solicitantes in body.
     */
    @GetMapping("/solicitantes")
    public ResponseEntity<List<Solicitante>> getAllSolicitantes(SolicitanteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Solicitantes by criteria: {}", criteria);
        Page<Solicitante> page = solicitanteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /solicitantes/count} : count all the solicitantes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/solicitantes/count")
    public ResponseEntity<Long> countSolicitantes(SolicitanteCriteria criteria) {
        log.debug("REST request to count Solicitantes by criteria: {}", criteria);
        return ResponseEntity.ok().body(solicitanteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /solicitantes/:id} : get the "id" solicitante.
     *
     * @param id the id of the solicitante to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the solicitante, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/solicitantes/{id}")
    public ResponseEntity<Solicitante> getSolicitante(@PathVariable Long id) {
        log.debug("REST request to get Solicitante : {}", id);
        Optional<Solicitante> solicitante = solicitanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(solicitante);
    }

    /**
     * {@code DELETE  /solicitantes/:id} : delete the "id" solicitante.
     *
     * @param id the id of the solicitante to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/solicitantes/{id}")
    public ResponseEntity<Void> deleteSolicitante(@PathVariable Long id) {
        log.debug("REST request to delete Solicitante : {}", id);
        solicitanteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
