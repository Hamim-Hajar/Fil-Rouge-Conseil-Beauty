import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable } from "rxjs";




export class Interciptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let cloned = req;
    const token = localStorage.getItem('token') || '';

    if (token) {
      cloned = req.clone({setHeaders: {Authorization: `Bearer ${token}`}});
    }
    return next.handle(cloned);
  }


}
