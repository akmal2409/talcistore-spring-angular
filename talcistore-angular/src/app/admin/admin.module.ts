import { AdminComponent } from './admin.component';
import { SharedModule } from './../shared/shared.module';
import { NgModule } from '@angular/core';
import { AdminRoutingModule } from './admin-routing.module';
import { ProductEditComponent } from './products/product-edit/product-edit.component';
import { ImageEditComponent } from './products/image-edit/image-edit.component';

@NgModule({
  declarations: [AdminComponent, ProductEditComponent, ImageEditComponent],
  imports: [SharedModule, AdminRoutingModule],
})
export class AdminModule {}
