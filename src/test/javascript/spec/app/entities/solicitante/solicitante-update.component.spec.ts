import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { ViserasExtremaduraSurTestModule } from '../../../test.module';
import { SolicitanteUpdateComponent } from 'app/entities/solicitante/solicitante-update.component';
import { SolicitanteService } from 'app/entities/solicitante/solicitante.service';
import { Solicitante } from 'app/shared/model/solicitante.model';

describe('Component Tests', () => {
  describe('Solicitante Management Update Component', () => {
    let comp: SolicitanteUpdateComponent;
    let fixture: ComponentFixture<SolicitanteUpdateComponent>;
    let service: SolicitanteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [ViserasExtremaduraSurTestModule],
        declarations: [SolicitanteUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(SolicitanteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SolicitanteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SolicitanteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Solicitante(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Solicitante();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
