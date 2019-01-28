import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatSort, MatDialog } from '@angular/material';
import { DialogComponent } from '../dialog/dialog.component';
import { Company } from 'src/app/shared/model/company';
import { CompaniesService } from 'src/app/shared/service/companies.service';

@Component({
  selector: 'app-list-company',
  templateUrl: './list-company.component.html',
  styleUrls: ['./list-company.component.css']
})

export class ListCompanyComponent implements AfterViewInit {

  loading = true;

  companies: Company[] = [];

  columnsToDisplay = ['id', 'nit', 'name', 'phone_number', 'email'];


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private dialog: MatDialog, private companiesService: CompaniesService) { }

  ngAfterViewInit() {
    this.reloadCompanies();
  }

  public reloadCompanies() {
    this.loading = true;
    this.companiesService.listCompanies().subscribe(data => {
      this.companies = data;
      this.loading = false;
    }, (error) => {
      this.loading = false;
      this.dialog.open(DialogComponent, {
        data: {
          title: 'Error',
          message: error.message
        }
      });
    });
  }

}
