import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'solicitante',
        loadChildren: () => import('./solicitante/solicitante.module').then(m => m.ViserasExtremaduraSurSolicitanteModule)
      },
      {
        path: 'donante',
        loadChildren: () => import('./donante/donante.module').then(m => m.ViserasExtremaduraSurDonanteModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class ViserasExtremaduraSurEntityModule {}
