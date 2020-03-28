export interface IDonante {
  id?: number;
  nombreEmpresaOParticular?: string;
  email?: string;
  telefono?: string;
  direccion?: string;
  localidad?: string;
  codigoPostal?: string;
  aportacion?: string;
  cuando?: string;
  consentimiento?: boolean;
}

export class Donante implements IDonante {
  constructor(
    public id?: number,
    public nombreEmpresaOParticular?: string,
    public email?: string,
    public telefono?: string,
    public direccion?: string,
    public localidad?: string,
    public codigoPostal?: string,
    public aportacion?: string,
    public cuando?: string,
    public consentimiento?: boolean
  ) {
    this.consentimiento = this.consentimiento || false;
  }
}
