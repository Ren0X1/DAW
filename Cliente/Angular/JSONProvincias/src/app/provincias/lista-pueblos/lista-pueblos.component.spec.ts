import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListaPueblosComponent } from './lista-pueblos.component';

describe('ListaPueblosComponent', () => {
  let component: ListaPueblosComponent;
  let fixture: ComponentFixture<ListaPueblosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListaPueblosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListaPueblosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
