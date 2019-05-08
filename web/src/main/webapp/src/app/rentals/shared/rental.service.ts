import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Rental} from './rental.model';
import {Movie} from '../../movies/shared/movie.model';
import {map} from 'rxjs/operators';

@Injectable()
export class RentalService {
  private rentalsUrl = 'http://localhost:8080/movie-shop/api/rentals';

  constructor(private httpClient: HttpClient) { }

  getRentals(): Observable<Rental[]> {
    return this.httpClient
      .get<Array<Rental>>(this.rentalsUrl);
  }

  getRental(id: number): Observable<Rental> {
    return this.getRentals().pipe(map(rentals => rentals.find(
      rental => rental.id === id)));
  }

  save(clientId: number, movieId: number): Observable<Rental> {
    const rental = {clientId, movieId};
    return this.httpClient
      .post<Rental>(this.rentalsUrl, rental);
  }

  delete(id: number): Observable<{}> {
    const url = `${this.rentalsUrl}/${id}`;
    return this.httpClient.delete(url);
  }
}
