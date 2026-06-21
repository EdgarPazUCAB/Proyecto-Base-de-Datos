import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertaLaboralDetalle } from './oferta-laboral-detalle';

describe('OfertaLaboralDetalle', () => {
  let component: OfertaLaboralDetalle;
  let fixture: ComponentFixture<OfertaLaboralDetalle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertaLaboralDetalle],
    }).compileComponents();

    fixture = TestBed.createComponent(OfertaLaboralDetalle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
