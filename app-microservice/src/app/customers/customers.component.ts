import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {NgForOf} from "@angular/common";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers: any;
  constructor(private http:HttpClient,private router:Router) {
  }
  ngOnInit(): void {
    this.http.get("http://localhost:8088/CUSTOMER-SERVICE/customers").subscribe(
      {
        next:(data) => {
          this.customers = data;
        },
        error:(err) => {
          console.log(err)
        }
      }
    )
  }

  getBills(id:any) {
    this.router.navigateByUrl("/bills/"+id);
  }
}
