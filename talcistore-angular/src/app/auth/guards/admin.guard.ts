import { Injectable } from '@angular/core';
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  CanActivateChild,
  Router,
} from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthService } from '../auth.service';
import { Roles } from '../roles.enum';

@Injectable({
  providedIn: 'root',
})
export class AdminGuard implements CanActivate, CanActivateChild {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return new Observable((sub) => {
      if (!this.authService.isLoggedIn()) {
        sub.next(false);
        this.router.navigate(['/login']);
      } else {
        return this.authService
          .getRoles()
          .pipe(
            map(
              (roles: { id: number; name: string; description: string }[]) => {
                return roles.map((role) => role.name);
              }
            )
          )
          .subscribe(
            (roles: string[]) => {
              if (roles.indexOf('ADMIN') !== -1) {
                sub.next(true);
              } else {
                sub.next(false);
                this.router.navigate(['/login']);
              }
            },
            (error) => {
              sub.next(false);
              this.router.navigate(['/login']);
            }
          );
      }
    });
  }

  canActivateChild(
    childRoute: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.canActivate(childRoute, state);
  }
}
