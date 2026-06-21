import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PagoMovil } from './pago-movil';

describe('PagoMovil', () => {
  let component: PagoMovil;
  let fixture: ComponentFixture<PagoMovil>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PagoMovil],
    }).compileComponents();

    fixture = TestBed.createComponent(PagoMovil);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
