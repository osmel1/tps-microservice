import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {DecimalPipe, JsonPipe, NgForOf, NgIf} from "@angular/common";
import {json} from "stream/consumers";

@Component({
  selector: 'app-bill-details',
  standalone: true,
  imports: [
    NgForOf,
    JsonPipe,
    NgIf,
    DecimalPipe
  ],
  templateUrl: './bill-details.component.html',
  styleUrl: './bill-details.component.css'
})
export class BillDetailsComponent implements OnInit{
  billId:any;
  billDetails:any;
  constructor(private route : ActivatedRoute,private http:HttpClient) {

  }
  ngOnInit(): void {
    this.billId = this.route.snapshot.params['billId'];
    this.http.get("http://localhost:8088/BILLING-SERVICE/fullBill/"+this.billId).subscribe(
      {
        next:(data) => {
          this.billDetails = data;
        },
        error:(err) => {
          console.log(err)
        }
      }
    )
  }
}
