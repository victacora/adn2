package co.com.adnII.application.service.query;

import co.com.adnII.domain.entities.Company;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface ICompanyQueryService {

    CompletionStage<Stream<Company>> listCompanies();

}
