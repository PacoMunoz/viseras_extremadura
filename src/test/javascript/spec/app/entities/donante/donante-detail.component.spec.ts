import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ViserasExtremaduraSurTestModule } from '../../../test.module';
import { DonanteDetailComponent } from 'app/entities/donante/donante-detail.component';
import { Donante } from 'app/shared/model/donante.model';

describe('Component Tests', () => {
  describe('Donante Management Detail Component', () => {
    let comp: DonanteDetailComponent;
    let fixture: ComponentFixture<DonanteDetailComponent>;
    const route = ({ data: of({ donante: new Donante(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ViserasExtremaduraSurTestModule],
        declarations: [DonanteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(DonanteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(DonanteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load donante on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.donante).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
