import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss'],
})
export class LandingComponent implements OnInit {
  carouselImages: string[] = [
    '../../../../assets/images/talci-carousel/1.jpg',
    '../../../../assets/images/talci-carousel/2.jpg',
    '../../../../assets/images/talci-carousel/3.jpg',
    '../../../../assets/images/talci-carousel/4.jpg',
    '../../../../assets/images/talci-carousel/5.jpg',
    '../../../../assets/images/talci-carousel/6.jpg',
    '../../../../assets/images/talci-carousel/7.jpg',
  ];
  constructor() {}

  ngOnInit(): void {}
}
