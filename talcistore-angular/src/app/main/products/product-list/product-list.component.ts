import { ViewportScroller } from '@angular/common';
import {
  AfterViewInit,
  Component,
  ElementRef,
  Input,
  OnInit,
  ViewChild,
} from '@angular/core';
import { PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { faShippingFast } from '@fortawesome/free-solid-svg-icons';
import { first } from 'rxjs/operators';
import { PageResponseModel } from 'src/app/models/page-response.model';
import { ProductModel } from 'src/app/models/product.model';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit, AfterViewInit {
  @Input() previewMode: boolean;

  products: ProductModel[] = [];
  pageLength: number;
  pageSize: number = 10;
  pageIndex: number = 0;
  pageSizeOptions: number[] = [10, 20, 30, 50];

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private viewportScroller: ViewportScroller
  ) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: Params) => {
      this.pageIndex = params['page'] ? params['page'] : 0;
      this.pageSize = params['size'] ? params['size'] : 20;
      this.pageLength = params['length'] ? params['length'] : this.pageLength;
      this.fetchProducts();
    });
  }

  ngAfterViewInit(): void {
    this.route.fragment.pipe(first()).subscribe((fragment) => {
      this.viewportScroller.scrollToAnchor(fragment);
    });
  }

  fetchProducts(): void {
    if (!this.previewMode) {
      this.productService
        .fetchProducts(this.pageIndex, this.pageSize)
        .subscribe((page: PageResponseModel<ProductModel>) => {
          this.products = page.content;
          this.pageLength = page.totalItems;
          this.pageIndex = page.currentPage;
        });
    } else {
      this.productService
        .fetchTopRated(this.pageIndex, this.pageSize)
        .subscribe((page: PageResponseModel<ProductModel>) => {
          this.products = page.content;
          this.pageLength = page.totalItems;
          this.pageIndex = page.currentPage;
        });
    }
  }

  onNextPage(event: PageEvent): void {
    // this.pageIndex = event.pageIndex;
    // this.pageSize = event.pageSize;
    // this.fetchProducts();
    this.router.navigate(['/'], {
      queryParams: {
        page: event.pageIndex,
        size: event.pageSize,
        length: this.pageLength,
      },
      fragment: 'main-container',
    });
  }
}
