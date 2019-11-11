import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { MeComponent } from './components/me/me.component';
import { ViewRequestsComponent } from './components/view-requests/view-requests.component';
import { SubmitRequestComponent } from './components/submit-request/submit-request.component';
import { ViewAllRequestsComponent } from './components/view-all-requests/view-all-requests.component';
import { ViewRequestsByUserComponent } from './components/view-requests-by-user/view-requests-by-user.component';
import { ViewRequestsByStatusComponent } from './components/view-requests-by-status/view-requests-by-status.component';
// tslint:disable-next-line: max-line-length
import { ViewRequestsByUserAndStatusComponent } from './components/view-requests-by-user-and-status/view-requests-by-user-and-status.component';



const routes: Routes = [
  {
    path: 'me',
    component: MeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'viewRequests',
    component: ViewRequestsComponent
  },
  {
    path: 'submitRequests',
    component: SubmitRequestComponent
  },
  {
    path: 'viewAllRequests',
    component: ViewAllRequestsComponent
  },
  {
    path: 'viewRequestsByUser',
    component: ViewRequestsByUserComponent
  },
  {
    path: 'viewRequestsByStatus',
    component: ViewRequestsByStatusComponent
  },
  {
    path: 'viewRequestsByUserAndStatus',
    component: ViewRequestsByUserAndStatusComponent
  },
  {
    path: '**',
    pathMatch: 'full',
    component: NotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
