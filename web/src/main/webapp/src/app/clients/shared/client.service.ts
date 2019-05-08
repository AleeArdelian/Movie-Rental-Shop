import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Client} from './client.model';
import {map} from 'rxjs/operators';

@Injectable()
export class ClientService {
  private clientsUrl = 'http://localhost:8080/movie-shop/api/clients';

  constructor(private httpClient: HttpClient) { }

  getClients(): Observable<Client[]> {
    return this.httpClient
      .get<Array<Client>>(this.clientsUrl);
  }

  getClient(id: number): Observable<Client> {
    return this.getClients().pipe(map(clients => clients.find(
      client => client.id === id)));
  }

  update(client): Observable<Client> {
    const url = `${this.clientsUrl}/${client.id}`;
    return this.httpClient
      .put<Client>(url, client);
  }

  save(firstName: string, lastName: string, age: number): Observable<Client> {
    const client = {firstName, lastName, age};
    return this.httpClient
      .post<Client>(this.clientsUrl, client);
  }

  delete(id: number): Observable<{}> {
    const url = `${this.clientsUrl}/${id}`;
    return this.httpClient.delete(url);
  }
}

