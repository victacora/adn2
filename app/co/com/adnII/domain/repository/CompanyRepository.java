package co.com.adnII.domain.repository;

import co.com.adnII.domain.entities.Company;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface CompanyRepository {

    CompletionStage<Stream<Company>> list();

    CompletionStage<Void> create(Company company);

}
