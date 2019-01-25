package co.com.adnII.infrastructure.persistence.jdbi.mapper;

import co.com.adnII.domain.entities.Company;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements ResultSetMapper<Company> {

    @Override
    public Company map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getLong("id"));
        company.setNit(resultSet.getString("nit"));
        company.setName(resultSet.getString("name"));
        company.setPhoneNumber(resultSet.getString("phone_number"));
        company.setEmail(resultSet.getString("email"));
        return company;
    }
}
