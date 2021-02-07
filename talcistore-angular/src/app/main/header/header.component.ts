import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {
  faHeart,
  faSearch,
  faShoppingCart,
  faSignInAlt,
  faUser,
  faUserPlus,
} from '@fortawesome/free-solid-svg-icons';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent implements OnInit {
  searchIcon = faSearch;
  userIcon = faUser;
  heartIcon = faHeart;
  shoppingCartIcon = faShoppingCart;
  signInIcon = faSignInAlt;
  signUpIcon = faUserPlus;
  username: string;

  isLoggedIn: boolean;

  @Output() sidebarToggle = new EventEmitter<any>();
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.authService.loggedIn.subscribe((status) => {
      this.isLoggedIn = status;
      this.username = this.authService.getUsername();
    });
  }

  onToggleSidebar(): void {
    this.sidebarToggle.emit(null);
  }
}
