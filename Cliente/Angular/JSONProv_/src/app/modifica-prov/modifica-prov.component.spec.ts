import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModificaProvComponent } from './modifica-prov.component';

describe('ModificaProvComponent', () => {
  let component: ModificaProvComponent;
  let fixture: ComponentFixture<ModificaProvComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModificaProvComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ModificaProvComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
