import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDonante, Donante } from 'app/shared/model/donante.model';
import { DonanteService } from './donante.service';
import { DonanteComponent } from './donante.component';
import { DonanteDetailComponent } from './donante-detail.component';
import { DonanteUpdateComponent } from './donante-update.component';

@Injectable({ providedIn: 'root' })
export class DonanteResolve implements Resolve<IDonante> {
  constructor(private service: DonanteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDonante> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((donante: HttpResponse<Donante>) => {
          if (donante.body) {
            return of(donante.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Donante());
  }
}

export const donanteRoute: Routes = [
  {
    path: '',
    component: DonanteComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Donantes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: DonanteDetailComponent,
    resolve: {
      donante: DonanteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Donantes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: DonanteUpdateComponent,
    resolve: {
      donante: DonanteResolve
    },
    data: {
      authorities: [],
      pageTitle: 'Donantes'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: DonanteUpdateComponent,
    resolve: {
      donante: DonanteResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Donantes'
    },
    canActivate: [UserRouteAccessService]
  }
];
