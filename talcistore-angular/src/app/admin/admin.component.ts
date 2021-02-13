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

  constructor(private sidenavService: SidenavService) {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    this.sidenavService.setSidenav(this.sidenav);
  }

  close() {
    this.sidenavService.close();
  }

  open() {
    this.sidenavService.open();
  }
}
