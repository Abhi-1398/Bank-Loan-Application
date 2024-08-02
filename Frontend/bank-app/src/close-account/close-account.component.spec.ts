import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseAccountComponent } from './close-account.component';

describe('CloseAccountComponent', () => {
  let component: CloseAccountComponent;
  let fixture: ComponentFixture<CloseAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CloseAccountComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CloseAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
