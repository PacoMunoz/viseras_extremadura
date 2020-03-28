package es.pmg.viseras.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Solicitante.
 */
@Entity
@Table(name = "solicitante")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Solicitante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nombre_centro", length = 100, nullable = false)
    private String nombreCentro;

    @NotNull
    @Size(max = 100)
    @Column(name = "persona_contacto", length = 100, nullable = false)
    private String personaContacto;

    @NotNull
    @Size(max = 30)
    @Column(name = "telefono", length = 30, nullable = false)
    private String telefono;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(max = 30)
    @Column(name = "localidad", length = 30, nullable = false)
    private String localidad;

    @NotNull
    @Size(max = 100)
    @Column(name = "direccion", length = 100, nullable = false)
    private String direccion;

    @NotNull
    @Size(max = 10)
    @Column(name = "codigo_postal", length = 10, nullable = false)
    private String codigoPostal;

    @NotNull
    @Column(name = "acepta_no_homologado", nullable = false)
    private Boolean aceptaNoHomologado;

    @NotNull
    @Column(name = "necesidad", nullable = false)
    private Integer necesidad;

    @NotNull
    @Size(max = 100)
    @Column(name = "horarios_entrega", length = 100, nullable = false)
    private String horariosEntrega;

    @Size(max = 250)
    @Column(name = "comentarios", length = 250)
    private String comentarios;

    @NotNull
    @Column(name = "consentimiento", nullable = false)
    private Boolean consentimiento;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public Solicitante nombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
        return this;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public Solicitante personaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
        return this;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelefono() {
        return telefono;
    }

    public Solicitante telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public Solicitante email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocalidad() {
        return localidad;
    }

    public Solicitante localidad(String localidad) {
        this.localidad = localidad;
        return this;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public Solicitante direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public Solicitante codigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
        return this;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Boolean isAceptaNoHomologado() {
        return aceptaNoHomologado;
    }

    public Solicitante aceptaNoHomologado(Boolean aceptaNoHomologado) {
        this.aceptaNoHomologado = aceptaNoHomologado;
        return this;
    }

    public void setAceptaNoHomologado(Boolean aceptaNoHomologado) {
        this.aceptaNoHomologado = aceptaNoHomologado;
    }

    public Integer getNecesidad() {
        return necesidad;
    }

    public Solicitante necesidad(Integer necesidad) {
        this.necesidad = necesidad;
        return this;
    }

    public void setNecesidad(Integer necesidad) {
        this.necesidad = necesidad;
    }

    public String getHorariosEntrega() {
        return horariosEntrega;
    }

    public Solicitante horariosEntrega(String horariosEntrega) {
        this.horariosEntrega = horariosEntrega;
        return this;
    }

    public void setHorariosEntrega(String horariosEntrega) {
        this.horariosEntrega = horariosEntrega;
    }

    public String getComentarios() {
        return comentarios;
    }

    public Solicitante comentarios(String comentarios) {
        this.comentarios = comentarios;
        return this;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Boolean isConsentimiento() {
        return consentimiento;
    }

    public Solicitante consentimiento(Boolean consentimiento) {
        this.consentimiento = consentimiento;
        return this;
    }

    public void setConsentimiento(Boolean consentimiento) {
        this.consentimiento = consentimiento;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Solicitante)) {
            return false;
        }
        return id != null && id.equals(((Solicitante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Solicitante{" +
            "id=" + getId() +
            ", nombreCentro='" + getNombreCentro() + "'" +
            ", personaContacto='" + getPersonaContacto() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", email='" + getEmail() + "'" +
            ", localidad='" + getLocalidad() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", codigoPostal='" + getCodigoPostal() + "'" +
            ", aceptaNoHomologado='" + isAceptaNoHomologado() + "'" +
            ", necesidad=" + getNecesidad() +
            ", horariosEntrega='" + getHorariosEntrega() + "'" +
            ", comentarios='" + getComentarios() + "'" +
            ", consentimiento='" + isConsentimiento() + "'" +
            "}";
    }
}
