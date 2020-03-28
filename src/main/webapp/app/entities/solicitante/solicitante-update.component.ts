import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISolicitante, Solicitante } from 'app/shared/model/solicitante.model';
import { SolicitanteService } from './solicitante.service';

@Component({
  selector: 'jhi-solicitante-update',
  templateUrl: './solicitante-update.component.html'
})
export class SolicitanteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombreCentro: [null, [Validators.required, Validators.maxLength(100)]],
    personaContacto: [null, [Validators.required, Validators.maxLength(100)]],
    telefono: [null, [Validators.required, Validators.maxLength(30)]],
    email: [null, [Validators.required, Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    localidad: [null, [Validators.required, Validators.maxLength(30)]],
    direccion: [null, [Validators.required, Validators.maxLength(100)]],
    codigoPostal: [null, [Validators.required, Validators.maxLength(10)]],
    aceptaNoHomologado: [null, [Validators.required]],
    necesidad: [null, [Validators.required]],
    horariosEntrega: [null, [Validators.required, Validators.maxLength(100)]],
    comentarios: [null, [Validators.maxLength(250)]],
    consentimiento: [null, [Validators.required]]
  });

  constructor(protected solicitanteService: SolicitanteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ solicitante }) => {
      this.updateForm(solicitante);
    });
  }

  updateForm(solicitante: ISolicitante): void {
    this.editForm.patchValue({
      id: solicitante.id,
      nombreCentro: solicitante.nombreCentro,
      personaContacto: solicitante.personaContacto,
      telefono: solicitante.telefono,
      email: solicitante.email,
      localidad: solicitante.localidad,
      direccion: solicitante.direccion,
      codigoPostal: solicitante.codigoPostal,
      aceptaNoHomologado: solicitante.aceptaNoHomologado,
      necesidad: solicitante.necesidad,
      horariosEntrega: solicitante.horariosEntrega,
      comentarios: solicitante.comentarios,
      consentimiento: solicitante.consentimiento
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const solicitante = this.createFromForm();
    if (solicitante.id !== undefined) {
      this.subscribeToSaveResponse(this.solicitanteService.update(solicitante));
    } else {
      this.subscribeToSaveResponse(this.solicitanteService.create(solicitante));
    }
  }

  private createFromForm(): ISolicitante {
    return {
      ...new Solicitante(),
      id: this.editForm.get(['id'])!.value,
      nombreCentro: this.editForm.get(['nombreCentro'])!.value,
      personaContacto: this.editForm.get(['personaContacto'])!.value,
      telefono: this.editForm.get(['telefono'])!.value,
      email: this.editForm.get(['email'])!.value,
      localidad: this.editForm.get(['localidad'])!.value,
      direccion: this.editForm.get(['direccion'])!.value,
      codigoPostal: this.editForm.get(['codigoPostal'])!.value,
      aceptaNoHomologado: this.editForm.get(['aceptaNoHomologado'])!.value,
      necesidad: this.editForm.get(['necesidad'])!.value,
      horariosEntrega: this.editForm.get(['horariosEntrega'])!.value,
      comentarios: this.editForm.get(['comentarios'])!.value,
      consentimiento: this.editForm.get(['consentimiento'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISolicitante>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
