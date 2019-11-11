import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NavComponent } from './components/nav/nav.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { LoginComponent } from './components/login/login.component';
import { PokemonComponent } from './components/pokemon/pokemon.component';
import { PokemonCardComponent } from './components/pokemon-card/pokemon-card.component';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { MeComponent } from './components/me/me.component';
import { ViewRequestsComponent } from './components/view-requests/view-requests.component';
import { SubmitRequestComponent } from './components/submit-request/submit-request.component';
import { ViewAllRequestsComponent } from './components/view-all-requests/view-all-requests.component';
import { ViewRequestsByUserComponent } from './components/view-requests-by-user/view-requests-by-user.component';
import { ViewRequestsByStatusComponent } from './components/view-requests-by-status/view-requests-by-status.component';
// tslint:disable-next-line: max-line-length
import { ViewRequestsByUserAndStatusComponent } from './components/view-requests-by-user-and-status/view-requests-by-user-and-status.component';

@NgModule({
  declarations: [
    AppComponent,
    NavComponent,
    NotFoundComponent,
    LoginComponent,
    PokemonComponent,
    PokemonCardComponent,
    MeComponent,
    ViewRequestsComponent,
    SubmitRequestComponent,
    ViewAllRequestsComponent,
    ViewRequestsByUserComponent,
    ViewRequestsByStatusComponent,
    ViewRequestsByUserAndStatusComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
