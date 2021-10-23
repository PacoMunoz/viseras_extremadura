import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDonante } from 'app/shared/model/donante.model';
import { HttpResponse } from '@angular/common/http';
import { onTaskCompleted } from '@angular/compiler-cli/ngcc/src/execution/utils';

@Component({
  selector: 'jhi-donante-detail',
  templateUrl: './donante-detail.component.html'
})
export class DonanteDetailComponent implements OnInit {
  donante: IDonante | null = null;
  donantes: IDonante[];

  constructor(protected activatedRoute: ActivatedRoute) {
    this.donantes = [];
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ donante }) => (this.donante = donante));
  }

  previousState(): void {
    window.history.back();
  }
}
