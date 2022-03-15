import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaProvinciasComponent } from './lista-provincias.component';

describe('ListaProvinciasComponent', () => {
  let component: ListaProvinciasComponent;
  let fixture: ComponentFixture<ListaProvinciasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaProvinciasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaProvinciasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
