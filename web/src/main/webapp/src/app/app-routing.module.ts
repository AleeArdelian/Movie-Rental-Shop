import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientsComponent} from './clients/clients.component';
import {MoviesComponent} from './movies/movies.component';
import {ClientDetailComponent} from './clients/client-detail/client-detail.component';
import {ClientNewComponent} from './clients/client-new/client-new.component';
import {MovieNewComponent} from './movies/movie-new/movie-new.component';
import {MovieDetailComponent} from './movies/movie-detail/movie-detail.component';
import {RentalsComponent} from './rentals/rentals.component';
import {RentalNewComponent} from './rentals/rental-new/rental-new.component';
import {RentalDetailComponent} from './rentals/rental-detail/rental-detail.component';

// Routes tell the router which view to display when a user clicks a link or pastes a URL into the browser address bar.
const routes: Routes = [
  {path: 'clients', component: ClientsComponent}, // path: a string that matches the URL in the browser address bar.
  {path: 'movies', component: MoviesComponent}, // component: the component that the router should create when navigating to this route.
  {path: 'rentals', component: RentalsComponent},
  {path: 'client/detail/:id', component: ClientDetailComponent },
  {path: 'client-new', component: ClientNewComponent},
  {path: 'movie-new', component: MovieNewComponent},
  {path: 'movie/detail/:id', component: MovieDetailComponent},
  {path: 'rental-new', component: RentalNewComponent},
  {path: 'rental/detail/:id', component: RentalDetailComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
