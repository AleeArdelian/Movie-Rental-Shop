import { Component, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import {MovieService} from '../shared/movie.service';

@Component({
  selector: 'app-movie-new',
  templateUrl: './movie-new.component.html',
  styleUrls: ['./movie-new.component.css']
})
export class MovieNewComponent implements OnInit {

  constructor(private movieService: MovieService,
              private location: Location) { }

  ngOnInit() {
  }

  save(movieName, yearOfRelease, director) {
    console.log('save button pressed', movieName, yearOfRelease, director);
    this.movieService.save(movieName, yearOfRelease, director)
      .subscribe(_ => {
          console.debug('movie saved');
          this.location.back();
        },
        err => console.error('error saving movie', err));
  }
}
