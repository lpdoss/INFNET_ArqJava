import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AppService {
  private baseUrl: string = 'http://localhost:8080/'

  constructor(private http: HttpClient) { }

  public postLogin(object: any) {
    return this.http.post(`${this.baseUrl}login`, object)
  }

  public getOrders() {
    return this.http.get(`${this.baseUrl}ordem`)
  }

  public getMyOrders(email: any = 'lsantos@clienteartico.com') {
    return this.http.get(`${this.baseUrl}ordem/${email}`)
  }

  public getProducts() {
    return this.http.get(`${this.baseUrl}produto`)
  }
}
