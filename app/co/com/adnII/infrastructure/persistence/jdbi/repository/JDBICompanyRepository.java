package co.com.adnII.infrastructure.persistence.jdbi.repository;

import co.com.adnII.domain.entities.Company;
import co.com.adnII.domain.repository.CompanyRepository;
import co.com.adnII.infrastructure.persistence.jdbi.context.CompanyExecutionContext;
import co.com.adnII.infrastructure.persistence.jdbi.mapper.CompanyMapper;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Query;
import org.skife.jdbi.v2.TransactionCallback;
import org.skife.jdbi.v2.Update;
import org.skife.jdbi.v2.tweak.HandleCallback;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;

@Singleton
public class JDBICompanyRepository implements CompanyRepository {

    private final DBI dbi;
    private final CompanyExecutionContext ec;

    @Inject
    public JDBICompanyRepository(ConnectionPool connectionPool, CompanyExecutionContext ec) {
        this.dbi = new DBI(connectionPool.getDataSource());
        this.ec = ec;
    }

    @Override
    public CompletionStage<Stream<Company>> list() {
        return supplyAsync(() -> wrapHandle(select()), ec);
    }

    @Override
    public CompletionStage<Void> create(Company company) {
        return runAsync(() -> wrapTransaction(insert(company)), ec);
    }

    private <T> T wrapTransaction(TransactionCallback<T> callback) {
        return dbi.inTransaction(callback);
    }

    private <T> T wrapHandle(HandleCallback<T> callback) {
        return dbi.withHandle(callback);
    }

    private HandleCallback<Stream<Company>> select() {
        return (conn) -> {
            Query query = conn.createQuery("SELECT id,nit,name,phone_number,email FROM company");
            return query.map(new CompanyMapper()).list().stream();
        };
    }


    private TransactionCallback<Void> insert(Company company) {
        return (conn, status) -> {
            Update update= conn.createStatement("INSERT INTO company (name,nit,email,phone_number) VALUES(:name,:nit,:email,:phone_number)");
            update.bind("name",company.getName());
            update.bind("nit",company.getNit());
            update.bind("email",company.getEmail());
            update.bind("phone_number",company.getPhoneNumber());
            update.execute();
            return null;
        };
    }


}
