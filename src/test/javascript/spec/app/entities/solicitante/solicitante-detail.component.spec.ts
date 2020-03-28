import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ViserasExtremaduraSurTestModule } from '../../../test.module';
import { SolicitanteDetailComponent } from 'app/entities/solicitante/solicitante-detail.component';
import { Solicitante } from 'app/shared/model/solicitante.model';

describe('Component Tests', () => {
  describe('Solicitante Management Detail Component', () => {
    let comp: SolicitanteDetailComponent;
    let fixture: ComponentFixture<SolicitanteDetailComponent>;
    const route = ({ data: of({ solicitante: new Solicitante(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ViserasExtremaduraSurTestModule],
        declarations: [SolicitanteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(SolicitanteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SolicitanteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load solicitante on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.solicitante).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
