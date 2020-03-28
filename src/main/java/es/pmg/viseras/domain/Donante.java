package es.pmg.viseras.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Donante.
 */
@Entity
@Table(name = "donante")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Donante implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "nombre_empresa_o_particular", length = 100, nullable = false)
    private String nombreEmpresaOParticular;

    @NotNull
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Size(max = 30)
    @Column(name = "telefono", length = 30, nullable = false)
    private String telefono;

    @Size(max = 100)
    @Column(name = "direccion", length = 100)
    private String direccion;

    @Size(max = 100)
    @Column(name = "localidad", length = 100)
    private String localidad;

    @Size(max = 10)
    @Column(name = "codigo_postal", length = 10)
    private String codigoPostal;

    @NotNull
    @Size(max = 200)
    @Column(name = "aportacion", length = 200, nullable = false)
    private String aportacion;

    @NotNull
    @Size(max = 200)
    @Column(name = "cuando", length = 200, nullable = false)
    private String cuando;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresaOParticular() {
        return nombreEmpresaOParticular;
    }

    public Donante nombreEmpresaOParticular(String nombreEmpresaOParticular) {
        this.nombreEmpresaOParticular = nombreEmpresaOParticular;
        return this;
    }

    public void setNombreEmpresaOParticular(String nombreEmpresaOParticular) {
        this.nombreEmpresaOParticular = nombreEmpresaOParticular;
    }

    public String getEmail() {
        return email;
    }

    public Donante email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public Donante telefono(String telefono) {
        this.telefono = telefono;
        return this;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public Donante direccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public Donante localidad(String localidad) {
        this.localidad = localidad;
        return this;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public Donante codigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
        return this;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getAportacion() {
        return aportacion;
    }

    public Donante aportacion(String aportacion) {
        this.aportacion = aportacion;
        return this;
    }

    public void setAportacion(String aportacion) {
        this.aportacion = aportacion;
    }

    public String getCuando() {
        return cuando;
    }

    public Donante cuando(String cuando) {
        this.cuando = cuando;
        return this;
    }

    public void setCuando(String cuando) {
        this.cuando = cuando;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Donante)) {
            return false;
        }
        return id != null && id.equals(((Donante) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Donante{" +
            "id=" + getId() +
            ", nombreEmpresaOParticular='" + getNombreEmpresaOParticular() + "'" +
            ", email='" + getEmail() + "'" +
            ", telefono='" + getTelefono() + "'" +
            ", direccion='" + getDireccion() + "'" +
            ", localidad='" + getLocalidad() + "'" +
            ", codigoPostal='" + getCodigoPostal() + "'" +
            ", aportacion='" + getAportacion() + "'" +
            ", cuando='" + getCuando() + "'" +
            "}";
    }
}
