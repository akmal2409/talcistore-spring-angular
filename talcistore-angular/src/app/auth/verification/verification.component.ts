import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import {
  faCheckCircle,
  faExclamationCircle,
} from '@fortawesome/free-solid-svg-icons';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.scss'],
})
export class VerificationComponent implements OnInit {
  token: string;
  success: boolean;
  showSpinner: boolean;
  checkIcon = faCheckCircle;
  exlamationIcon = faExclamationCircle;

  constructor(
    private route: ActivatedRoute,
    private authService: AuthService,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    this.showSpinner = true;
    this.route.params.subscribe((params: Params) => {
      this.token = params['token'];
      this.sendToken();
    });
  }

  sendToken(): void {
    this.authService.verifyAccount(this.token).subscribe(
      (response) => {
        console.log(response);
        this.showSpinner = false;
        this.success = true;
      },
      (error) => {
        console.log(error);
        this.showSpinner = false;
        this.success = false;
        this.toastr.error(error.message);
      }
    );
  }
}
