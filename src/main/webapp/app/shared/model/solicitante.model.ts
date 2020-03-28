export interface ISolicitante {
  id?: number;
  nombreCentro?: string;
  personaContacto?: string;
  telefono?: string;
  email?: string;
  localidad?: string;
  direccion?: string;
  codigoPostal?: string;
  aceptaNoHomologado?: boolean;
  necesidad?: number;
  horariosEntrega?: string;
  comentarios?: string;
  consentimiento?: boolean;
}

export class Solicitante implements ISolicitante {
  constructor(
    public id?: number,
    public nombreCentro?: string,
    public personaContacto?: string,
    public telefono?: string,
    public email?: string,
    public localidad?: string,
    public direccion?: string,
    public codigoPostal?: string,
    public aceptaNoHomologado?: boolean,
    public necesidad?: number,
    public horariosEntrega?: string,
    public comentarios?: string,
    public consentimiento?: boolean
  ) {
    this.aceptaNoHomologado = this.aceptaNoHomologado || false;
    this.consentimiento = this.consentimiento || false;
  }
}
