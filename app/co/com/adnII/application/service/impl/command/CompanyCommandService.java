package co.com.adnII.application.service.impl.command;

import co.com.adnII.application.service.command.ICompanyCommandService;
import co.com.adnII.domain.entities.Company;
import co.com.adnII.domain.repository.CompanyRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class CompanyCommandService implements ICompanyCommandService {

    @Inject
    private CompanyRepository companyRepository;

    @Override
    public CompletionStage<Void> createCompany(Company company) {
       return companyRepository.create(company);
    }
}