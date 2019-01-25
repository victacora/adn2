import co.com.adnII.application.dto.command.CompanyCreateCommandDTO;
import co.com.adnII.application.service.command.ICompanyCommandService;
import co.com.adnII.application.service.impl.command.CompanyCommandService;
import co.com.adnII.application.service.impl.query.CompanyQueryService;
import co.com.adnII.application.service.query.ICompanyQueryService;
import co.com.adnII.domain.repository.CompanyRepository;
import co.com.adnII.infrastructure.persistence.jdbi.repository.JDBICompanyRepository;
import co.com.adnII.infrastructure.persistence.jpa.repository.JPACompanyRepository;
import com.google.inject.AbstractModule;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class Module extends AbstractModule {

    @Override
    public void configure() {

        // bind(CompanyRepository.class).to(JPACompanyRepository.class).asEagerSingleton();
        bind(CompanyRepository.class).to(JDBICompanyRepository.class).asEagerSingleton();
        bind(ICompanyCommandService.class).to(CompanyCommandService.class);
        bind(ICompanyQueryService.class).to(CompanyQueryService.class);

    }

}
