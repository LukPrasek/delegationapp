package pl.lukaszprasek.delegationApp.domain.services;

import pl.lukaszprasek.delegationApp.common.dto.EmployeeDto;
import pl.lukaszprasek.delegationApp.common.dto.SiteDto;

import java.util.List;

public interface SiteService {
    List<SiteDto> showAllSites();

    SiteDto getSiteById(long id);

    List<EmployeeDto> showAllEmployeesForGivenSiteId(long id);

    SiteDto createSite(SiteDto siteDto);

    Long deleteSiteById(long id);
}
