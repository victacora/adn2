package co.com.adnII.application.service.command;

import co.com.adnII.domain.entities.Company;

import java.util.concurrent.CompletionStage;


public interface ICompanyCommandService {

   CompletionStage<Void> createCompany(Company company);

}
