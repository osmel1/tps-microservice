import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-bills',
  standalone: true,
  imports: [
    NgForOf
  ],
  templateUrl: './bills.component.html',
  styleUrl: './bills.component.css'
})
export class BillsComponent implements OnInit{
  customerId: any;
  bills: any;
constructor(private route :ActivatedRoute,private http:HttpClient,private router:Router) {
}
  ngOnInit(): void {
    this.customerId = this.route.snapshot.params['id'];
    this.http.get("http://localhost:8088/BILLING-SERVICE/bills/search/byCustomer?customerId="+this.customerId+"&projection=fullBill").subscribe(
      {
        next:(data) => {
          this.bills = data;
        },
        error:(err) => {
          console.log(err)
        }
  }
    )
  }

  getBillDetails(id:any) {
    this.router.navigateByUrl("/bill-details/"+id);
  }
}
