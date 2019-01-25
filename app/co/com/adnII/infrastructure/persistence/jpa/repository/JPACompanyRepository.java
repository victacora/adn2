package co.com.adnII.infrastructure.persistence.jpa.repository;

import co.com.adnII.domain.entities.Company;
import co.com.adnII.domain.repository.CompanyRepository;
import co.com.adnII.infrastructure.persistence.jpa.context.CompanyExecutionContext;
import co.com.adnII.infrastructure.persistence.jpa.entities.CompanyEntity;
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
        TypedQuery<CompanyEntity> query = em.createQuery("SELECT c FROM CompanyEntity c",  CompanyEntity.class);
        return query.getResultList().stream().map(c->convertToCompany(c));
    }


    private CompanyEntity insert(EntityManager em, Company Company) {
        return em.merge(convertToCompanyEntity(Company));
    }

    private CompanyEntity convertToCompanyEntity(Company company){
        CompanyEntity companyEntityEntity =new CompanyEntity();
        companyEntityEntity.setNit(company.getNit());
        companyEntityEntity.setName(company.getName());
        companyEntityEntity.setEmail(company.getEmail());
        companyEntityEntity.setPhoneNumber(company.getPhoneNumber());
        return companyEntityEntity;
    }

    private Company convertToCompany(CompanyEntity company){
       Company companyEntity=new Company();
        companyEntity.setNit(company.getNit());
        companyEntity.setName(company.getName());
        companyEntity.setEmail(company.getEmail());
        companyEntity.setPhoneNumber(company.getPhoneNumber());
        return companyEntity;
    }
}
