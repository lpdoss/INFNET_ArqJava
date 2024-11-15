import { Component } from '@angular/core';
import { AppService } from '../app.service';
import { NgFor } from '@angular/common';

@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [NgFor],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.scss'
})
export class OrdersComponent {
  orders: any = []

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    this.getOrders()
  }

  public getOrders() {
    this.appService.getMyOrders().subscribe(orders => {
      this.orders = orders
      console.warn(this.orders)
    });
  }
}
