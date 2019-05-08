import { Component, OnInit } from '@angular/core';
import {Client} from '../shared/client.model';
import {ClientService} from '../shared/client.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  clients: Array<Client>;
  selectedClient: Client;

  constructor(private clientService: ClientService, private router: Router) { }

  ngOnInit() {
    this.getAllClients();
  }
  getAllClients() {
    this.clientService.getClients()
      .subscribe(clients => this.clients = clients);
  }

  onSelect(client: Client): void {
    this.selectedClient = client;
  }

  goToDetail(): void {
    this.router.navigate(['/client/detail', this.selectedClient.id]);
  }

  remove(): void {
    this.clientService.delete(this.selectedClient.id).subscribe();
  }


}
