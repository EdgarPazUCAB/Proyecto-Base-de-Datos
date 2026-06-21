import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnadirVinculoFamiliar } from './anadir-vinculo-familiar';

describe('AnadirVinculoFamiliar', () => {
  let component: AnadirVinculoFamiliar;
  let fixture: ComponentFixture<AnadirVinculoFamiliar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AnadirVinculoFamiliar],
    }).compileComponents();

    fixture = TestBed.createComponent(AnadirVinculoFamiliar);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
