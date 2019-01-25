import { DialogoComponent } from '../dialog/dialog.component';
import { Component, Output, EventEmitter, ViewChild,  AfterViewInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { CompaniesService } from '../service/companies.service';
import { Company } from '../model/company';

@Component({
  selector: 'app-createcompany',
  templateUrl: './createcompany.component.html',
  styleUrls: ['./createcompany.component.css']
})
export class CreateCompanyComponent implements AfterViewInit {

  private company: Company;

  @ViewChild('createcompanyfrm') createcompanyfrm;

  @Output() public reload = new EventEmitter();

  constructor(private dialog: MatDialog, private companiesService: CompaniesService) { }

 ngAfterViewInit() {
    this.company = new Company();
  }

  createCompany() {
    this.companiesService.createCompanies(this.company).subscribe(() => {
      this.dialog.open(DialogoComponent, {
        data: {
          title: 'Informacion',
          message: 'Entrada registrada correctamente.'
        }
      });
      this.reload.emit();
      this.company = new Company();
    }, (error) => {
      this.dialog.open(DialogoComponent, {
        data: {
          title: 'Error',
          message: error.error.message
        }
      });
    });
  }
}
