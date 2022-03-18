import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoObjComponent } from './listado-obj.component';

describe('ListadoObjComponent', () => {
  let component: ListadoObjComponent;
  let fixture: ComponentFixture<ListadoObjComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListadoObjComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoObjComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
