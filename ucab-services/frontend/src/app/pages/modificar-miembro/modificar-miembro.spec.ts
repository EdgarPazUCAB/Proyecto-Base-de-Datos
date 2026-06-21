import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificarMiembro } from './modificar-miembro';

describe('ModificarMiembro', () => {
  let component: ModificarMiembro;
  let fixture: ComponentFixture<ModificarMiembro>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModificarMiembro],
    }).compileComponents();

    fixture = TestBed.createComponent(ModificarMiembro);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
