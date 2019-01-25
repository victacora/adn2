package co.com.adnII.infrastructure.persistence.jpa.repository;

import co.com.adnII.domain.entities.Company;
import co.com.adnII.domain.repository.CompanyRepository;
import co.com.adnII.infrastructure.persistence.jpa.context.CompanyExecutionContext;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPACompanyRepository implements CompanyRepository {

    private final JPAApi jpaApi;
    private final CompanyExecutionContext ec;

    @Inject
    public JPACompanyRepository(JPAApi api, CompanyExecutionContext ec) {
        this.jpaApi = api;
        this.ec = ec;
    }

    @Override
    public CompletionStage<Stream<Company>> list() {
        return supplyAsync(() -> wrap(em -> select(em)), ec);
    }

    @Override
    public CompletionStage<Void> create(Company company) {
        return runAsync(() -> wrap(em -> insert(em, company)), ec);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Stream<Company> select(EntityManager em) {
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c", Company.class);
        return query.getResultList().stream();
    }


    private Company insert(EntityManager em, Company Company) {
        return em.merge(Company);
    }

}
