import { Component, OnInit } from '@angular/core';
import {Rental} from '../shared/rental.model';
import {RentalService} from '../shared/rental.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-rentals-list',
  templateUrl: './rental-list.component.html',
  styleUrls: ['./rental-list.component.css']
})
export class RentalListComponent implements OnInit {
  rentals: Array<Rental>;
  selectedRental: Rental;

  constructor(private rentalService: RentalService, private router: Router,
              private location: Location) { }

  ngOnInit() {
    this.rentalService.getRentals().subscribe(rentals => this.rentals = rentals);
  }

  onSelect(rental: Rental): void {
    this.selectedRental = rental;
  }

  remove(): void {
    this.rentalService.delete(this.selectedRental.id).subscribe();
    location.reload();
  }

  goToDetail(): void {
    this.router.navigate(['/rental/detail/', this.selectedRental.id]);
  }
}
