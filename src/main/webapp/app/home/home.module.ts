import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ViserasExtremaduraSurSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [ViserasExtremaduraSurSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent]
})
export class ViserasExtremaduraSurHomeModule {}
