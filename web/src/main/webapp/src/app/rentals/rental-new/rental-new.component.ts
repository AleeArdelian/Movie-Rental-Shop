import { Component, OnInit } from '@angular/core';
import {RentalService} from '../shared/rental.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-rental-new',
  templateUrl: './rental-new.component.html',
  styleUrls: ['./rental-new.component.css']
})
export class RentalNewComponent implements OnInit {

  constructor(private rentalService: RentalService,
              private location: Location) { }

  ngOnInit() {
  }

  save(clientId, movieId) {
    console.log('save button pressed', clientId, movieId);
    this.rentalService.save(clientId, movieId)
      .subscribe(_ => {
          console.debug('rental saved');
          this.location.back();
        },
        err => console.error('error saving rental', err));
  }
}
