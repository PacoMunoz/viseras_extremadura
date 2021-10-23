import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDonante } from 'app/shared/model/donante.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { DonanteService } from './donante.service';
import { DonanteDeleteDialogComponent } from './donante-delete-dialog.component';

@Component({
  selector: 'jhi-donante',
  templateUrl: './donante.component.html'
})
export class DonanteComponent implements OnInit, OnDestroy {
  donantes: IDonante[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected donanteService: DonanteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.donantes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.donanteService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IDonante[]>) => this.paginateDonantes(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.donantes = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInDonantes();

    const query = {};
    query['key1'] = 'hola';
    query['key2'] = 'admios';

    console.log('*************************************************************************' + JSON.stringify(query));
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IDonante): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInDonantes(): void {
    this.eventSubscriber = this.eventManager.subscribe('donanteListModification', () => this.reset());
  }

  delete(donante: IDonante): void {
    const modalRef = this.modalService.open(DonanteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.donante = donante;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateDonantes(data: IDonante[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.donantes.push(data[i]);
      }
    }
  }
}
