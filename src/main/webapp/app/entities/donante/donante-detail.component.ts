import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDonante } from 'app/shared/model/donante.model';

@Component({
  selector: 'jhi-donante-detail',
  templateUrl: './donante-detail.component.html'
})
export class DonanteDetailComponent implements OnInit {
  donante: IDonante | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ donante }) => (this.donante = donante));
  }

  previousState(): void {
    window.history.back();
  }
}
