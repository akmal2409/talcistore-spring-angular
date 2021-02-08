import { Component, OnInit } from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { faShippingFast } from '@fortawesome/free-solid-svg-icons';
import { PageResponseModel } from 'src/app/models/page-response.model';
import { ProductModel } from 'src/app/models/product.model';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  shippingIcon = faShippingFast;
  products: ProductModel[] = [];
  pageLength: number;
  pageSize: number = 10;
  pageIndex: number = 0;
  pageSizeOptions: number[] = [10, 20, 30, 50];

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.fetchProducts();
  }

  fetchProducts(): void {
    this.productService
      .fetchProducts(this.pageIndex, this.pageSize)
      .subscribe((page: PageResponseModel<ProductModel>) => {
        this.products = page.content;
        this.pageLength = page.totalItems;
        this.pageIndex = page.currentPage;
      });
  }

  getShipping(product: ProductModel): string {
    if (product.shippingCost === null || product.shippingCost < 1) {
      return 'Free Shipping';
    } else {
      return 'Shipping ' + product.shippingCost.toFixed(2);
    }
  }

  getShippingClass(product: ProductModel): string {
    if (product.shippingCost === null || product.shippingCost < 1) {
      return 'text-indigo-600';
    } else {
      return 'text-red-600';
    }
  }

  onNextPage(event: PageEvent): void {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.fetchProducts();
  }

  getImage(product: ProductModel): string {
    if (product.imgUrl === null || product.imgUrl.length < 1) {
      return 'https://748073e22e8db794416a-cc51ef6b37841580002827d4d94d19b6.ssl.cf3.rackcdn.com/not-found.png';
    } else {
      return product.imgUrl;
    }
  }

  getRating(product: ProductModel): number {
    if (product.rating !== null) {
      return Math.round(product.rating);
    } else {
      return 0;
    }
  }
}
