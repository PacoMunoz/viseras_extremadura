import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViserasExtremaduraSurSharedModule } from 'app/shared/shared.module';
import { SolicitanteComponent } from './solicitante.component';
import { SolicitanteDetailComponent } from './solicitante-detail.component';
import { SolicitanteUpdateComponent } from './solicitante-update.component';
import { SolicitanteDeleteDialogComponent } from './solicitante-delete-dialog.component';
import { solicitanteRoute } from './solicitante.route';

@NgModule({
  imports: [ViserasExtremaduraSurSharedModule, RouterModule.forChild(solicitanteRoute)],
  declarations: [SolicitanteComponent, SolicitanteDetailComponent, SolicitanteUpdateComponent, SolicitanteDeleteDialogComponent],
  entryComponents: [SolicitanteDeleteDialogComponent]
})
export class ViserasExtremaduraSurSolicitanteModule {}
