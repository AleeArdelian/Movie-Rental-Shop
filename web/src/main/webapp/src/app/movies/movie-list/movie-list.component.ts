import { Component, OnInit } from '@angular/core';
import {Movie} from '../shared/movie.model';
import {Router} from '@angular/router';
import {MovieService} from '../shared/movie.service'
import {Location} from '@angular/common';

@Component({
  selector: 'app-movie-list',
  templateUrl: './movie-list.component.html',
  styleUrls: ['./movie-list.component.css']
})
export class MovieListComponent implements OnInit {
  movies: Array<Movie>;
  selectedMovie: Movie;

  constructor(private movieService: MovieService, private router: Router
               ) { }

  ngOnInit() {
    this.movieService.getMovies()
      .subscribe(movies => this.movies = movies);
  }
  onSelect(movie: Movie): void {
    this.selectedMovie = movie;
  }

  goToDetail(): void {
    this.router.navigate(['/movie/detail/', this.selectedMovie.id]);
  }

  remove(): void {
    this.movieService.delete(this.selectedMovie.id).subscribe();
    location.reload();
  }
}
