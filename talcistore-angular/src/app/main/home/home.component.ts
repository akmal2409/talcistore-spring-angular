import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { CategoryModel } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/services/category.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  @ViewChild('sidenav') sidenav: MatSidenav;
  categories: CategoryModel[] = [];
  loggedIn: boolean;
  loginStatusSub: Subscription;

  constructor(
    private categoryService: CategoryService,
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.categoryService.fetchCategories().subscribe((categories) => {
      this.categories = categories;
    });
    this.loggedIn = this.authService.isLoggedIn();
    this.loginStatusSub = this.authService.loggedIn.subscribe((status) => {
      this.loggedIn = status;
    });
  }

  close() {
    this.sidenav.close();
  }

  onLogout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  onLogin(): void {
    this.router.navigate(['/login']);
  }

  onSignup(): void {
    this.router.navigate(['/sign-up']);
  }

  ngOnDestroy(): void {
    this.loginStatusSub.unsubscribe();
  }
}
