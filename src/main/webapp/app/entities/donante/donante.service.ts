import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDonante } from 'app/shared/model/donante.model';

type EntityResponseType = HttpResponse<IDonante>;
type EntityArrayResponseType = HttpResponse<IDonante[]>;

@Injectable({ providedIn: 'root' })
export class DonanteService {
  public resourceUrl = SERVER_API_URL + 'api/donantes';

  constructor(protected http: HttpClient) {}

  create(donante: IDonante): Observable<EntityResponseType> {
    return this.http.post<IDonante>(this.resourceUrl, donante, { observe: 'response' });
  }

  update(donante: IDonante): Observable<EntityResponseType> {
    return this.http.put<IDonante>(this.resourceUrl, donante, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDonante>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDonante[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
