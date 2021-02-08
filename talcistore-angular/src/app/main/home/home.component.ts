import {
  AfterViewInit,
  Component,
  OnDestroy,
  OnInit,
  ViewChild,
} from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { CategoryModel } from 'src/app/models/category.model';
import { CategoryService } from 'src/app/services/category.service';
import { SidenavService } from 'src/app/services/sidenav.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy, AfterViewInit {
  @ViewChild('sidenav') sidenav: MatSidenav;
  categories: CategoryModel[] = [];
  loggedIn: boolean;
  loginStatusSub: Subscription;
  closeIcon = faTimes;

  constructor(
    private categoryService: CategoryService,
    private authService: AuthService,
    private router: Router,
    private sidenavService: SidenavService
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

  ngAfterViewInit(): void {
    this.sidenavService.setSidenav(this.sidenav);
  }

  close() {
    this.sidenavService.close();
  }

  open() {
    this.sidenavService.open();
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
