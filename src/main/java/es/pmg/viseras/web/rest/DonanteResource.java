package es.pmg.viseras.web.rest;

import es.pmg.viseras.domain.Donante;
import es.pmg.viseras.service.DonanteService;
import es.pmg.viseras.web.rest.errors.BadRequestAlertException;
import es.pmg.viseras.service.dto.DonanteCriteria;
import es.pmg.viseras.service.DonanteQueryService;

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
 * REST controller for managing {@link es.pmg.viseras.domain.Donante}.
 */
@RestController
@RequestMapping("/api")
public class DonanteResource {

    private final Logger log = LoggerFactory.getLogger(DonanteResource.class);

    private static final String ENTITY_NAME = "donante";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DonanteService donanteService;

    private final DonanteQueryService donanteQueryService;

    public DonanteResource(DonanteService donanteService, DonanteQueryService donanteQueryService) {
        this.donanteService = donanteService;
        this.donanteQueryService = donanteQueryService;
    }

    /**
     * {@code POST  /donantes} : Create a new donante.
     *
     * @param donante the donante to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new donante, or with status {@code 400 (Bad Request)} if the donante has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/donantes")
    public ResponseEntity<Donante> createDonante(@Valid @RequestBody Donante donante) throws URISyntaxException {
        log.debug("REST request to save Donante : {}", donante);
        if (donante.getId() != null) {
            throw new BadRequestAlertException("A new donante cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Donante result = donanteService.save(donante);
        return ResponseEntity.created(new URI("/api/donantes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /donantes} : Updates an existing donante.
     *
     * @param donante the donante to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated donante,
     * or with status {@code 400 (Bad Request)} if the donante is not valid,
     * or with status {@code 500 (Internal Server Error)} if the donante couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/donantes")
    public ResponseEntity<Donante> updateDonante(@Valid @RequestBody Donante donante) throws URISyntaxException {
        log.debug("REST request to update Donante : {}", donante);
        if (donante.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Donante result = donanteService.save(donante);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, donante.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /donantes} : get all the donantes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of donantes in body.
     */
    @GetMapping("/donantes")
    public ResponseEntity<List<Donante>> getAllDonantes(DonanteCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Donantes by criteria: {}", criteria);
        Page<Donante> page = donanteQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /donantes/count} : count all the donantes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/donantes/count")
    public ResponseEntity<Long> countDonantes(DonanteCriteria criteria) {
        log.debug("REST request to count Donantes by criteria: {}", criteria);
        return ResponseEntity.ok().body(donanteQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /donantes/:id} : get the "id" donante.
     *
     * @param id the id of the donante to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the donante, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/donantes/{id}")
    public ResponseEntity<Donante> getDonante(@PathVariable Long id) {
        log.debug("REST request to get Donante : {}", id);
        Optional<Donante> donante = donanteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(donante);
    }

    /**
     * {@code DELETE  /donantes/:id} : delete the "id" donante.
     *
     * @param id the id of the donante to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/donantes/{id}")
    public ResponseEntity<Void> deleteDonante(@PathVariable Long id) {
        log.debug("REST request to delete Donante : {}", id);
        donanteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
