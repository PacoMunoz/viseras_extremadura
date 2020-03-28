import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDonante } from 'app/shared/model/donante.model';
import { DonanteService } from './donante.service';

@Component({
  templateUrl: './donante-delete-dialog.component.html'
})
export class DonanteDeleteDialogComponent {
  donante?: IDonante;

  constructor(protected donanteService: DonanteService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.donanteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('donanteListModification');
      this.activeModal.close();
    });
  }
}
