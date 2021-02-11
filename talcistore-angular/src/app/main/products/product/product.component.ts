import { Component, Input, OnInit } from '@angular/core';
import { faShippingFast } from '@fortawesome/free-solid-svg-icons';
import { ProductImageModel } from 'src/app/models/product-image.model';
import { ProductModel } from 'src/app/models/product.model';
import { ImageService } from '../image.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss'],
})
export class ProductComponent implements OnInit {
  @Input() product: ProductModel;
  shippingIcon = faShippingFast;
  images: ProductImageModel[] = [];

  constructor(private imageService: ImageService) {}

  ngOnInit(): void {
    this.imageService.fetchImages(this.product.id).subscribe((images) => {
      this.images = images;
    });
  }

  getShipping(): string {
    if (this.product.shippingCost === null || this.product.shippingCost < 1) {
      return 'Free Shipping';
    } else {
      return 'Shipping ' + this.product.shippingCost.toFixed(2);
    }
  }

  getShippingClass(): string {
    if (this.product.shippingCost === null || this.product.shippingCost < 1) {
      return 'text-indigo-600';
    } else {
      return 'text-red-600';
    }
  }

  getImage(): string {
    if (this.images === null || this.images.length < 1) {
      return 'https://www.uejecutivos.cl/img/nophoto.png';
    } else {
      return this.images[0].url;
    }
  }

  getRating(): number {
    if (this.product.rating !== null) {
      return Math.round(this.product.rating);
    } else {
      return 0;
    }
  }
}
