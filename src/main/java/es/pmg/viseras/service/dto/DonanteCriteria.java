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
 * Criteria class for the {@link es.pmg.viseras.domain.Donante} entity. This class is used
 * in {@link es.pmg.viseras.web.rest.DonanteResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /donantes?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DonanteCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter nombreEmpresaOParticular;

    private StringFilter email;

    private StringFilter telefono;

    private StringFilter direccion;

    private StringFilter localidad;

    private StringFilter codigoPostal;

    private StringFilter aportacion;

    private StringFilter cuando;

    public DonanteCriteria() {
    }

    public DonanteCriteria(DonanteCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.nombreEmpresaOParticular = other.nombreEmpresaOParticular == null ? null : other.nombreEmpresaOParticular.copy();
        this.email = other.email == null ? null : other.email.copy();
        this.telefono = other.telefono == null ? null : other.telefono.copy();
        this.direccion = other.direccion == null ? null : other.direccion.copy();
        this.localidad = other.localidad == null ? null : other.localidad.copy();
        this.codigoPostal = other.codigoPostal == null ? null : other.codigoPostal.copy();
        this.aportacion = other.aportacion == null ? null : other.aportacion.copy();
        this.cuando = other.cuando == null ? null : other.cuando.copy();
    }

    @Override
    public DonanteCriteria copy() {
        return new DonanteCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getNombreEmpresaOParticular() {
        return nombreEmpresaOParticular;
    }

    public void setNombreEmpresaOParticular(StringFilter nombreEmpresaOParticular) {
        this.nombreEmpresaOParticular = nombreEmpresaOParticular;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
    }

    public StringFilter getTelefono() {
        return telefono;
    }

    public void setTelefono(StringFilter telefono) {
        this.telefono = telefono;
    }

    public StringFilter getDireccion() {
        return direccion;
    }

    public void setDireccion(StringFilter direccion) {
        this.direccion = direccion;
    }

    public StringFilter getLocalidad() {
        return localidad;
    }

    public void setLocalidad(StringFilter localidad) {
        this.localidad = localidad;
    }

    public StringFilter getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(StringFilter codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public StringFilter getAportacion() {
        return aportacion;
    }

    public void setAportacion(StringFilter aportacion) {
        this.aportacion = aportacion;
    }

    public StringFilter getCuando() {
        return cuando;
    }

    public void setCuando(StringFilter cuando) {
        this.cuando = cuando;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DonanteCriteria that = (DonanteCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(nombreEmpresaOParticular, that.nombreEmpresaOParticular) &&
            Objects.equals(email, that.email) &&
            Objects.equals(telefono, that.telefono) &&
            Objects.equals(direccion, that.direccion) &&
            Objects.equals(localidad, that.localidad) &&
            Objects.equals(codigoPostal, that.codigoPostal) &&
            Objects.equals(aportacion, that.aportacion) &&
            Objects.equals(cuando, that.cuando);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        nombreEmpresaOParticular,
        email,
        telefono,
        direccion,
        localidad,
        codigoPostal,
        aportacion,
        cuando
        );
    }

    @Override
    public String toString() {
        return "DonanteCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (nombreEmpresaOParticular != null ? "nombreEmpresaOParticular=" + nombreEmpresaOParticular + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (telefono != null ? "telefono=" + telefono + ", " : "") +
                (direccion != null ? "direccion=" + direccion + ", " : "") +
                (localidad != null ? "localidad=" + localidad + ", " : "") +
                (codigoPostal != null ? "codigoPostal=" + codigoPostal + ", " : "") +
                (aportacion != null ? "aportacion=" + aportacion + ", " : "") +
                (cuando != null ? "cuando=" + cuando + ", " : "") +
            "}";
    }

}
