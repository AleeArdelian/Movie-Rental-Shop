import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit() {
  }

  addMovie() {
    console.log('addMovie button pressed');
    this.router.navigate(['/movie-new']);
  }

}
