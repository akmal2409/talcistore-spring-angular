import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {
  faHeart,
  faSearch,
  faShoppingCart,
  faSignInAlt,
  faUser,
  faUserPlus,
} from '@fortawesome/free-solid-svg-icons';

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

  isLoggedIn: boolean = false;

  @Output() sidebarToggle = new EventEmitter<any>();
  constructor() {}

  ngOnInit(): void {}

  onToggleSidebar(): void {
    this.sidebarToggle.emit(null);
  }
}
