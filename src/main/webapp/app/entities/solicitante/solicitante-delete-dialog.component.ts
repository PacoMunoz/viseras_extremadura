import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISolicitante } from 'app/shared/model/solicitante.model';
import { SolicitanteService } from './solicitante.service';

@Component({
  templateUrl: './solicitante-delete-dialog.component.html'
})
export class SolicitanteDeleteDialogComponent {
  solicitante?: ISolicitante;

  constructor(
    protected solicitanteService: SolicitanteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.solicitanteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('solicitanteListModification');
      this.activeModal.close();
    });
  }
}
