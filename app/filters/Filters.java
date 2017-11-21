package filters;
import play.mvc.EssentialFilter;
import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;
import play.mvc.EssentialFilter;
import play.filters.hosts.AllowedHostsFilter;
import javax.inject.Inject;

public class Filters extends DefaultHttpFilters {
    @Inject public Filters(CORSFilter corsFilter,AllowedHostsFilter allowedHostsFilter) {
        super(corsFilter,allowedHostsFilter);
    }
}