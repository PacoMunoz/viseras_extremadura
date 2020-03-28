package es.pmg.viseras.web.rest;

import es.pmg.viseras.ViserasExtremaduraSurApp;
import es.pmg.viseras.domain.Donante;
import es.pmg.viseras.repository.DonanteRepository;
import es.pmg.viseras.service.DonanteService;
import es.pmg.viseras.service.dto.DonanteCriteria;
import es.pmg.viseras.service.DonanteQueryService;

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
 * Integration tests for the {@link DonanteResource} REST controller.
 */
@SpringBootTest(classes = ViserasExtremaduraSurApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class DonanteResourceIT {

    private static final String DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR = "AAAAAAAAAA";
    private static final String UPDATED_NOMBRE_EMPRESA_O_PARTICULAR = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "p/53@EKr7.wO0,Sv";
    private static final String UPDATED_EMAIL = "5]u@!--L.Pe0E+,";

    private static final String DEFAULT_TELEFONO = "AAAAAAAAAA";
    private static final String UPDATED_TELEFONO = "BBBBBBBBBB";

    private static final String DEFAULT_DIRECCION = "AAAAAAAAAA";
    private static final String UPDATED_DIRECCION = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALIDAD = "AAAAAAAAAA";
    private static final String UPDATED_LOCALIDAD = "BBBBBBBBBB";

    private static final String DEFAULT_CODIGO_POSTAL = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO_POSTAL = "BBBBBBBBBB";

    private static final String DEFAULT_APORTACION = "AAAAAAAAAA";
    private static final String UPDATED_APORTACION = "BBBBBBBBBB";

    private static final String DEFAULT_CUANDO = "AAAAAAAAAA";
    private static final String UPDATED_CUANDO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CONSENTIMIENTO = false;
    private static final Boolean UPDATED_CONSENTIMIENTO = true;

    @Autowired
    private DonanteRepository donanteRepository;

    @Autowired
    private DonanteService donanteService;

    @Autowired
    private DonanteQueryService donanteQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDonanteMockMvc;

    private Donante donante;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Donante createEntity(EntityManager em) {
        Donante donante = new Donante()
            .nombreEmpresaOParticular(DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR)
            .email(DEFAULT_EMAIL)
            .telefono(DEFAULT_TELEFONO)
            .direccion(DEFAULT_DIRECCION)
            .localidad(DEFAULT_LOCALIDAD)
            .codigoPostal(DEFAULT_CODIGO_POSTAL)
            .aportacion(DEFAULT_APORTACION)
            .cuando(DEFAULT_CUANDO)
            .consentimiento(DEFAULT_CONSENTIMIENTO);
        return donante;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Donante createUpdatedEntity(EntityManager em) {
        Donante donante = new Donante()
            .nombreEmpresaOParticular(UPDATED_NOMBRE_EMPRESA_O_PARTICULAR)
            .email(UPDATED_EMAIL)
            .telefono(UPDATED_TELEFONO)
            .direccion(UPDATED_DIRECCION)
            .localidad(UPDATED_LOCALIDAD)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .aportacion(UPDATED_APORTACION)
            .cuando(UPDATED_CUANDO)
            .consentimiento(UPDATED_CONSENTIMIENTO);
        return donante;
    }

    @BeforeEach
    public void initTest() {
        donante = createEntity(em);
    }

    @Test
    @Transactional
    public void createDonante() throws Exception {
        int databaseSizeBeforeCreate = donanteRepository.findAll().size();

        // Create the Donante
        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isCreated());

        // Validate the Donante in the database
        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeCreate + 1);
        Donante testDonante = donanteList.get(donanteList.size() - 1);
        assertThat(testDonante.getNombreEmpresaOParticular()).isEqualTo(DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR);
        assertThat(testDonante.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testDonante.getTelefono()).isEqualTo(DEFAULT_TELEFONO);
        assertThat(testDonante.getDireccion()).isEqualTo(DEFAULT_DIRECCION);
        assertThat(testDonante.getLocalidad()).isEqualTo(DEFAULT_LOCALIDAD);
        assertThat(testDonante.getCodigoPostal()).isEqualTo(DEFAULT_CODIGO_POSTAL);
        assertThat(testDonante.getAportacion()).isEqualTo(DEFAULT_APORTACION);
        assertThat(testDonante.getCuando()).isEqualTo(DEFAULT_CUANDO);
        assertThat(testDonante.isConsentimiento()).isEqualTo(DEFAULT_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void createDonanteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = donanteRepository.findAll().size();

        // Create the Donante with an existing ID
        donante.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        // Validate the Donante in the database
        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNombreEmpresaOParticularIsRequired() throws Exception {
        int databaseSizeBeforeTest = donanteRepository.findAll().size();
        // set the field null
        donante.setNombreEmpresaOParticular(null);

        // Create the Donante, which fails.

        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = donanteRepository.findAll().size();
        // set the field null
        donante.setEmail(null);

        // Create the Donante, which fails.

        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTelefonoIsRequired() throws Exception {
        int databaseSizeBeforeTest = donanteRepository.findAll().size();
        // set the field null
        donante.setTelefono(null);

        // Create the Donante, which fails.

        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAportacionIsRequired() throws Exception {
        int databaseSizeBeforeTest = donanteRepository.findAll().size();
        // set the field null
        donante.setAportacion(null);

        // Create the Donante, which fails.

        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCuandoIsRequired() throws Exception {
        int databaseSizeBeforeTest = donanteRepository.findAll().size();
        // set the field null
        donante.setCuando(null);

        // Create the Donante, which fails.

        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkConsentimientoIsRequired() throws Exception {
        int databaseSizeBeforeTest = donanteRepository.findAll().size();
        // set the field null
        donante.setConsentimiento(null);

        // Create the Donante, which fails.

        restDonanteMockMvc.perform(post("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDonantes() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList
        restDonanteMockMvc.perform(get("/api/donantes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreEmpresaOParticular").value(hasItem(DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].aportacion").value(hasItem(DEFAULT_APORTACION)))
            .andExpect(jsonPath("$.[*].cuando").value(hasItem(DEFAULT_CUANDO)))
            .andExpect(jsonPath("$.[*].consentimiento").value(hasItem(DEFAULT_CONSENTIMIENTO.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getDonante() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get the donante
        restDonanteMockMvc.perform(get("/api/donantes/{id}", donante.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(donante.getId().intValue()))
            .andExpect(jsonPath("$.nombreEmpresaOParticular").value(DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.telefono").value(DEFAULT_TELEFONO))
            .andExpect(jsonPath("$.direccion").value(DEFAULT_DIRECCION))
            .andExpect(jsonPath("$.localidad").value(DEFAULT_LOCALIDAD))
            .andExpect(jsonPath("$.codigoPostal").value(DEFAULT_CODIGO_POSTAL))
            .andExpect(jsonPath("$.aportacion").value(DEFAULT_APORTACION))
            .andExpect(jsonPath("$.cuando").value(DEFAULT_CUANDO))
            .andExpect(jsonPath("$.consentimiento").value(DEFAULT_CONSENTIMIENTO.booleanValue()));
    }


    @Test
    @Transactional
    public void getDonantesByIdFiltering() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        Long id = donante.getId();

        defaultDonanteShouldBeFound("id.equals=" + id);
        defaultDonanteShouldNotBeFound("id.notEquals=" + id);

        defaultDonanteShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDonanteShouldNotBeFound("id.greaterThan=" + id);

        defaultDonanteShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDonanteShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDonantesByNombreEmpresaOParticularIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where nombreEmpresaOParticular equals to DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldBeFound("nombreEmpresaOParticular.equals=" + DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR);

        // Get all the donanteList where nombreEmpresaOParticular equals to UPDATED_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldNotBeFound("nombreEmpresaOParticular.equals=" + UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);
    }

    @Test
    @Transactional
    public void getAllDonantesByNombreEmpresaOParticularIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where nombreEmpresaOParticular not equals to DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldNotBeFound("nombreEmpresaOParticular.notEquals=" + DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR);

        // Get all the donanteList where nombreEmpresaOParticular not equals to UPDATED_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldBeFound("nombreEmpresaOParticular.notEquals=" + UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);
    }

    @Test
    @Transactional
    public void getAllDonantesByNombreEmpresaOParticularIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where nombreEmpresaOParticular in DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR or UPDATED_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldBeFound("nombreEmpresaOParticular.in=" + DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR + "," + UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);

        // Get all the donanteList where nombreEmpresaOParticular equals to UPDATED_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldNotBeFound("nombreEmpresaOParticular.in=" + UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);
    }

    @Test
    @Transactional
    public void getAllDonantesByNombreEmpresaOParticularIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where nombreEmpresaOParticular is not null
        defaultDonanteShouldBeFound("nombreEmpresaOParticular.specified=true");

        // Get all the donanteList where nombreEmpresaOParticular is null
        defaultDonanteShouldNotBeFound("nombreEmpresaOParticular.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByNombreEmpresaOParticularContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where nombreEmpresaOParticular contains DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldBeFound("nombreEmpresaOParticular.contains=" + DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR);

        // Get all the donanteList where nombreEmpresaOParticular contains UPDATED_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldNotBeFound("nombreEmpresaOParticular.contains=" + UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);
    }

    @Test
    @Transactional
    public void getAllDonantesByNombreEmpresaOParticularNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where nombreEmpresaOParticular does not contain DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldNotBeFound("nombreEmpresaOParticular.doesNotContain=" + DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR);

        // Get all the donanteList where nombreEmpresaOParticular does not contain UPDATED_NOMBRE_EMPRESA_O_PARTICULAR
        defaultDonanteShouldBeFound("nombreEmpresaOParticular.doesNotContain=" + UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);
    }


    @Test
    @Transactional
    public void getAllDonantesByEmailIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where email equals to DEFAULT_EMAIL
        defaultDonanteShouldBeFound("email.equals=" + DEFAULT_EMAIL);

        // Get all the donanteList where email equals to UPDATED_EMAIL
        defaultDonanteShouldNotBeFound("email.equals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDonantesByEmailIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where email not equals to DEFAULT_EMAIL
        defaultDonanteShouldNotBeFound("email.notEquals=" + DEFAULT_EMAIL);

        // Get all the donanteList where email not equals to UPDATED_EMAIL
        defaultDonanteShouldBeFound("email.notEquals=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDonantesByEmailIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where email in DEFAULT_EMAIL or UPDATED_EMAIL
        defaultDonanteShouldBeFound("email.in=" + DEFAULT_EMAIL + "," + UPDATED_EMAIL);

        // Get all the donanteList where email equals to UPDATED_EMAIL
        defaultDonanteShouldNotBeFound("email.in=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDonantesByEmailIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where email is not null
        defaultDonanteShouldBeFound("email.specified=true");

        // Get all the donanteList where email is null
        defaultDonanteShouldNotBeFound("email.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByEmailContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where email contains DEFAULT_EMAIL
        defaultDonanteShouldBeFound("email.contains=" + DEFAULT_EMAIL);

        // Get all the donanteList where email contains UPDATED_EMAIL
        defaultDonanteShouldNotBeFound("email.contains=" + UPDATED_EMAIL);
    }

    @Test
    @Transactional
    public void getAllDonantesByEmailNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where email does not contain DEFAULT_EMAIL
        defaultDonanteShouldNotBeFound("email.doesNotContain=" + DEFAULT_EMAIL);

        // Get all the donanteList where email does not contain UPDATED_EMAIL
        defaultDonanteShouldBeFound("email.doesNotContain=" + UPDATED_EMAIL);
    }


    @Test
    @Transactional
    public void getAllDonantesByTelefonoIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where telefono equals to DEFAULT_TELEFONO
        defaultDonanteShouldBeFound("telefono.equals=" + DEFAULT_TELEFONO);

        // Get all the donanteList where telefono equals to UPDATED_TELEFONO
        defaultDonanteShouldNotBeFound("telefono.equals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllDonantesByTelefonoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where telefono not equals to DEFAULT_TELEFONO
        defaultDonanteShouldNotBeFound("telefono.notEquals=" + DEFAULT_TELEFONO);

        // Get all the donanteList where telefono not equals to UPDATED_TELEFONO
        defaultDonanteShouldBeFound("telefono.notEquals=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllDonantesByTelefonoIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where telefono in DEFAULT_TELEFONO or UPDATED_TELEFONO
        defaultDonanteShouldBeFound("telefono.in=" + DEFAULT_TELEFONO + "," + UPDATED_TELEFONO);

        // Get all the donanteList where telefono equals to UPDATED_TELEFONO
        defaultDonanteShouldNotBeFound("telefono.in=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllDonantesByTelefonoIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where telefono is not null
        defaultDonanteShouldBeFound("telefono.specified=true");

        // Get all the donanteList where telefono is null
        defaultDonanteShouldNotBeFound("telefono.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByTelefonoContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where telefono contains DEFAULT_TELEFONO
        defaultDonanteShouldBeFound("telefono.contains=" + DEFAULT_TELEFONO);

        // Get all the donanteList where telefono contains UPDATED_TELEFONO
        defaultDonanteShouldNotBeFound("telefono.contains=" + UPDATED_TELEFONO);
    }

    @Test
    @Transactional
    public void getAllDonantesByTelefonoNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where telefono does not contain DEFAULT_TELEFONO
        defaultDonanteShouldNotBeFound("telefono.doesNotContain=" + DEFAULT_TELEFONO);

        // Get all the donanteList where telefono does not contain UPDATED_TELEFONO
        defaultDonanteShouldBeFound("telefono.doesNotContain=" + UPDATED_TELEFONO);
    }


    @Test
    @Transactional
    public void getAllDonantesByDireccionIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where direccion equals to DEFAULT_DIRECCION
        defaultDonanteShouldBeFound("direccion.equals=" + DEFAULT_DIRECCION);

        // Get all the donanteList where direccion equals to UPDATED_DIRECCION
        defaultDonanteShouldNotBeFound("direccion.equals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllDonantesByDireccionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where direccion not equals to DEFAULT_DIRECCION
        defaultDonanteShouldNotBeFound("direccion.notEquals=" + DEFAULT_DIRECCION);

        // Get all the donanteList where direccion not equals to UPDATED_DIRECCION
        defaultDonanteShouldBeFound("direccion.notEquals=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllDonantesByDireccionIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where direccion in DEFAULT_DIRECCION or UPDATED_DIRECCION
        defaultDonanteShouldBeFound("direccion.in=" + DEFAULT_DIRECCION + "," + UPDATED_DIRECCION);

        // Get all the donanteList where direccion equals to UPDATED_DIRECCION
        defaultDonanteShouldNotBeFound("direccion.in=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllDonantesByDireccionIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where direccion is not null
        defaultDonanteShouldBeFound("direccion.specified=true");

        // Get all the donanteList where direccion is null
        defaultDonanteShouldNotBeFound("direccion.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByDireccionContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where direccion contains DEFAULT_DIRECCION
        defaultDonanteShouldBeFound("direccion.contains=" + DEFAULT_DIRECCION);

        // Get all the donanteList where direccion contains UPDATED_DIRECCION
        defaultDonanteShouldNotBeFound("direccion.contains=" + UPDATED_DIRECCION);
    }

    @Test
    @Transactional
    public void getAllDonantesByDireccionNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where direccion does not contain DEFAULT_DIRECCION
        defaultDonanteShouldNotBeFound("direccion.doesNotContain=" + DEFAULT_DIRECCION);

        // Get all the donanteList where direccion does not contain UPDATED_DIRECCION
        defaultDonanteShouldBeFound("direccion.doesNotContain=" + UPDATED_DIRECCION);
    }


    @Test
    @Transactional
    public void getAllDonantesByLocalidadIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where localidad equals to DEFAULT_LOCALIDAD
        defaultDonanteShouldBeFound("localidad.equals=" + DEFAULT_LOCALIDAD);

        // Get all the donanteList where localidad equals to UPDATED_LOCALIDAD
        defaultDonanteShouldNotBeFound("localidad.equals=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllDonantesByLocalidadIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where localidad not equals to DEFAULT_LOCALIDAD
        defaultDonanteShouldNotBeFound("localidad.notEquals=" + DEFAULT_LOCALIDAD);

        // Get all the donanteList where localidad not equals to UPDATED_LOCALIDAD
        defaultDonanteShouldBeFound("localidad.notEquals=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllDonantesByLocalidadIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where localidad in DEFAULT_LOCALIDAD or UPDATED_LOCALIDAD
        defaultDonanteShouldBeFound("localidad.in=" + DEFAULT_LOCALIDAD + "," + UPDATED_LOCALIDAD);

        // Get all the donanteList where localidad equals to UPDATED_LOCALIDAD
        defaultDonanteShouldNotBeFound("localidad.in=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllDonantesByLocalidadIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where localidad is not null
        defaultDonanteShouldBeFound("localidad.specified=true");

        // Get all the donanteList where localidad is null
        defaultDonanteShouldNotBeFound("localidad.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByLocalidadContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where localidad contains DEFAULT_LOCALIDAD
        defaultDonanteShouldBeFound("localidad.contains=" + DEFAULT_LOCALIDAD);

        // Get all the donanteList where localidad contains UPDATED_LOCALIDAD
        defaultDonanteShouldNotBeFound("localidad.contains=" + UPDATED_LOCALIDAD);
    }

    @Test
    @Transactional
    public void getAllDonantesByLocalidadNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where localidad does not contain DEFAULT_LOCALIDAD
        defaultDonanteShouldNotBeFound("localidad.doesNotContain=" + DEFAULT_LOCALIDAD);

        // Get all the donanteList where localidad does not contain UPDATED_LOCALIDAD
        defaultDonanteShouldBeFound("localidad.doesNotContain=" + UPDATED_LOCALIDAD);
    }


    @Test
    @Transactional
    public void getAllDonantesByCodigoPostalIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where codigoPostal equals to DEFAULT_CODIGO_POSTAL
        defaultDonanteShouldBeFound("codigoPostal.equals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the donanteList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultDonanteShouldNotBeFound("codigoPostal.equals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllDonantesByCodigoPostalIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where codigoPostal not equals to DEFAULT_CODIGO_POSTAL
        defaultDonanteShouldNotBeFound("codigoPostal.notEquals=" + DEFAULT_CODIGO_POSTAL);

        // Get all the donanteList where codigoPostal not equals to UPDATED_CODIGO_POSTAL
        defaultDonanteShouldBeFound("codigoPostal.notEquals=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllDonantesByCodigoPostalIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where codigoPostal in DEFAULT_CODIGO_POSTAL or UPDATED_CODIGO_POSTAL
        defaultDonanteShouldBeFound("codigoPostal.in=" + DEFAULT_CODIGO_POSTAL + "," + UPDATED_CODIGO_POSTAL);

        // Get all the donanteList where codigoPostal equals to UPDATED_CODIGO_POSTAL
        defaultDonanteShouldNotBeFound("codigoPostal.in=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllDonantesByCodigoPostalIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where codigoPostal is not null
        defaultDonanteShouldBeFound("codigoPostal.specified=true");

        // Get all the donanteList where codigoPostal is null
        defaultDonanteShouldNotBeFound("codigoPostal.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByCodigoPostalContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where codigoPostal contains DEFAULT_CODIGO_POSTAL
        defaultDonanteShouldBeFound("codigoPostal.contains=" + DEFAULT_CODIGO_POSTAL);

        // Get all the donanteList where codigoPostal contains UPDATED_CODIGO_POSTAL
        defaultDonanteShouldNotBeFound("codigoPostal.contains=" + UPDATED_CODIGO_POSTAL);
    }

    @Test
    @Transactional
    public void getAllDonantesByCodigoPostalNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where codigoPostal does not contain DEFAULT_CODIGO_POSTAL
        defaultDonanteShouldNotBeFound("codigoPostal.doesNotContain=" + DEFAULT_CODIGO_POSTAL);

        // Get all the donanteList where codigoPostal does not contain UPDATED_CODIGO_POSTAL
        defaultDonanteShouldBeFound("codigoPostal.doesNotContain=" + UPDATED_CODIGO_POSTAL);
    }


    @Test
    @Transactional
    public void getAllDonantesByAportacionIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where aportacion equals to DEFAULT_APORTACION
        defaultDonanteShouldBeFound("aportacion.equals=" + DEFAULT_APORTACION);

        // Get all the donanteList where aportacion equals to UPDATED_APORTACION
        defaultDonanteShouldNotBeFound("aportacion.equals=" + UPDATED_APORTACION);
    }

    @Test
    @Transactional
    public void getAllDonantesByAportacionIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where aportacion not equals to DEFAULT_APORTACION
        defaultDonanteShouldNotBeFound("aportacion.notEquals=" + DEFAULT_APORTACION);

        // Get all the donanteList where aportacion not equals to UPDATED_APORTACION
        defaultDonanteShouldBeFound("aportacion.notEquals=" + UPDATED_APORTACION);
    }

    @Test
    @Transactional
    public void getAllDonantesByAportacionIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where aportacion in DEFAULT_APORTACION or UPDATED_APORTACION
        defaultDonanteShouldBeFound("aportacion.in=" + DEFAULT_APORTACION + "," + UPDATED_APORTACION);

        // Get all the donanteList where aportacion equals to UPDATED_APORTACION
        defaultDonanteShouldNotBeFound("aportacion.in=" + UPDATED_APORTACION);
    }

    @Test
    @Transactional
    public void getAllDonantesByAportacionIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where aportacion is not null
        defaultDonanteShouldBeFound("aportacion.specified=true");

        // Get all the donanteList where aportacion is null
        defaultDonanteShouldNotBeFound("aportacion.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByAportacionContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where aportacion contains DEFAULT_APORTACION
        defaultDonanteShouldBeFound("aportacion.contains=" + DEFAULT_APORTACION);

        // Get all the donanteList where aportacion contains UPDATED_APORTACION
        defaultDonanteShouldNotBeFound("aportacion.contains=" + UPDATED_APORTACION);
    }

    @Test
    @Transactional
    public void getAllDonantesByAportacionNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where aportacion does not contain DEFAULT_APORTACION
        defaultDonanteShouldNotBeFound("aportacion.doesNotContain=" + DEFAULT_APORTACION);

        // Get all the donanteList where aportacion does not contain UPDATED_APORTACION
        defaultDonanteShouldBeFound("aportacion.doesNotContain=" + UPDATED_APORTACION);
    }


    @Test
    @Transactional
    public void getAllDonantesByCuandoIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where cuando equals to DEFAULT_CUANDO
        defaultDonanteShouldBeFound("cuando.equals=" + DEFAULT_CUANDO);

        // Get all the donanteList where cuando equals to UPDATED_CUANDO
        defaultDonanteShouldNotBeFound("cuando.equals=" + UPDATED_CUANDO);
    }

    @Test
    @Transactional
    public void getAllDonantesByCuandoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where cuando not equals to DEFAULT_CUANDO
        defaultDonanteShouldNotBeFound("cuando.notEquals=" + DEFAULT_CUANDO);

        // Get all the donanteList where cuando not equals to UPDATED_CUANDO
        defaultDonanteShouldBeFound("cuando.notEquals=" + UPDATED_CUANDO);
    }

    @Test
    @Transactional
    public void getAllDonantesByCuandoIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where cuando in DEFAULT_CUANDO or UPDATED_CUANDO
        defaultDonanteShouldBeFound("cuando.in=" + DEFAULT_CUANDO + "," + UPDATED_CUANDO);

        // Get all the donanteList where cuando equals to UPDATED_CUANDO
        defaultDonanteShouldNotBeFound("cuando.in=" + UPDATED_CUANDO);
    }

    @Test
    @Transactional
    public void getAllDonantesByCuandoIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where cuando is not null
        defaultDonanteShouldBeFound("cuando.specified=true");

        // Get all the donanteList where cuando is null
        defaultDonanteShouldNotBeFound("cuando.specified=false");
    }
                @Test
    @Transactional
    public void getAllDonantesByCuandoContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where cuando contains DEFAULT_CUANDO
        defaultDonanteShouldBeFound("cuando.contains=" + DEFAULT_CUANDO);

        // Get all the donanteList where cuando contains UPDATED_CUANDO
        defaultDonanteShouldNotBeFound("cuando.contains=" + UPDATED_CUANDO);
    }

    @Test
    @Transactional
    public void getAllDonantesByCuandoNotContainsSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where cuando does not contain DEFAULT_CUANDO
        defaultDonanteShouldNotBeFound("cuando.doesNotContain=" + DEFAULT_CUANDO);

        // Get all the donanteList where cuando does not contain UPDATED_CUANDO
        defaultDonanteShouldBeFound("cuando.doesNotContain=" + UPDATED_CUANDO);
    }


    @Test
    @Transactional
    public void getAllDonantesByConsentimientoIsEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where consentimiento equals to DEFAULT_CONSENTIMIENTO
        defaultDonanteShouldBeFound("consentimiento.equals=" + DEFAULT_CONSENTIMIENTO);

        // Get all the donanteList where consentimiento equals to UPDATED_CONSENTIMIENTO
        defaultDonanteShouldNotBeFound("consentimiento.equals=" + UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void getAllDonantesByConsentimientoIsNotEqualToSomething() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where consentimiento not equals to DEFAULT_CONSENTIMIENTO
        defaultDonanteShouldNotBeFound("consentimiento.notEquals=" + DEFAULT_CONSENTIMIENTO);

        // Get all the donanteList where consentimiento not equals to UPDATED_CONSENTIMIENTO
        defaultDonanteShouldBeFound("consentimiento.notEquals=" + UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void getAllDonantesByConsentimientoIsInShouldWork() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where consentimiento in DEFAULT_CONSENTIMIENTO or UPDATED_CONSENTIMIENTO
        defaultDonanteShouldBeFound("consentimiento.in=" + DEFAULT_CONSENTIMIENTO + "," + UPDATED_CONSENTIMIENTO);

        // Get all the donanteList where consentimiento equals to UPDATED_CONSENTIMIENTO
        defaultDonanteShouldNotBeFound("consentimiento.in=" + UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void getAllDonantesByConsentimientoIsNullOrNotNull() throws Exception {
        // Initialize the database
        donanteRepository.saveAndFlush(donante);

        // Get all the donanteList where consentimiento is not null
        defaultDonanteShouldBeFound("consentimiento.specified=true");

        // Get all the donanteList where consentimiento is null
        defaultDonanteShouldNotBeFound("consentimiento.specified=false");
    }
    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDonanteShouldBeFound(String filter) throws Exception {
        restDonanteMockMvc.perform(get("/api/donantes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(donante.getId().intValue())))
            .andExpect(jsonPath("$.[*].nombreEmpresaOParticular").value(hasItem(DEFAULT_NOMBRE_EMPRESA_O_PARTICULAR)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].telefono").value(hasItem(DEFAULT_TELEFONO)))
            .andExpect(jsonPath("$.[*].direccion").value(hasItem(DEFAULT_DIRECCION)))
            .andExpect(jsonPath("$.[*].localidad").value(hasItem(DEFAULT_LOCALIDAD)))
            .andExpect(jsonPath("$.[*].codigoPostal").value(hasItem(DEFAULT_CODIGO_POSTAL)))
            .andExpect(jsonPath("$.[*].aportacion").value(hasItem(DEFAULT_APORTACION)))
            .andExpect(jsonPath("$.[*].cuando").value(hasItem(DEFAULT_CUANDO)))
            .andExpect(jsonPath("$.[*].consentimiento").value(hasItem(DEFAULT_CONSENTIMIENTO.booleanValue())));

        // Check, that the count call also returns 1
        restDonanteMockMvc.perform(get("/api/donantes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDonanteShouldNotBeFound(String filter) throws Exception {
        restDonanteMockMvc.perform(get("/api/donantes?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDonanteMockMvc.perform(get("/api/donantes/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDonante() throws Exception {
        // Get the donante
        restDonanteMockMvc.perform(get("/api/donantes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDonante() throws Exception {
        // Initialize the database
        donanteService.save(donante);

        int databaseSizeBeforeUpdate = donanteRepository.findAll().size();

        // Update the donante
        Donante updatedDonante = donanteRepository.findById(donante.getId()).get();
        // Disconnect from session so that the updates on updatedDonante are not directly saved in db
        em.detach(updatedDonante);
        updatedDonante
            .nombreEmpresaOParticular(UPDATED_NOMBRE_EMPRESA_O_PARTICULAR)
            .email(UPDATED_EMAIL)
            .telefono(UPDATED_TELEFONO)
            .direccion(UPDATED_DIRECCION)
            .localidad(UPDATED_LOCALIDAD)
            .codigoPostal(UPDATED_CODIGO_POSTAL)
            .aportacion(UPDATED_APORTACION)
            .cuando(UPDATED_CUANDO)
            .consentimiento(UPDATED_CONSENTIMIENTO);

        restDonanteMockMvc.perform(put("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDonante)))
            .andExpect(status().isOk());

        // Validate the Donante in the database
        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeUpdate);
        Donante testDonante = donanteList.get(donanteList.size() - 1);
        assertThat(testDonante.getNombreEmpresaOParticular()).isEqualTo(UPDATED_NOMBRE_EMPRESA_O_PARTICULAR);
        assertThat(testDonante.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testDonante.getTelefono()).isEqualTo(UPDATED_TELEFONO);
        assertThat(testDonante.getDireccion()).isEqualTo(UPDATED_DIRECCION);
        assertThat(testDonante.getLocalidad()).isEqualTo(UPDATED_LOCALIDAD);
        assertThat(testDonante.getCodigoPostal()).isEqualTo(UPDATED_CODIGO_POSTAL);
        assertThat(testDonante.getAportacion()).isEqualTo(UPDATED_APORTACION);
        assertThat(testDonante.getCuando()).isEqualTo(UPDATED_CUANDO);
        assertThat(testDonante.isConsentimiento()).isEqualTo(UPDATED_CONSENTIMIENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingDonante() throws Exception {
        int databaseSizeBeforeUpdate = donanteRepository.findAll().size();

        // Create the Donante

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDonanteMockMvc.perform(put("/api/donantes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(donante)))
            .andExpect(status().isBadRequest());

        // Validate the Donante in the database
        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDonante() throws Exception {
        // Initialize the database
        donanteService.save(donante);

        int databaseSizeBeforeDelete = donanteRepository.findAll().size();

        // Delete the donante
        restDonanteMockMvc.perform(delete("/api/donantes/{id}", donante.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Donante> donanteList = donanteRepository.findAll();
        assertThat(donanteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
