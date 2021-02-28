import { HeaderComponent } from './../main/header/header.component';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from './shared.module';
import { NgModule } from '@angular/core';

@NgModule({
  declarations: [HeaderComponent],
  imports: [SharedModule, ReactiveFormsModule, RouterModule],
  exports: [HeaderComponent],
})
export class UIModule {}
