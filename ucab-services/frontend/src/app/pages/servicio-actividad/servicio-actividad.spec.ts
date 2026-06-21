import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicioActividad } from './servicio-actividad';

describe('ServicioActividad', () => {
  let component: ServicioActividad;
  let fixture: ComponentFixture<ServicioActividad>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicioActividad],
    }).compileComponents();

    fixture = TestBed.createComponent(ServicioActividad);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
