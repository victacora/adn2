import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {LayoutModule} from '@angular/cdk/layout';
import {
  MatProgressSpinnerModule, MatCardModule, MatToolbarModule, MatFormFieldModule, MatInputModule, MatButtonModule,
  MatSidenavModule, MatIconModule, MatListModule, MatDialogModule, MatTableModule, MatPaginatorModule, MatSortModule
} from '@angular/material';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {CreateCompanyComponent} from './createcompany/createcompany.component';
import {DialogoComponent as DialogComponent} from './dialog/dialog.component';
import {HttpClientModule} from '@angular/common/http';
import { ListCompanyComponent } from './listCompany/listCompany.component';


@NgModule({
  declarations: [
    AppComponent,
    CreateCompanyComponent,
    ListCompanyComponent,
    DialogComponent
  ],
  entryComponents: [DialogComponent],
  imports: [
    MatProgressSpinnerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FlexLayoutModule,
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HttpClientModule
  ],
  exports: [
    MatProgressSpinnerModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    FlexLayoutModule,
    BrowserModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    FormsModule,
    ReactiveFormsModule,
    MatDialogModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
