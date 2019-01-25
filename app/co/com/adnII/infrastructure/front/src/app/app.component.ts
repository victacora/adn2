import { Component, ViewChild } from '@angular/core';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { ListCompanyComponent } from './components/list-company/list-company.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title = 'Companies';

  @ViewChild(CreateCompanyComponent) private createCompanyComp:
    CreateCompanyComponent;

  @ViewChild(ListCompanyComponent) private listCompaniesComp:
  ListCompanyComponent;

  public reloadCompanies() {
    this.listCompaniesComp.reloadCompanies();
  }


}
