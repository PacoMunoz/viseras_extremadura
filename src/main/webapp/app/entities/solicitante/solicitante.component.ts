import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISolicitante } from 'app/shared/model/solicitante.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { SolicitanteService } from './solicitante.service';
import { SolicitanteDeleteDialogComponent } from './solicitante-delete-dialog.component';

@Component({
  selector: 'jhi-solicitante',
  templateUrl: './solicitante.component.html'
})
export class SolicitanteComponent implements OnInit, OnDestroy {
  solicitantes: ISolicitante[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected solicitanteService: SolicitanteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.solicitantes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.solicitanteService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<ISolicitante[]>) => this.paginateSolicitantes(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.solicitantes = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSolicitantes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISolicitante): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSolicitantes(): void {
    this.eventSubscriber = this.eventManager.subscribe('solicitanteListModification', () => this.reset());
  }

  delete(solicitante: ISolicitante): void {
    const modalRef = this.modalService.open(SolicitanteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.solicitante = solicitante;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateSolicitantes(data: ISolicitante[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.solicitantes.push(data[i]);
      }
    }
  }
}
