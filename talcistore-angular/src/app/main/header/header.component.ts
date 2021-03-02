import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import {
  faHeart,
  faSearch,
  faShoppingCart,
  faSignInAlt,
  faUser,
  faUserPlus,
} from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { debounceTime, filter, switchMap } from 'rxjs/operators';
import { AuthService } from 'src/app/auth/auth.service';
import { SidenavService } from 'src/app/services/sidenav.service';
import { ProductService } from '../products/product.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  searchIcon = faSearch;
  userIcon = faUser;
  heartIcon = faHeart;
  shoppingCartIcon = faShoppingCart;
  signInIcon = faSignInAlt;
  signUpIcon = faUserPlus;
  username: string;
  searchForm: FormGroup;
  searchResult: string[] = [];
  isLoading = false;

  isLoggedIn: boolean;
  loginStatusSub: Subscription;

  constructor(
    private authService: AuthService,
    private sidenavService: SidenavService,
    private productService: ProductService
  ) {
    this.searchForm = new FormGroup({
      text: new FormControl(''),
    });
  }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUsername();
    this.loginStatusSub = this.authService.loggedIn.subscribe((status) => {
      this.isLoggedIn = status;
      this.username = this.authService.getUsername();
    });
    this.onChanges();
  }

  onChanges(): void {
    this.searchForm
      .get('text')
      .valueChanges.pipe(
        filter((val) => val.trim().length > 0),
        debounceTime(500),
        switchMap((value: string) => {
          return this.productService.searchValue(value);
        })
      )
      .subscribe((result: { options: string[] }) => {
        // console.log(result.options);
        this.searchResult = result.options;
      });
  }

  displayFn(text: string): string {
    return text;
  }

  onToggleSidebar(): void {
    this.sidenavService.open();
  }

  ngOnDestroy(): void {
    this.loginStatusSub.unsubscribe();
  }
}
