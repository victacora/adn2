import { Component, ViewChild } from '@angular/core';
import { CreateCompanyComponent } from './createCompany/createCompany.component';
import { ListCompanyComponent } from './listCompany/listCompany.component';

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
