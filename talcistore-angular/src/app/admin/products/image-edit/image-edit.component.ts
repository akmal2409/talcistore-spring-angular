import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-image-edit',
  templateUrl: './image-edit.component.html',
  styleUrls: ['./image-edit.component.scss'],
})
export class ImageEditComponent implements OnInit {
  editMode: boolean = false;
  productId: number;

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: Params) => {
      this.editMode = params['editMode'] === 'true';

      if (this.editMode) {
        this.route.params.subscribe((params: Params) => {
          this.productId = params['productId'];
        });
      }
    });
  }
}
