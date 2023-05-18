import {ErrorHandler, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { LoginModule } from './login/login.module';
import { JwtInterceptor } from './security/JwtInterceptor ';
import { AdminModule } from './principal/admin/admin.module';
import { GarcomModule } from './principal/garcom/garcom.module';
import { CozinhaModule } from './principal/cozinha/cozinha.module';
import { PrincipalModule } from './principal/principal.module';
import {ErrorHandlerService} from "./security/ErrorHandler";

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    LoginModule,
    AdminModule,
    GarcomModule,
    CozinhaModule,
    PrincipalModule
  ],
  providers: [
    AppComponent,
    JwtInterceptor,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    {provide: ErrorHandler, useClass: ErrorHandlerService}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
