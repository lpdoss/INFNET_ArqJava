import { NgClass, NgFor } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { AppService } from '../app.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [NgFor, NgClass],
  templateUrl: './products.component.html',
  styleUrl: './products.component.scss'
})
export class ProductsComponent implements OnInit {
  products: any = []

  constructor(private appService: AppService) { }

  ngOnInit(): void {
    this.getProducts()
  }

  public getProducts() {
    this.appService.getProducts().subscribe(products => {
      this.products = products
      console.warn(this.products)
    });
  }
}
