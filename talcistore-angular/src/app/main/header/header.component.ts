import {
  Component,
  OnInit,
  Output,
  EventEmitter,
  OnDestroy,
} from '@angular/core';
import {
  faHeart,
  faSearch,
  faShoppingCart,
  faSignInAlt,
  faUser,
  faUserPlus,
} from '@fortawesome/free-solid-svg-icons';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';

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

  isLoggedIn: boolean;
  loginStatusSub: Subscription;

  @Output() sidebarToggle = new EventEmitter<any>();
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUsername();
    this.loginStatusSub = this.authService.loggedIn.subscribe((status) => {
      this.isLoggedIn = status;
      this.username = this.authService.getUsername();
    });
  }

  onToggleSidebar(): void {
    this.sidebarToggle.emit(null);
  }

  ngOnDestroy(): void {
    this.loginStatusSub.unsubscribe();
  }
}
