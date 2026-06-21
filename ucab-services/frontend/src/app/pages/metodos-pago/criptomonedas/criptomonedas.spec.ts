import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Criptomonedas } from './criptomonedas';

describe('Criptomonedas', () => {
  let component: Criptomonedas;
  let fixture: ComponentFixture<Criptomonedas>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Criptomonedas],
    }).compileComponents();

    fixture = TestBed.createComponent(Criptomonedas);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
