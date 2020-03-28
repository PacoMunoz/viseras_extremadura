import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IDonante, Donante } from 'app/shared/model/donante.model';
import { DonanteService } from './donante.service';

@Component({
  selector: 'jhi-donante-update',
  templateUrl: './donante-update.component.html'
})
export class DonanteUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nombreEmpresaOParticular: [null, [Validators.required, Validators.maxLength(100)]],
    email: [null, [Validators.required, Validators.pattern('^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$')]],
    telefono: [null, [Validators.required, Validators.maxLength(30)]],
    direccion: [null, [Validators.maxLength(100)]],
    localidad: [null, [Validators.maxLength(100)]],
    codigoPostal: [null, [Validators.maxLength(10)]],
    aportacion: [null, [Validators.required, Validators.maxLength(200)]],
    cuando: [null, [Validators.required, Validators.maxLength(200)]],
    consentimiento: [null, [Validators.required]]
  });

  constructor(protected donanteService: DonanteService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ donante }) => {
      this.updateForm(donante);
    });
  }

  updateForm(donante: IDonante): void {
    this.editForm.patchValue({
      id: donante.id,
      nombreEmpresaOParticular: donante.nombreEmpresaOParticular,
      email: donante.email,
      telefono: donante.telefono,
      direccion: donante.direccion,
      localidad: donante.localidad,
      codigoPostal: donante.codigoPostal,
      aportacion: donante.aportacion,
      cuando: donante.cuando,
      consentimiento: donante.consentimiento
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const donante = this.createFromForm();
    if (donante.id !== undefined) {
      this.subscribeToSaveResponse(this.donanteService.update(donante));
    } else {
      this.subscribeToSaveResponse(this.donanteService.create(donante));
    }
  }

  private createFromForm(): IDonante {
    return {
      ...new Donante(),
      id: this.editForm.get(['id'])!.value,
      nombreEmpresaOParticular: this.editForm.get(['nombreEmpresaOParticular'])!.value,
      email: this.editForm.get(['email'])!.value,
      telefono: this.editForm.get(['telefono'])!.value,
      direccion: this.editForm.get(['direccion'])!.value,
      localidad: this.editForm.get(['localidad'])!.value,
      codigoPostal: this.editForm.get(['codigoPostal'])!.value,
      aportacion: this.editForm.get(['aportacion'])!.value,
      cuando: this.editForm.get(['cuando'])!.value,
      consentimiento: this.editForm.get(['consentimiento'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDonante>>): void {
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
