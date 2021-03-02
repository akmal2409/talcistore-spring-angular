import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { UnsubscriptionError } from 'rxjs';

@Component({
  selector: 'app-product-edit',
  templateUrl: './product-edit.component.html',
  styleUrls: ['./product-edit.component.scss'],
})
export class ProductEditComponent implements OnInit {
  editMode: boolean = false;
  productId: number;
  productForm: FormGroup;
  positiveRegexp = /^[1-9]\d*$/;
  constructor(private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.route.queryParams.subscribe((params: Params) => {
      this.editMode = params['edit'] === 'true';

      if (this.editMode) {
        this.route.params.subscribe((params: Params) => {
          this.productId = params['id'];
        });
      }
    });

    this.initForm();
  }

  initForm(): void {
    if (this.editMode && this.productId !== null) {
      // Todo: populate the form
    }
    this.productForm = new FormGroup({
      productName: new FormControl('', [Validators.required]),
      description: new FormControl('', [
        Validators.required,
        Validators.minLength(20),
      ]),
      categoryId: new FormControl(0),
      countryOfOrigin: new FormControl('', [Validators.required]),
      leftInStock: new FormControl(1, [
        Validators.required,
        Validators.pattern(this.positiveRegexp),
      ]),
      pricePerUnit: new FormControl(1.0, [Validators.required]),
      producer: new FormControl('', [Validators.required]),
      shippingCost: new FormControl(0, [Validators.required]),
    });
  }
}
