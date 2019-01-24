package co.com.adnII.model.repository;

import co.com.adnII.model.entities.Company;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

public interface CompanyRepository {

    CompletionStage<Stream<Company>> list();

    CompletionStage<Company> create(Company company);

    CompletionStage<Optional<Company>> find(Long id);

}
