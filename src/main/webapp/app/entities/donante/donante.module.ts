import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViserasExtremaduraSurSharedModule } from 'app/shared/shared.module';
import { DonanteComponent } from './donante.component';
import { DonanteDetailComponent } from './donante-detail.component';
import { DonanteUpdateComponent } from './donante-update.component';
import { DonanteDeleteDialogComponent } from './donante-delete-dialog.component';
import { donanteRoute } from './donante.route';

@NgModule({
  imports: [ViserasExtremaduraSurSharedModule, RouterModule.forChild(donanteRoute)],
  declarations: [DonanteComponent, DonanteDetailComponent, DonanteUpdateComponent, DonanteDeleteDialogComponent],
  entryComponents: [DonanteDeleteDialogComponent]
})
export class ViserasExtremaduraSurDonanteModule {}
