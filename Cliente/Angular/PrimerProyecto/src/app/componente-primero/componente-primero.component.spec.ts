import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ComponentePrimeroComponent } from './componente-primero.component';

describe('ComponentePrimeroComponent', () => {
  let component: ComponentePrimeroComponent;
  let fixture: ComponentFixture<ComponentePrimeroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ComponentePrimeroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ComponentePrimeroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
