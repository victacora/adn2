import { DialogComponent } from '../dialog/dialog.component';
import { Component, Output, EventEmitter, ViewChild, AfterViewInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Company } from 'src/app/shared/model/company';
import { CompaniesService } from 'src/app/shared/service/companies.service';


@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent {

  private company: Company = new Company();

  @ViewChild('createcompanyfrm') createcompanyfrm;

  @Output() public reload = new EventEmitter();

  constructor(private dialog: MatDialog, private companiesService: CompaniesService) { }

  createCompany() {
    this.companiesService.createCompanies(this.company).subscribe(() => {
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Informacion',
          message: 'Empresa registrada correctamente.'
        }
      });
      this.reload.emit();
      this.company = new Company();
      this.createcompanyfrm.reset();
    }, (error) => {
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Error',
          message: error.message
        }
      });
    });
  }
}
