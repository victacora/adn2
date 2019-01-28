import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Company } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompaniesService {

  API_URL = '/api/v1/companies';

  constructor(private httpClient: HttpClient) { }

  listCompanies(): Observable<Array<Company>> {
    return this.httpClient.get<Array<Company>>
      (this.API_URL);
  }

  createCompanies(company: Company): Observable<any> {
    return this.httpClient.post(this.API_URL, company);
  }

}
