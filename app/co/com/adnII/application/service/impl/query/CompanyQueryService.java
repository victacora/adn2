package co.com.adnII.application.service.impl.query;

import co.com.adnII.application.service.query.ICompanyQueryService;
import co.com.adnII.domain.entities.Company;
import co.com.adnII.domain.repository.CompanyRepository;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public class CompanyQueryService implements ICompanyQueryService {

    private CompanyRepository companyRepository;

    @Inject
    public CompanyQueryService(CompanyRepository companyRepository){
        this.companyRepository=companyRepository;
    }

    @Override
    public CompletionStage<Stream<Company>> listCompanies() {
        return companyRepository.list();
    }
}
