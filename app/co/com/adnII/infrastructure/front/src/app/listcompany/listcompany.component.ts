import { Company } from '../model/company';
import { Component, ViewChild, AfterViewInit } from '@angular/core';
import { MatPaginator, MatSort } from '@angular/material';
import { CompaniesService } from '../service/companies.service';

@Component({
  selector: 'app-listcompany',
  templateUrl: './listcompany.component.html',
  styleUrls: ['./listcompany.component.css']
})

export class ListCompanyComponent implements AfterViewInit {

  loading = true;
  companies: Company[] = [];

  columsList = ['id', 'nit', 'name', 'phone_number', 'email'];


  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private companiesService: CompaniesService) { }

  ngAfterViewInit() {
    this.reloadCompanies();
  }

  public reloadCompanies() {
      this.loading = true;
     this.companiesService.listCompanies().subscribe(data => this.companies = data);
  }

}
