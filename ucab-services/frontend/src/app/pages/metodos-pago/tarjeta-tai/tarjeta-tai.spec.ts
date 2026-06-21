import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TarjetaTai } from './tarjeta-tai';

describe('TarjetaTai', () => {
  let component: TarjetaTai;
  let fixture: ComponentFixture<TarjetaTai>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TarjetaTai],
    }).compileComponents();

    fixture = TestBed.createComponent(TarjetaTai);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
