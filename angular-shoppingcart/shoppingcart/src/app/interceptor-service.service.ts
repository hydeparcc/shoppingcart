import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpRequest,
  HttpHandler,
} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class InterceptorServiceService implements HttpInterceptor {
  constructor() {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const request = req.clone({
      headers: req.headers.set('X-Requested-With', 'XMLHttpRequest'),
    });
    return next.handle(request);
  }
}
