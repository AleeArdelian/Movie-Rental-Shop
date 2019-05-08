import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

import {Movie} from './movie.model';
import {Client} from '../../clients/shared/client.model';
import {map} from 'rxjs/operators';

@Injectable()
export class MovieService {
  private moviesUrl = 'http://localhost:8080/movie-shop/api/movies';

  constructor(private httpClient: HttpClient) { }

  getMovies(): Observable<Movie[]> {
    return this.httpClient
      .get<Array<Movie>>(this.moviesUrl);
  }

  getMovie(id: number): Observable<Movie> {
    return this.getMovies().pipe(map(movies => movies.find(
      movie => movie.id === id)));
  }

  update(movie): Observable<Movie> {
    const url = `${this.moviesUrl}/${movie.id}`;
    return this.httpClient
      .put<Movie>(url, movie);
  }

  save(movieName: string, yearOfRelease: number, director: string): Observable<Movie> {
    const movie = {movieName, yearOfRelease, director};
    return this.httpClient
      .post<Movie>(this.moviesUrl, movie);
  }

  delete(id: number): Observable<{}> {
    const url = `${this.moviesUrl}/${id}`;
    return this.httpClient.delete(url);
  }
}
