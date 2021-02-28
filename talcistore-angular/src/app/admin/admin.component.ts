import { faTimes } from '@fortawesome/free-solid-svg-icons';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { SidenavService } from '../services/sidenav.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})
export class AdminComponent implements OnInit, AfterViewInit {
  @ViewChild('sidenav') sidenav: MatSidenav;
  closeIcon = faTimes;

  constructor() {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {}

  close() {
    this.sidenav.close();
  }

  open() {
    this.sidenav.open();
  }

  toggle() {
    this.sidenav.toggle();
  }
}
