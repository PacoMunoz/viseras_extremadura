import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { DonanteService } from 'app/entities/donante/donante.service';
import { IDonante, Donante } from 'app/shared/model/donante.model';

describe('Service Tests', () => {
  describe('Donante Service', () => {
    let injector: TestBed;
    let service: DonanteService;
    let httpMock: HttpTestingController;
    let elemDefault: IDonante;
    let expectedResult: IDonante | IDonante[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(DonanteService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Donante(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Donante', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Donante()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Donante', () => {
        const returnedFromService = Object.assign(
          {
            nombreEmpresaOParticular: 'BBBBBB',
            email: 'BBBBBB',
            telefono: 'BBBBBB',
            direccion: 'BBBBBB',
            localidad: 'BBBBBB',
            codigoPostal: 'BBBBBB',
            aportacion: 'BBBBBB',
            cuando: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Donante', () => {
        const returnedFromService = Object.assign(
          {
            nombreEmpresaOParticular: 'BBBBBB',
            email: 'BBBBBB',
            telefono: 'BBBBBB',
            direccion: 'BBBBBB',
            localidad: 'BBBBBB',
            codigoPostal: 'BBBBBB',
            aportacion: 'BBBBBB',
            cuando: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Donante', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
