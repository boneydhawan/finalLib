import com.google.inject.AbstractModule;
import com.tekizma.coreServices.CommonDao;
import com.tekizma.coreServices.CommonDaoImpl;
import com.tekizma.services.MainService;
import com.tekizma.services.MainServiceImpl;

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
        bind(MainService.class).to(MainServiceImpl.class);
        bind(CommonDao.class).to(CommonDaoImpl.class);
    }

}
