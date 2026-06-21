import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FolioConsumo } from './folio-consumo';

describe('FolioConsumo', () => {
  let component: FolioConsumo;
  let fixture: ComponentFixture<FolioConsumo>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FolioConsumo],
    }).compileComponents();

    fixture = TestBed.createComponent(FolioConsumo);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
