import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ClientsComponent } from './clients/clients.component';
import { MoviesComponent } from './movies/movies.component';
import {AppRoutingModule} from './app-routing.module';
import { ClientListComponent } from './clients/client-list/client-list.component';
import {ClientService} from './clients/shared/client.service';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import { MovieListComponent } from './movies/movie-list/movie-list.component';
import {MovieService} from './movies/shared/movie.service';
import { ClientNewComponent } from './clients/client-new/client-new.component';
import { ClientDetailComponent } from './clients/client-detail/client-detail.component';
import { MovieNewComponent } from './movies/movie-new/movie-new.component';
import { MovieDetailComponent } from './movies/movie-detail/movie-detail.component';
import { RentalsComponent } from './rentals/rentals.component';
import { RentalListComponent } from './rentals/rental-list/rental-list.component';
import { RentalNewComponent } from './rentals/rental-new/rental-new.component';
import {RentalService} from './rentals/shared/rental.service';
import { RentalDetailComponent } from './rentals/rental-detail/rental-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientsComponent,
    MoviesComponent,
    ClientListComponent,
    MovieListComponent,
    ClientNewComponent,
    ClientDetailComponent,
    MovieNewComponent,
    MovieDetailComponent,
    RentalsComponent,
    RentalListComponent,
    RentalNewComponent,
    RentalDetailComponent,
    // The classes that belong to this module and are related to views.
    // There are three classes in Angular that can contain views: components, directives and pipes.
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
    // Modules whose classes are needed by the components of this module.
    // Only @NgModule references go in the imports array.
  ],
  providers: [ClientService, MovieService, RentalService]
  , //  Services present in one of the modules which are going to be used in the other modules or components
  bootstrap: [AppComponent]
  // The root component which is the main view of the application.
  // Only the root module has this property and it indicates the component that’s gonna be bootstrapped.
})
export class AppModule { }
