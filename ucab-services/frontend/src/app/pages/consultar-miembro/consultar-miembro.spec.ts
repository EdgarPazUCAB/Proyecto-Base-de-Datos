import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarMiembro } from './consultar-miembro';

describe('ConsultarMiembro', () => {
  let component: ConsultarMiembro;
  let fixture: ComponentFixture<ConsultarMiembro>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultarMiembro],
    }).compileComponents();

    fixture = TestBed.createComponent(ConsultarMiembro);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
