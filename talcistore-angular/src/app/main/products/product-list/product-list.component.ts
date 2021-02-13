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
  @Input() categoryMode: boolean;
  categoryId: number;

  products: ProductModel[] = [];
  pageLength: number;
  pageSize: number = 10;
  pageIndex: number = 0;
  pageSizeOptions: number[] = [10, 20, 30, 50];

  filterOptions: { name: string; value: string }[] = [
    { name: 'None', value: null },
    { name: 'Price low-high', value: 'pricePerUnit:asc' },
    { name: 'Price high-low', value: 'rating:desc' },
    { name: 'Rating', value: 'rating:desc' },
    { name: 'Orders', value: 'orderCount:desc' },
    { name: 'Date added', value: 'addedOn:desc' },
    { name: 'Last updated', value: 'lastUpdated:desc' },
    { name: 'Shipping cost', value: 'shippingCost:asc' },
  ];

  seletectedFilter: string;

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
      this.seletectedFilter = params['filter'];
      this.fetchProducts();
    });
  }

  ngAfterViewInit(): void {
    this.route.fragment.pipe(first()).subscribe((fragment) => {
      this.viewportScroller.scrollToAnchor(fragment);
    });
  }

  fetchProducts(): void {
    if (this.categoryMode) {
      this.fetchByCategory();
    } else if (this.previewMode) {
      this.productService
        .fetchTopRated(this.pageIndex, this.pageSize)
        .subscribe((page: PageResponseModel<ProductModel>) => {
          this.products = page.content;
          this.pageLength = page.totalItems;
          this.pageIndex = page.currentPage;
        });
    }
  }

  fetchByCategory(): void {
    this.route.params.subscribe((params: Params) => {
      this.categoryId = +params['id'];

      this.productService
        .fetchProductsByCategory(
          this.pageIndex,
          this.pageSize,
          this.categoryId,
          this.seletectedFilter
        )
        .subscribe((page: PageResponseModel<ProductModel>) => {
          this.products = page.content;
          this.pageLength = page.totalItems;
          this.pageIndex = page.currentPage;
        });
    });
  }

  onNextPage(event: PageEvent): void {
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        page: event.pageIndex,
        size: event.pageSize,
        length: this.pageLength,
      },
      queryParamsHandling: 'merge',
      fragment: 'main-container',
    });
  }

  onSelectFilter(): void {
    let filterParam = null;
    if (this.seletectedFilter) {
      filterParam = { filter: this.seletectedFilter };
    }
    this.router.navigate([], {
      relativeTo: this.route,
      queryParams: {
        filter: this.seletectedFilter,
        size: this.route.snapshot.queryParams['size'],
      },
    });
  }
}
