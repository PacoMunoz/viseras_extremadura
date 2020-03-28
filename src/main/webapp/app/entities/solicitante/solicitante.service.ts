import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISolicitante } from 'app/shared/model/solicitante.model';

type EntityResponseType = HttpResponse<ISolicitante>;
type EntityArrayResponseType = HttpResponse<ISolicitante[]>;

@Injectable({ providedIn: 'root' })
export class SolicitanteService {
  public resourceUrl = SERVER_API_URL + 'api/solicitantes';

  constructor(protected http: HttpClient) {}

  create(solicitante: ISolicitante): Observable<EntityResponseType> {
    return this.http.post<ISolicitante>(this.resourceUrl, solicitante, { observe: 'response' });
  }

  update(solicitante: ISolicitante): Observable<EntityResponseType> {
    return this.http.put<ISolicitante>(this.resourceUrl, solicitante, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISolicitante>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISolicitante[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
