package co.com.adnII.model.repository;

import co.com.adnII.model.context.CompanyExecutionContext;
import co.com.adnII.model.entities.Company;
import net.jodah.failsafe.CircuitBreaker;
import net.jodah.failsafe.Failsafe;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JPACompanyRepository implements CompanyRepository {

    private final JPAApi jpaApi;
    private final CompanyExecutionContext ec;
    private final CircuitBreaker circuitBreaker = new CircuitBreaker().withFailureThreshold(1).withSuccessThreshold(3);

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
    public CompletionStage<Company> create(Company company) {
        return supplyAsync(() -> wrap(em -> insert(em, company)), ec);
    }

    @Override
    public CompletionStage<Optional<Company>> find(Long id) {
        return supplyAsync(() -> wrap(em -> Failsafe.with(circuitBreaker).get(() -> lookup(em, id))), ec);
    }

    private <T> T wrap(Function<EntityManager, T> function) {
        return jpaApi.withTransaction(function);
    }

    private Optional<Company> lookup(EntityManager em, Long id) {
        return Optional.ofNullable(em.find(Company.class, id));
    }

    private Stream<Company> select(EntityManager em) {
        TypedQuery<Company> query = em.createQuery("SELECT c FROM Company c", Company.class);
        return query.getResultList().stream();
    }


    private Company insert(EntityManager em, Company Company) {
        return em.merge(Company);
    }

}
