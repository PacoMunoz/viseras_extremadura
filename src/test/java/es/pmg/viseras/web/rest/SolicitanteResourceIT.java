package es.pmg.viseras.web.rest;

import es.pmg.viseras.ViserasExtremaduraSurApp;
import es.pmg.viseras.domain.Solicitante;
import es.pmg.viseras.repository.SolicitanteRepository;
import es.pmg.viseras.service.SolicitanteService;
import es.pmg.viseras.service.dto.SolicitanteCriteria;
import es.pmg.viseras.service.SolicitanteQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SolicitanteResource} REST controller.
 */
@SpringBootTest(classes = ViserasExtremaduraSurApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class SolicitanteResourceIT {

    private static final String DEFAULT_NOMBRE_CENTRO = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_CENTRO = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONA_CONTACTO = "AAAAAAAAAA";
    private static final String UPDATED_PERSONA_CONTACTO = "BBBBBBBBBB";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "U=@KE3E?.'wSZwD";
    private static final String UPDATED_EMAIL = "Zk^@\"'~teC.1TyR";

    private static final String DEFAULT_LOCALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_LOCALIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_POSTAL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ACEPTA_NO_HOMOLOGADO = false;
    private static final Boolean UPDATED_ACEPTA_NO_HOMOLOGADO = true;

    private static final Integer DEFAULT_NECESIDAD = 1;
    private static final Integer UPDATED_NECESIDAD = 2;
    private static final Integer SMALLER_NECESIDAD = 1 - 1;

    private static final String DEFAULT_HORARIOS_ENTREGA = "AAAAAAAAAA";
    private static final String UPDATED_HORARIOS_ENTREGA = "BBBBBBBBBB";

    private static final String DEFAULT_COMENTARIOS = "AAAAAAAAAA";
    private static final String UPDATED_COMENTARIOS = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONSENTIMIENTO = false;
    private static final Boolean UPDATED_CONSENTIMIENTO = true;

    @Autowired
    private SolicitanteRepository solicitanteRepository;

    @Autowired
    private SolicitanteService solicitanteService;

    @Autowired
    private SolicitanteQueryService solicitanteQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSolicitanteMockMvc;

    private Solicitante solicitante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Solicitante createEntity(EntityManager em) {
        Solicitante solicitante = new Solicitante()
            .nombreCentro(DEFAULT_NOMBRE_CENTRO)
            .personaContacto(DEFAULT_PERSONA_CONTACTO)
            .telefono(DEFAULT_TELEFONO)
            .email(DEFAULT_EMAIL)
            .localidad(DEFAULT_LOCALIDAD)
            .direccion(DEFAULT_DIRECCION)
            .codigoPostal(DEFAULT_CODIGO_POSTAL)
            .aceptaNoHomologado(DEFAULT_ACEPTA_NO_HOMOLOGADO)
            .necesidad(DEFAULT_NECESIDAD)
            .horariosEntrega(DEFAULT_HORARIOS_ENTREGA)
            .comentarios(DEFAULT_COMENTARIOS)
            .consentimiento(DEFAULT_CONSENTIMIENTO);
        return solicitante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Solicitante createUpdatedEntity(EntityManager em) {
        Solicitante solicitante = new Solicitante()
            .nombreCentro(UPDATED_NOMBRE_CENTRO)
            .personaContacto(UPDATED_PERSONA_CONTACTO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .localidad(UPDATED_LOCALIDAD)
            .direccion(UPDATED_DIRECCION)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .aceptaNoHomologado(UPDATED_ACEPTA_NO_HOMOLOGADO)
            .necesidad(UPDATED_NECESIDAD)
            .horariosEntrega(UPDATED_HORARIOS_ENTREGA)
            .comentarios(UPDATED_COMENTARIOS)
            .consentimiento(UPDATED_CONSENTIMIENTO);
        return solicitante;
    }

    @BeforeEach
    public void initTest() {
        solicitante = createEntity(em);
    }

    @Test
    @Transactional
    public void createSolicitante() throws Exception {
        int databaseSizeBeforeCreate = solicitanteRepository.findAll().size();

        // Create the Solicitante
        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isCreated());

        // Validate the Solicitante in the database
        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeCreate + 1);
        Solicitante testSolicitante = solicitanteList.get(solicitanteList.size() - 1);
        assertThat(testSolicitante.getNombreCentro()).isEqualTo(DEFAULT_NOMBRE_CENTRO);
        assertThat(testSolicitante.getPersonaContacto()).isEqualTo(DEFAULT_PERSONA_CONTACTO);
        assertThat(testSolicitante.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testSolicitante.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSolicitante.getLocalidad()).isEqualTo(DEFAULT_LOCALIDAD);
        assertThat(testSolicitante.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testSolicitante.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
        assertThat(testSolicitante.isAceptaNoHomologado()).isEqualTo(DEFAULT_ACEPTA_NO_HOMOLOGADO);
        assertThat(testSolicitante.getNecesidad()).isEqualTo(DEFAULT_NECESIDAD);
        assertThat(testSolicitante.getHorariosEntrega()).isEqualTo(DEFAULT_HORARIOS_ENTREGA);
        assertThat(testSolicitante.getComentarios()).isEqualTo(DEFAULT_COMENTARIOS);
        assertThat(testSolicitante.isConsentimiento()).isEqualTo(DEFAULT_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void createSolicitanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = solicitanteRepository.findAll().size();

        // Create the Solicitante with an existing ID
        solicitante.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitante in the database
        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreCentroIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setNombreCentro(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPersonaContactoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setPersonaContacto(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefonoIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setTelefono(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setEmail(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalidadIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setLocalidad(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDireccionIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setDireccion(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCodigoPostalIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setCodigoPostal(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHorariosEntregaIsRequired() throws Exception {
        int databaseSizeBeforeTest = solicitanteRepository.findAll().size();
        // set the field null
        solicitante.setHorariosEntrega(null);

        // Create the Solicitante, which fails.

        restSolicitanteMockMvc.perform(post("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllSolicitantes() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList
        restSolicitanteMockMvc.perform(get("/api/solicitantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(solicitante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreCentro").value(hasItem(DEFAULT_NOMBRE_CENTRO)))
            .andExpect(jsonPath("$.[*].personaContacto").value(hasItem(DEFAULT_PERSONA_CONTACTO)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].aceptaNoHomologado").value(hasItem(DEFAULT_ACEPTA_NO_HOMOLOGADO.booleanValue())))
            .andExpect(jsonPath("$.[*].necesidad").value(hasItem(DEFAULT_NECESIDAD)))
            .andExpect(jsonPath("$.[*].horariosEntrega").value(hasItem(DEFAULT_HORARIOS_ENTREGA)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)))
            .andExpect(jsonPath("$.[*].consentimiento").value(hasItem(DEFAULT_CONSENTIMIENTO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSolicitante() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get the solicitante
        restSolicitanteMockMvc.perform(get("/api/solicitantes/{id}", solicitante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(solicitante.getId().intValue()))
            .andExpect(jsonPath("$.nombreCentro").value(DEFAULT_NOMBRE_CENTRO))
            .andExpect(jsonPath("$.personaContacto").value(DEFAULT_PERSONA_CONTACTO))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.localidad").value(DEFAULT_LOCALIDAD))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL))
            .andExpect(jsonPath("$.aceptaNoHomologado").value(DEFAULT_ACEPTA_NO_HOMOLOGADO.booleanValue()))
            .andExpect(jsonPath("$.necesidad").value(DEFAULT_NECESIDAD))
            .andExpect(jsonPath("$.horariosEntrega").value(DEFAULT_HORARIOS_ENTREGA))
            .andExpect(jsonPath("$.comentarios").value(DEFAULT_COMENTARIOS))
            .andExpect(jsonPath("$.consentimiento").value(DEFAULT_CONSENTIMIENTO.booleanValue()));
    }


    @Test
    @Transactional
    public void getSolicitantesByIdFiltering() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        Long id = solicitante.getId();

        defaultSolicitanteShouldBeFound("id.equals=" + id);
        defaultSolicitanteShouldNotBeFound("id.notEquals=" + id);

        defaultSolicitanteShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultSolicitanteShouldNotBeFound("id.greaterThan=" + id);

        defaultSolicitanteShouldBeFound("id.lessThanOrEqual=" + id);
        defaultSolicitanteShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByNombreCentroIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where nombreCentro equals to DEFAULT_NOMBRE_CENTRO
        defaultSolicitanteShouldBeFound("nombreCentro.equals=" + DEFAULT_NOMBRE_CENTRO);

        // Get all the solicitanteList where nombreCentro equals to UPDATED_NOMBRE_CENTRO
        defaultSolicitanteShouldNotBeFound("nombreCentro.equals=" + UPDATED_NOMBRE_CENTRO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNombreCentroIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where nombreCentro not equals to DEFAULT_NOMBRE_CENTRO
        defaultSolicitanteShouldNotBeFound("nombreCentro.notEquals=" + DEFAULT_NOMBRE_CENTRO);

        // Get all the solicitanteList where nombreCentro not equals to UPDATED_NOMBRE_CENTRO
        defaultSolicitanteShouldBeFound("nombreCentro.notEquals=" + UPDATED_NOMBRE_CENTRO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNombreCentroIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where nombreCentro in DEFAULT_NOMBRE_CENTRO or UPDATED_NOMBRE_CENTRO
        defaultSolicitanteShouldBeFound("nombreCentro.in=" + DEFAULT_NOMBRE_CENTRO + "," + UPDATED_NOMBRE_CENTRO);

        // Get all the solicitanteList where nombreCentro equals to UPDATED_NOMBRE_CENTRO
        defaultSolicitanteShouldNotBeFound("nombreCentro.in=" + UPDATED_NOMBRE_CENTRO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNombreCentroIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where nombreCentro is not null
        defaultSolicitanteShouldBeFound("nombreCentro.specified=true");

        // Get all the solicitanteList where nombreCentro is null
        defaultSolicitanteShouldNotBeFound("nombreCentro.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByNombreCentroContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where nombreCentro contains DEFAULT_NOMBRE_CENTRO
        defaultSolicitanteShouldBeFound("nombreCentro.contains=" + DEFAULT_NOMBRE_CENTRO);

        // Get all the solicitanteList where nombreCentro contains UPDATED_NOMBRE_CENTRO
        defaultSolicitanteShouldNotBeFound("nombreCentro.contains=" + UPDATED_NOMBRE_CENTRO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNombreCentroNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where nombreCentro does not contain DEFAULT_NOMBRE_CENTRO
        defaultSolicitanteShouldNotBeFound("nombreCentro.doesNotContain=" + DEFAULT_NOMBRE_CENTRO);

        // Get all the solicitanteList where nombreCentro does not contain UPDATED_NOMBRE_CENTRO
        defaultSolicitanteShouldBeFound("nombreCentro.doesNotContain=" + UPDATED_NOMBRE_CENTRO);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByPersonaContactoIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where personaContacto equals to DEFAULT_PERSONA_CONTACTO
        defaultSolicitanteShouldBeFound("personaContacto.equals=" + DEFAULT_PERSONA_CONTACTO);

        // Get all the solicitanteList where personaContacto equals to UPDATED_PERSONA_CONTACTO
        defaultSolicitanteShouldNotBeFound("personaContacto.equals=" + UPDATED_PERSONA_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByPersonaContactoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where personaContacto not equals to DEFAULT_PERSONA_CONTACTO
        defaultSolicitanteShouldNotBeFound("personaContacto.notEquals=" + DEFAULT_PERSONA_CONTACTO);

        // Get all the solicitanteList where personaContacto not equals to UPDATED_PERSONA_CONTACTO
        defaultSolicitanteShouldBeFound("personaContacto.notEquals=" + UPDATED_PERSONA_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByPersonaContactoIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where personaContacto in DEFAULT_PERSONA_CONTACTO or UPDATED_PERSONA_CONTACTO
        defaultSolicitanteShouldBeFound("personaContacto.in=" + DEFAULT_PERSONA_CONTACTO + "," + UPDATED_PERSONA_CONTACTO);

        // Get all the solicitanteList where personaContacto equals to UPDATED_PERSONA_CONTACTO
        defaultSolicitanteShouldNotBeFound("personaContacto.in=" + UPDATED_PERSONA_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByPersonaContactoIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where personaContacto is not null
        defaultSolicitanteShouldBeFound("personaContacto.specified=true");

        // Get all the solicitanteList where personaContacto is null
        defaultSolicitanteShouldNotBeFound("personaContacto.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByPersonaContactoContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where personaContacto contains DEFAULT_PERSONA_CONTACTO
        defaultSolicitanteShouldBeFound("personaContacto.contains=" + DEFAULT_PERSONA_CONTACTO);

        // Get all the solicitanteList where personaContacto contains UPDATED_PERSONA_CONTACTO
        defaultSolicitanteShouldNotBeFound("personaContacto.contains=" + UPDATED_PERSONA_CONTACTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByPersonaContactoNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where personaContacto does not contain DEFAULT_PERSONA_CONTACTO
        defaultSolicitanteShouldNotBeFound("personaContacto.doesNotContain=" + DEFAULT_PERSONA_CONTACTO);

        // Get all the solicitanteList where personaContacto does not contain UPDATED_PERSONA_CONTACTO
        defaultSolicitanteShouldBeFound("personaContacto.doesNotContain=" + UPDATED_PERSONA_CONTACTO);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where telefono equals to DEFAULT_TELEFONO
        defaultSolicitanteShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the solicitanteList where telefono equals to UPDATED_TELEFONO
        defaultSolicitanteShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByTelefonoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where telefono not equals to DEFAULT_TELEFONO
        defaultSolicitanteShouldNotBeFound("telefono.notEquals=" + DEFAULT_TELEFONO);

        // Get all the solicitanteList where telefono not equals to UPDATED_TELEFONO
        defaultSolicitanteShouldBeFound("telefono.notEquals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultSolicitanteShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the solicitanteList where telefono equals to UPDATED_TELEFONO
        defaultSolicitanteShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where telefono is not null
        defaultSolicitanteShouldBeFound("telefono.specified=true");

        // Get all the solicitanteList where telefono is null
        defaultSolicitanteShouldNotBeFound("telefono.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByTelefonoContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where telefono contains DEFAULT_TELEFONO
        defaultSolicitanteShouldBeFound("telefono.contains=" + DEFAULT_TELEFONO);

        // Get all the solicitanteList where telefono contains UPDATED_TELEFONO
        defaultSolicitanteShouldNotBeFound("telefono.contains=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByTelefonoNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where telefono does not contain DEFAULT_TELEFONO
        defaultSolicitanteShouldNotBeFound("telefono.doesNotContain=" + DEFAULT_TELEFONO);

        // Get all the solicitanteList where telefono does not contain UPDATED_TELEFONO
        defaultSolicitanteShouldBeFound("telefono.doesNotContain=" + UPDATED_TELEFONO);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where email equals to DEFAULT_EMAIL
        defaultSolicitanteShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the solicitanteList where email equals to UPDATED_EMAIL
        defaultSolicitanteShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where email not equals to DEFAULT_EMAIL
        defaultSolicitanteShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the solicitanteList where email not equals to UPDATED_EMAIL
        defaultSolicitanteShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultSolicitanteShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the solicitanteList where email equals to UPDATED_EMAIL
        defaultSolicitanteShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where email is not null
        defaultSolicitanteShouldBeFound("email.specified=true");

        // Get all the solicitanteList where email is null
        defaultSolicitanteShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByEmailContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where email contains DEFAULT_EMAIL
        defaultSolicitanteShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the solicitanteList where email contains UPDATED_EMAIL
        defaultSolicitanteShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where email does not contain DEFAULT_EMAIL
        defaultSolicitanteShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the solicitanteList where email does not contain UPDATED_EMAIL
        defaultSolicitanteShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByLocalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where localidad equals to DEFAULT_LOCALIDAD
        defaultSolicitanteShouldBeFound("localidad.equals=" + DEFAULT_LOCALIDAD);

        // Get all the solicitanteList where localidad equals to UPDATED_LOCALIDAD
        defaultSolicitanteShouldNotBeFound("localidad.equals=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByLocalidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where localidad not equals to DEFAULT_LOCALIDAD
        defaultSolicitanteShouldNotBeFound("localidad.notEquals=" + DEFAULT_LOCALIDAD);

        // Get all the solicitanteList where localidad not equals to UPDATED_LOCALIDAD
        defaultSolicitanteShouldBeFound("localidad.notEquals=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByLocalidadIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where localidad in DEFAULT_LOCALIDAD or UPDATED_LOCALIDAD
        defaultSolicitanteShouldBeFound("localidad.in=" + DEFAULT_LOCALIDAD + "," + UPDATED_LOCALIDAD);

        // Get all the solicitanteList where localidad equals to UPDATED_LOCALIDAD
        defaultSolicitanteShouldNotBeFound("localidad.in=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByLocalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where localidad is not null
        defaultSolicitanteShouldBeFound("localidad.specified=true");

        // Get all the solicitanteList where localidad is null
        defaultSolicitanteShouldNotBeFound("localidad.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByLocalidadContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where localidad contains DEFAULT_LOCALIDAD
        defaultSolicitanteShouldBeFound("localidad.contains=" + DEFAULT_LOCALIDAD);

        // Get all the solicitanteList where localidad contains UPDATED_LOCALIDAD
        defaultSolicitanteShouldNotBeFound("localidad.contains=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByLocalidadNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where localidad does not contain DEFAULT_LOCALIDAD
        defaultSolicitanteShouldNotBeFound("localidad.doesNotContain=" + DEFAULT_LOCALIDAD);

        // Get all the solicitanteList where localidad does not contain UPDATED_LOCALIDAD
        defaultSolicitanteShouldBeFound("localidad.doesNotContain=" + UPDATED_LOCALIDAD);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where direccion equals to DEFAULT_DIRECCION
        defaultSolicitanteShouldBeFound("direccion.equals=" + DEFAULT_DIRECCION);

        // Get all the solicitanteList where direccion equals to UPDATED_DIRECCION
        defaultSolicitanteShouldNotBeFound("direccion.equals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByDireccionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where direccion not equals to DEFAULT_DIRECCION
        defaultSolicitanteShouldNotBeFound("direccion.notEquals=" + DEFAULT_DIRECCION);

        // Get all the solicitanteList where direccion not equals to UPDATED_DIRECCION
        defaultSolicitanteShouldBeFound("direccion.notEquals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByDireccionIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where direccion in DEFAULT_DIRECCION or UPDATED_DIRECCION
        defaultSolicitanteShouldBeFound("direccion.in=" + DEFAULT_DIRECCION + "," + UPDATED_DIRECCION);

        // Get all the solicitanteList where direccion equals to UPDATED_DIRECCION
        defaultSolicitanteShouldNotBeFound("direccion.in=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByDireccionIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where direccion is not null
        defaultSolicitanteShouldBeFound("direccion.specified=true");

        // Get all the solicitanteList where direccion is null
        defaultSolicitanteShouldNotBeFound("direccion.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByDireccionContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where direccion contains DEFAULT_DIRECCION
        defaultSolicitanteShouldBeFound("direccion.contains=" + DEFAULT_DIRECCION);

        // Get all the solicitanteList where direccion contains UPDATED_DIRECCION
        defaultSolicitanteShouldNotBeFound("direccion.contains=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByDireccionNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where direccion does not contain DEFAULT_DIRECCION
        defaultSolicitanteShouldNotBeFound("direccion.doesNotContain=" + DEFAULT_DIRECCION);

        // Get all the solicitanteList where direccion does not contain UPDATED_DIRECCION
        defaultSolicitanteShouldBeFound("direccion.doesNotContain=" + UPDATED_DIRECCION);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByCodigoPostalIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where codigoPostal equals to DEFAULT_CODIGO_POSTAL
        defaultSolicitanteShouldBeFound("codigoPostal.equals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the solicitanteList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultSolicitanteShouldNotBeFound("codigoPostal.equals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByCodigoPostalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where codigoPostal not equals to DEFAULT_CODIGO_POSTAL
        defaultSolicitanteShouldNotBeFound("codigoPostal.notEquals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the solicitanteList where codigoPostal not equals to UPDATED_CODIGO_POSTAL
        defaultSolicitanteShouldBeFound("codigoPostal.notEquals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByCodigoPostalIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where codigoPostal in DEFAULT_CODIGO_POSTAL or UPDATED_CODIGO_POSTAL
        defaultSolicitanteShouldBeFound("codigoPostal.in=" + DEFAULT_CODIGO_POSTAL + "," + UPDATED_CODIGO_POSTAL);

        // Get all the solicitanteList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultSolicitanteShouldNotBeFound("codigoPostal.in=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByCodigoPostalIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where codigoPostal is not null
        defaultSolicitanteShouldBeFound("codigoPostal.specified=true");

        // Get all the solicitanteList where codigoPostal is null
        defaultSolicitanteShouldNotBeFound("codigoPostal.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByCodigoPostalContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where codigoPostal contains DEFAULT_CODIGO_POSTAL
        defaultSolicitanteShouldBeFound("codigoPostal.contains=" + DEFAULT_CODIGO_POSTAL);

        // Get all the solicitanteList where codigoPostal contains UPDATED_CODIGO_POSTAL
        defaultSolicitanteShouldNotBeFound("codigoPostal.contains=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByCodigoPostalNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where codigoPostal does not contain DEFAULT_CODIGO_POSTAL
        defaultSolicitanteShouldNotBeFound("codigoPostal.doesNotContain=" + DEFAULT_CODIGO_POSTAL);

        // Get all the solicitanteList where codigoPostal does not contain UPDATED_CODIGO_POSTAL
        defaultSolicitanteShouldBeFound("codigoPostal.doesNotContain=" + UPDATED_CODIGO_POSTAL);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByAceptaNoHomologadoIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where aceptaNoHomologado equals to DEFAULT_ACEPTA_NO_HOMOLOGADO
        defaultSolicitanteShouldBeFound("aceptaNoHomologado.equals=" + DEFAULT_ACEPTA_NO_HOMOLOGADO);

        // Get all the solicitanteList where aceptaNoHomologado equals to UPDATED_ACEPTA_NO_HOMOLOGADO
        defaultSolicitanteShouldNotBeFound("aceptaNoHomologado.equals=" + UPDATED_ACEPTA_NO_HOMOLOGADO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByAceptaNoHomologadoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where aceptaNoHomologado not equals to DEFAULT_ACEPTA_NO_HOMOLOGADO
        defaultSolicitanteShouldNotBeFound("aceptaNoHomologado.notEquals=" + DEFAULT_ACEPTA_NO_HOMOLOGADO);

        // Get all the solicitanteList where aceptaNoHomologado not equals to UPDATED_ACEPTA_NO_HOMOLOGADO
        defaultSolicitanteShouldBeFound("aceptaNoHomologado.notEquals=" + UPDATED_ACEPTA_NO_HOMOLOGADO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByAceptaNoHomologadoIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where aceptaNoHomologado in DEFAULT_ACEPTA_NO_HOMOLOGADO or UPDATED_ACEPTA_NO_HOMOLOGADO
        defaultSolicitanteShouldBeFound("aceptaNoHomologado.in=" + DEFAULT_ACEPTA_NO_HOMOLOGADO + "," + UPDATED_ACEPTA_NO_HOMOLOGADO);

        // Get all the solicitanteList where aceptaNoHomologado equals to UPDATED_ACEPTA_NO_HOMOLOGADO
        defaultSolicitanteShouldNotBeFound("aceptaNoHomologado.in=" + UPDATED_ACEPTA_NO_HOMOLOGADO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByAceptaNoHomologadoIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where aceptaNoHomologado is not null
        defaultSolicitanteShouldBeFound("aceptaNoHomologado.specified=true");

        // Get all the solicitanteList where aceptaNoHomologado is null
        defaultSolicitanteShouldNotBeFound("aceptaNoHomologado.specified=false");
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad equals to DEFAULT_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.equals=" + DEFAULT_NECESIDAD);

        // Get all the solicitanteList where necesidad equals to UPDATED_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.equals=" + UPDATED_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad not equals to DEFAULT_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.notEquals=" + DEFAULT_NECESIDAD);

        // Get all the solicitanteList where necesidad not equals to UPDATED_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.notEquals=" + UPDATED_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad in DEFAULT_NECESIDAD or UPDATED_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.in=" + DEFAULT_NECESIDAD + "," + UPDATED_NECESIDAD);

        // Get all the solicitanteList where necesidad equals to UPDATED_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.in=" + UPDATED_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad is not null
        defaultSolicitanteShouldBeFound("necesidad.specified=true");

        // Get all the solicitanteList where necesidad is null
        defaultSolicitanteShouldNotBeFound("necesidad.specified=false");
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad is greater than or equal to DEFAULT_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.greaterThanOrEqual=" + DEFAULT_NECESIDAD);

        // Get all the solicitanteList where necesidad is greater than or equal to UPDATED_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.greaterThanOrEqual=" + UPDATED_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad is less than or equal to DEFAULT_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.lessThanOrEqual=" + DEFAULT_NECESIDAD);

        // Get all the solicitanteList where necesidad is less than or equal to SMALLER_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.lessThanOrEqual=" + SMALLER_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsLessThanSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad is less than DEFAULT_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.lessThan=" + DEFAULT_NECESIDAD);

        // Get all the solicitanteList where necesidad is less than UPDATED_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.lessThan=" + UPDATED_NECESIDAD);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByNecesidadIsGreaterThanSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where necesidad is greater than DEFAULT_NECESIDAD
        defaultSolicitanteShouldNotBeFound("necesidad.greaterThan=" + DEFAULT_NECESIDAD);

        // Get all the solicitanteList where necesidad is greater than SMALLER_NECESIDAD
        defaultSolicitanteShouldBeFound("necesidad.greaterThan=" + SMALLER_NECESIDAD);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByHorariosEntregaIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where horariosEntrega equals to DEFAULT_HORARIOS_ENTREGA
        defaultSolicitanteShouldBeFound("horariosEntrega.equals=" + DEFAULT_HORARIOS_ENTREGA);

        // Get all the solicitanteList where horariosEntrega equals to UPDATED_HORARIOS_ENTREGA
        defaultSolicitanteShouldNotBeFound("horariosEntrega.equals=" + UPDATED_HORARIOS_ENTREGA);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByHorariosEntregaIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where horariosEntrega not equals to DEFAULT_HORARIOS_ENTREGA
        defaultSolicitanteShouldNotBeFound("horariosEntrega.notEquals=" + DEFAULT_HORARIOS_ENTREGA);

        // Get all the solicitanteList where horariosEntrega not equals to UPDATED_HORARIOS_ENTREGA
        defaultSolicitanteShouldBeFound("horariosEntrega.notEquals=" + UPDATED_HORARIOS_ENTREGA);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByHorariosEntregaIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where horariosEntrega in DEFAULT_HORARIOS_ENTREGA or UPDATED_HORARIOS_ENTREGA
        defaultSolicitanteShouldBeFound("horariosEntrega.in=" + DEFAULT_HORARIOS_ENTREGA + "," + UPDATED_HORARIOS_ENTREGA);

        // Get all the solicitanteList where horariosEntrega equals to UPDATED_HORARIOS_ENTREGA
        defaultSolicitanteShouldNotBeFound("horariosEntrega.in=" + UPDATED_HORARIOS_ENTREGA);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByHorariosEntregaIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where horariosEntrega is not null
        defaultSolicitanteShouldBeFound("horariosEntrega.specified=true");

        // Get all the solicitanteList where horariosEntrega is null
        defaultSolicitanteShouldNotBeFound("horariosEntrega.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByHorariosEntregaContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where horariosEntrega contains DEFAULT_HORARIOS_ENTREGA
        defaultSolicitanteShouldBeFound("horariosEntrega.contains=" + DEFAULT_HORARIOS_ENTREGA);

        // Get all the solicitanteList where horariosEntrega contains UPDATED_HORARIOS_ENTREGA
        defaultSolicitanteShouldNotBeFound("horariosEntrega.contains=" + UPDATED_HORARIOS_ENTREGA);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByHorariosEntregaNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where horariosEntrega does not contain DEFAULT_HORARIOS_ENTREGA
        defaultSolicitanteShouldNotBeFound("horariosEntrega.doesNotContain=" + DEFAULT_HORARIOS_ENTREGA);

        // Get all the solicitanteList where horariosEntrega does not contain UPDATED_HORARIOS_ENTREGA
        defaultSolicitanteShouldBeFound("horariosEntrega.doesNotContain=" + UPDATED_HORARIOS_ENTREGA);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByComentariosIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where comentarios equals to DEFAULT_COMENTARIOS
        defaultSolicitanteShouldBeFound("comentarios.equals=" + DEFAULT_COMENTARIOS);

        // Get all the solicitanteList where comentarios equals to UPDATED_COMENTARIOS
        defaultSolicitanteShouldNotBeFound("comentarios.equals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByComentariosIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where comentarios not equals to DEFAULT_COMENTARIOS
        defaultSolicitanteShouldNotBeFound("comentarios.notEquals=" + DEFAULT_COMENTARIOS);

        // Get all the solicitanteList where comentarios not equals to UPDATED_COMENTARIOS
        defaultSolicitanteShouldBeFound("comentarios.notEquals=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByComentariosIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where comentarios in DEFAULT_COMENTARIOS or UPDATED_COMENTARIOS
        defaultSolicitanteShouldBeFound("comentarios.in=" + DEFAULT_COMENTARIOS + "," + UPDATED_COMENTARIOS);

        // Get all the solicitanteList where comentarios equals to UPDATED_COMENTARIOS
        defaultSolicitanteShouldNotBeFound("comentarios.in=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByComentariosIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where comentarios is not null
        defaultSolicitanteShouldBeFound("comentarios.specified=true");

        // Get all the solicitanteList where comentarios is null
        defaultSolicitanteShouldNotBeFound("comentarios.specified=false");
    }
                @Test
    @Transactional
    public void getAllSolicitantesByComentariosContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where comentarios contains DEFAULT_COMENTARIOS
        defaultSolicitanteShouldBeFound("comentarios.contains=" + DEFAULT_COMENTARIOS);

        // Get all the solicitanteList where comentarios contains UPDATED_COMENTARIOS
        defaultSolicitanteShouldNotBeFound("comentarios.contains=" + UPDATED_COMENTARIOS);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByComentariosNotContainsSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where comentarios does not contain DEFAULT_COMENTARIOS
        defaultSolicitanteShouldNotBeFound("comentarios.doesNotContain=" + DEFAULT_COMENTARIOS);

        // Get all the solicitanteList where comentarios does not contain UPDATED_COMENTARIOS
        defaultSolicitanteShouldBeFound("comentarios.doesNotContain=" + UPDATED_COMENTARIOS);
    }


    @Test
    @Transactional
    public void getAllSolicitantesByConsentimientoIsEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where consentimiento equals to DEFAULT_CONSENTIMIENTO
        defaultSolicitanteShouldBeFound("consentimiento.equals=" + DEFAULT_CONSENTIMIENTO);

        // Get all the solicitanteList where consentimiento equals to UPDATED_CONSENTIMIENTO
        defaultSolicitanteShouldNotBeFound("consentimiento.equals=" + UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByConsentimientoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where consentimiento not equals to DEFAULT_CONSENTIMIENTO
        defaultSolicitanteShouldNotBeFound("consentimiento.notEquals=" + DEFAULT_CONSENTIMIENTO);

        // Get all the solicitanteList where consentimiento not equals to UPDATED_CONSENTIMIENTO
        defaultSolicitanteShouldBeFound("consentimiento.notEquals=" + UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByConsentimientoIsInShouldWork() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where consentimiento in DEFAULT_CONSENTIMIENTO or UPDATED_CONSENTIMIENTO
        defaultSolicitanteShouldBeFound("consentimiento.in=" + DEFAULT_CONSENTIMIENTO + "," + UPDATED_CONSENTIMIENTO);

        // Get all the solicitanteList where consentimiento equals to UPDATED_CONSENTIMIENTO
        defaultSolicitanteShouldNotBeFound("consentimiento.in=" + UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void getAllSolicitantesByConsentimientoIsNullOrNotNull() throws Exception {
        // Initialize the database
        solicitanteRepository.saveAndFlush(solicitante);

        // Get all the solicitanteList where consentimiento is not null
        defaultSolicitanteShouldBeFound("consentimiento.specified=true");

        // Get all the solicitanteList where consentimiento is null
        defaultSolicitanteShouldNotBeFound("consentimiento.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultSolicitanteShouldBeFound(String filter) throws Exception {
        restSolicitanteMockMvc.perform(get("/api/solicitantes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(solicitante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreCentro").value(hasItem(DEFAULT_NOMBRE_CENTRO)))
            .andExpect(jsonPath("$.[*].personaContacto").value(hasItem(DEFAULT_PERSONA_CONTACTO)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].aceptaNoHomologado").value(hasItem(DEFAULT_ACEPTA_NO_HOMOLOGADO.booleanValue())))
            .andExpect(jsonPath("$.[*].necesidad").value(hasItem(DEFAULT_NECESIDAD)))
            .andExpect(jsonPath("$.[*].horariosEntrega").value(hasItem(DEFAULT_HORARIOS_ENTREGA)))
            .andExpect(jsonPath("$.[*].comentarios").value(hasItem(DEFAULT_COMENTARIOS)))
            .andExpect(jsonPath("$.[*].consentimiento").value(hasItem(DEFAULT_CONSENTIMIENTO.booleanValue())));

        // Check, that the count call also returns 1
        restSolicitanteMockMvc.perform(get("/api/solicitantes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultSolicitanteShouldNotBeFound(String filter) throws Exception {
        restSolicitanteMockMvc.perform(get("/api/solicitantes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restSolicitanteMockMvc.perform(get("/api/solicitantes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingSolicitante() throws Exception {
        // Get the solicitante
        restSolicitanteMockMvc.perform(get("/api/solicitantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSolicitante() throws Exception {
        // Initialize the database
        solicitanteService.save(solicitante);

        int databaseSizeBeforeUpdate = solicitanteRepository.findAll().size();

        // Update the solicitante
        Solicitante updatedSolicitante = solicitanteRepository.findById(solicitante.getId()).get();
        // Disconnect from session so that the updates on updatedSolicitante are not directly saved in db
        em.detach(updatedSolicitante);
        updatedSolicitante
            .nombreCentro(UPDATED_NOMBRE_CENTRO)
            .personaContacto(UPDATED_PERSONA_CONTACTO)
            .telefono(UPDATED_TELEFONO)
            .email(UPDATED_EMAIL)
            .localidad(UPDATED_LOCALIDAD)
            .direccion(UPDATED_DIRECCION)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .aceptaNoHomologado(UPDATED_ACEPTA_NO_HOMOLOGADO)
            .necesidad(UPDATED_NECESIDAD)
            .horariosEntrega(UPDATED_HORARIOS_ENTREGA)
            .comentarios(UPDATED_COMENTARIOS)
            .consentimiento(UPDATED_CONSENTIMIENTO);

        restSolicitanteMockMvc.perform(put("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSolicitante)))
            .andExpect(status().isOk());

        // Validate the Solicitante in the database
        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeUpdate);
        Solicitante testSolicitante = solicitanteList.get(solicitanteList.size() - 1);
        assertThat(testSolicitante.getNombreCentro()).isEqualTo(UPDATED_NOMBRE_CENTRO);
        assertThat(testSolicitante.getPersonaContacto()).isEqualTo(UPDATED_PERSONA_CONTACTO);
        assertThat(testSolicitante.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testSolicitante.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSolicitante.getLocalidad()).isEqualTo(UPDATED_LOCALIDAD);
        assertThat(testSolicitante.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testSolicitante.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testSolicitante.isAceptaNoHomologado()).isEqualTo(UPDATED_ACEPTA_NO_HOMOLOGADO);
        assertThat(testSolicitante.getNecesidad()).isEqualTo(UPDATED_NECESIDAD);
        assertThat(testSolicitante.getHorariosEntrega()).isEqualTo(UPDATED_HORARIOS_ENTREGA);
        assertThat(testSolicitante.getComentarios()).isEqualTo(UPDATED_COMENTARIOS);
        assertThat(testSolicitante.isConsentimiento()).isEqualTo(UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingSolicitante() throws Exception {
        int databaseSizeBeforeUpdate = solicitanteRepository.findAll().size();

        // Create the Solicitante

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSolicitanteMockMvc.perform(put("/api/solicitantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(solicitante)))
            .andExpect(status().isBadRequest());

        // Validate the Solicitante in the database
        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSolicitante() throws Exception {
        // Initialize the database
        solicitanteService.save(solicitante);

        int databaseSizeBeforeDelete = solicitanteRepository.findAll().size();

        // Delete the solicitante
        restSolicitanteMockMvc.perform(delete("/api/solicitantes/{id}", solicitante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Solicitante> solicitanteList = solicitanteRepository.findAll();
        assertThat(solicitanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
