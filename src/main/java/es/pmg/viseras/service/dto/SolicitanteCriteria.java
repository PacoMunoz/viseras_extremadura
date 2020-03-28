package es.pmg.viseras.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link es.pmg.viseras.domain.Solicitante} entity. This class is used
 * in {@link es.pmg.viseras.web.rest.SolicitanteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /solicitantes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class SolicitanteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombreCentro;

    private StringFilter personaContacto;

    private StringFilter telefono;

    private StringFilter email;

    private StringFilter localidad;

    private StringFilter direccion;

    private StringFilter codigoPostal;

    private BooleanFilter aceptaNoHomologado;

    private IntegerFilter necesidad;

    private StringFilter horariosEntrega;

    private StringFilter comentarios;

    private BooleanFilter consentimiento;

    public SolicitanteCriteria() {
    }

    public SolicitanteCriteria(SolicitanteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombreCentro = other.nombreCentro == null ? null : other.nombreCentro.copy();
        this.personaContacto = other.personaContacto == null ? null : other.personaContacto.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.localidad = other.localidad == null ? null : other.localidad.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.codigoPostal = other.codigoPostal == null ? null : other.codigoPostal.copy();
        this.aceptaNoHomologado = other.aceptaNoHomologado == null ? null : other.aceptaNoHomologado.copy();
        this.necesidad = other.necesidad == null ? null : other.necesidad.copy();
        this.horariosEntrega = other.horariosEntrega == null ? null : other.horariosEntrega.copy();
        this.comentarios = other.comentarios == null ? null : other.comentarios.copy();
        this.consentimiento = other.consentimiento == null ? null : other.consentimiento.copy();
    }

    @Override
    public SolicitanteCriteria copy() {
        return new SolicitanteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(StringFilter nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public StringFilter getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(StringFilter personaContacto) {
        this.personaContacto = personaContacto;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getLocalidad() {
        return localidad;
    }

    public void setLocalidad(StringFilter localidad) {
        this.localidad = localidad;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public StringFilter getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(StringFilter codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public BooleanFilter getAceptaNoHomologado() {
        return aceptaNoHomologado;
    }

    public void setAceptaNoHomologado(BooleanFilter aceptaNoHomologado) {
        this.aceptaNoHomologado = aceptaNoHomologado;
    }

    public IntegerFilter getNecesidad() {
        return necesidad;
    }

    public void setNecesidad(IntegerFilter necesidad) {
        this.necesidad = necesidad;
    }

    public StringFilter getHorariosEntrega() {
        return horariosEntrega;
    }

    public void setHorariosEntrega(StringFilter horariosEntrega) {
        this.horariosEntrega = horariosEntrega;
    }

    public StringFilter getComentarios() {
        return comentarios;
    }

    public void setComentarios(StringFilter comentarios) {
        this.comentarios = comentarios;
    }

    public BooleanFilter getConsentimiento() {
        return consentimiento;
    }

    public void setConsentimiento(BooleanFilter consentimiento) {
        this.consentimiento = consentimiento;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SolicitanteCriteria that = (SolicitanteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombreCentro, that.nombreCentro) &&
            Objects.equals(personaContacto, that.personaContacto) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(email, that.email) &&
            Objects.equals(localidad, that.localidad) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(codigoPostal, that.codigoPostal) &&
            Objects.equals(aceptaNoHomologado, that.aceptaNoHomologado) &&
            Objects.equals(necesidad, that.necesidad) &&
            Objects.equals(horariosEntrega, that.horariosEntrega) &&
            Objects.equals(comentarios, that.comentarios) &&
            Objects.equals(consentimiento, that.consentimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombreCentro,
        personaContacto,
        telefono,
        email,
        localidad,
        direccion,
        codigoPostal,
        aceptaNoHomologado,
        necesidad,
        horariosEntrega,
        comentarios,
        consentimiento
        );
    }

    @Override
    public String toString() {
        return "SolicitanteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombreCentro != null ? "nombreCentro=" + nombreCentro + ", " : "") +
                (personaContacto != null ? "personaContacto=" + personaContacto + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (localidad != null ? "localidad=" + localidad + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (codigoPostal != null ? "codigoPostal=" + codigoPostal + ", " : "") +
                (aceptaNoHomologado != null ? "aceptaNoHomologado=" + aceptaNoHomologado + ", " : "") +
                (necesidad != null ? "necesidad=" + necesidad + ", " : "") +
                (horariosEntrega != null ? "horariosEntrega=" + horariosEntrega + ", " : "") +
                (comentarios != null ? "comentarios=" + comentarios + ", " : "") +
                (consentimiento != null ? "consentimiento=" + consentimiento + ", " : "") +
            "}";
    }

}
