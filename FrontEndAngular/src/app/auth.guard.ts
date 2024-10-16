import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  Router,
  RouterStateSnapshot,
  UrlTree
} from '@angular/router';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";
import {AuthService} from "./services/auth.service";

export class AuthGuard implements CanActivate  {
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.CheckLogin() ;
  }
  constructor(
    private router:Router,
   private authservice: AuthService
  ) {

  }
  private  CheckLogin() : boolean {

   const islogedin= this.authservice.isAuthenticated()
    if(!islogedin){
      this.router.navigate(['/login']);
      return false;
    }

      return true;

  }

}
