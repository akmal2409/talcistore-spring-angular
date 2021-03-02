import { CapitalizePipe } from './capitalize.pipe';
import { MaterialModule } from './../material.module';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [CapitalizePipe],
  imports: [CommonModule, FontAwesomeModule, MaterialModule, FormsModule],
  exports: [
    CommonModule,
    FontAwesomeModule,
    MaterialModule,
    FormsModule,
    CapitalizePipe,
  ],
})
export class SharedModule {}
