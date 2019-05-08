import { Component, OnInit } from '@angular/core';
import {ClientService} from '../shared/client.service';
import {Location} from '@angular/common';


@Component({
  selector: 'app-client-new',
  templateUrl: './client-new.component.html',
  styleUrls: ['./client-new.component.css']
})
export class ClientNewComponent implements OnInit {

  constructor(private clientService: ClientService,
              private location: Location) { }

  ngOnInit() {
  }

  save(firstName, lastName, age) {
    console.log('save button pressed', firstName, lastName, age);
    this.clientService.save(firstName, lastName, age)
      .subscribe(_ => {
          console.debug('client saved');
          this.location.back();
        },
        err => console.error('error saving client', err));
  }
}
