import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISolicitante, Solicitante } from 'app/shared/model/solicitante.model';
import { SolicitanteService } from './solicitante.service';
import { SolicitanteComponent } from './solicitante.component';
import { SolicitanteDetailComponent } from './solicitante-detail.component';
import { SolicitanteUpdateComponent } from './solicitante-update.component';

@Injectable({ providedIn: 'root' })
export class SolicitanteResolve implements Resolve<ISolicitante> {
  constructor(private service: SolicitanteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISolicitante> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((solicitante: HttpResponse<Solicitante>) => {
          if (solicitante.body) {
            return of(solicitante.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Solicitante());
  }
}

export const solicitanteRoute: Routes = [
  {
    path: '',
    component: SolicitanteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Solicitantes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: SolicitanteDetailComponent,
    resolve: {
      solicitante: SolicitanteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Solicitantes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: SolicitanteUpdateComponent,
    resolve: {
      solicitante: SolicitanteResolve
    },
    data: {
      authorities: [],
      pageTitle: 'Solicitantes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: SolicitanteUpdateComponent,
    resolve: {
      solicitante: SolicitanteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Solicitantes'
    },
    canActivate: [UserRouteAccessService]
  }
];
