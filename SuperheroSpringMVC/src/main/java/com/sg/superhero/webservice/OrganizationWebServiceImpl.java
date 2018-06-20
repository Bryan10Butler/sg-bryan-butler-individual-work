package com.sg.superhero.webservice;

import com.sg.dto.*;
import com.sg.model.commandmodel.organization.editorganization.OrganizationEditCommandModel;
import com.sg.model.commandmodel.organization.organizationpage.OrganizationPageCreateCommandModel;
import com.sg.model.viewmodel.organization.detailsorganization.OrganizationDetailsViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditLocationViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageLocationViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationViewModel;
import com.sg.service.HeroOrganizationService;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.superhero.webservice.util.PagingUtils;
import com.sg.webservice.OrganizationWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class OrganizationWebServiceImpl implements OrganizationWebService {

    OrganizationService organizationService;

    LocationService locationService;

    HeroOrganizationService heroOrganizationService;

    @Inject
    public OrganizationWebServiceImpl(OrganizationService organizationService, LocationService locationService, HeroOrganizationService heroOrganizationService) {
        this.organizationService = organizationService;
        this.locationService = locationService;
        this.heroOrganizationService = heroOrganizationService;
    }

    @Override
    public OrganizationPageViewModel retrieveOrganizationPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        //Instantiate
        OrganizationPageViewModel organizationPageViewModel = new OrganizationPageViewModel();

        //Look stuff up
        List<Organization> organizations = organizationService.retrieveAllOrganizations(limit, offset);
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);

        //Command Model
        OrganizationPageCreateCommandModel commandModel = new OrganizationPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        //Put stuff in
        organizationPageViewModel.setPageNumber(currentPage);
        organizationPageViewModel.setPageNumbers(pages);

        organizationPageViewModel.setOrganizationPageCreateCommandModel(commandModel);

        organizationPageViewModel.setOrganizations(translateOrganizationList(organizations));
        organizationPageViewModel.setLocations(translateOrganizationLocationList(locations));

        return organizationPageViewModel;
    }

    @Override
    public Organization saveOrganizationPageCreateCommandModel(OrganizationPageCreateCommandModel organizationPageCreateCommandModel) {

        //Instantiate
        Organization organization = new Organization();

        //Put stuff in (Set fields using values from passed in object (user input))
        organization.setName(organizationPageCreateCommandModel.getName());
        organization.setDescription(organizationPageCreateCommandModel.getDescription());

        //Lazy Loading
        if (organizationPageCreateCommandModel.getLocationId() != null) {
            Location location = locationService.retrieveLocation(organizationPageCreateCommandModel.getLocationId());
            organization.setLocation(location);
        }
        return organizationService.addOrganization(organization);
    }

    @Override
    public OrganizationEditViewModel retrieveOrganizationEditViewModel(Long organizationId) {

        //Instantiate
        OrganizationEditViewModel organizationEditViewModel = new OrganizationEditViewModel();

        //Look stuff up
        Organization existingOrganization = organizationService.retrieveOrganization(organizationId);
        List<Location> locations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);

        //Command model
        OrganizationEditCommandModel organizationEditCommandModel = new OrganizationEditCommandModel();

        //Put stuff in
        organizationEditCommandModel.setOrganizationId(existingOrganization.getOrganizationId());
        organizationEditCommandModel.setName(existingOrganization.getName());
        organizationEditCommandModel.setDescription(existingOrganization.getDescription());

        //Lazy Loading
        if (existingOrganization.getLocation().getLocationId() != null) {
            Location location = locationService.retrieveLocation(existingOrganization.getLocation().getLocationId());
            organizationEditCommandModel.setLocationId(location.getLocationId());
        }

        organizationEditViewModel.setLocations(translateOrganizationEditLocationsList(locations));
        organizationEditViewModel.setOrganizationEditCommandModel(organizationEditCommandModel);

        return organizationEditViewModel;
    }

    @Override
    public Organization saveOrganizationEditCommandModel(OrganizationEditCommandModel organizationEditCommandModel) {

        Organization organization = organizationService.retrieveOrganization(organizationEditCommandModel.getOrganizationId());

        organization.setName(organizationEditCommandModel.getName());
        organization.setDescription(organizationEditCommandModel.getDescription());

        //Lazy Loading
        if (organizationEditCommandModel.getLocationId() != null) {
            Location location = locationService.retrieveLocation(organizationEditCommandModel.getLocationId());
            organization.setLocation(location);
        }

        organizationService.updateOrganization(organization);

        return organization;
    }

    @Override
    public OrganizationDetailsViewModel retrieveOrganizationDetailsViewModel(Long organizationId) {

        //Instantiate
        OrganizationDetailsViewModel organizationDetailsViewModel = new OrganizationDetailsViewModel();
        Organization organization = organizationService.retrieveOrganization(organizationId);

        //Put stuff in
        organizationDetailsViewModel.setId(organizationId);
        organizationDetailsViewModel.setName(organization.getName());
        organizationDetailsViewModel.setDescription(organization.getDescription());

        if (organization.getLocation().getLocationId() != null) {
            Location location = locationService.retrieveLocation(organization.getLocation().getLocationId());
            organizationDetailsViewModel.setLocationName(location.getName());
        }
        return organizationDetailsViewModel;
    }

    @Override
    public void removeOrganizationViewModel(Long organizationId) {

        Organization organization = organizationService.retrieveOrganization(organizationId);
        // if organization has heroes related to it, we must delete it from the HeroOrganization bridge table
        List<HeroOrganization> heroOrganizationList = heroOrganizationService.retrieveHeroOrganizationByOrganization(organization.getOrganizationId());
        for(HeroOrganization heroOrganization : heroOrganizationList) {
            heroOrganizationService.removeHeroOrganization(heroOrganization);
        }
        organizationService.removeOrganization(organization);
    }

    //TRANSLATE METHODS FOR ORGANIZATION PAGE VIEW MODELS

    private List<OrganizationViewModel> translateOrganizationList(List<Organization> organizations) {

        List<OrganizationViewModel> organizationViewModels = new ArrayList<>();

        for (Organization organization : organizations) {
            organizationViewModels.add(translateOrganization(organization));
        }
        return organizationViewModels;
    }

    private OrganizationViewModel translateOrganization(Organization organization) {
        OrganizationViewModel organizationViewModel = new OrganizationViewModel();
        organizationViewModel.setId(organization.getOrganizationId());
        organizationViewModel.setName(organization.getName());

        return organizationViewModel;
    }

    //TRANSLATE METHODS FOR ORGANIZATION PAGE VIEW MODELS -- LOCATIONS

    private List<OrganizationPageLocationViewModel> translateOrganizationLocationList(List<Location> locations) {

        List<OrganizationPageLocationViewModel> organizationPageLocationViewModels = new ArrayList<>();

        for (Location location : locations) {

            organizationPageLocationViewModels.add(translateLocation(location));
        }
        return organizationPageLocationViewModels;
    }

    private OrganizationPageLocationViewModel translateLocation(Location location) {
        OrganizationPageLocationViewModel organizationPageLocationViewModel = new OrganizationPageLocationViewModel();
        organizationPageLocationViewModel.setId(location.getLocationId());
        organizationPageLocationViewModel.setName(location.getName());

        return organizationPageLocationViewModel;
    }

    //TRANSLATE METHODS FOR ORGANIZATION PAGE EDIT VIEW MODEL

    private List<OrganizationEditLocationViewModel> translateOrganizationEditLocationsList(List<Location> locations) {

        List<OrganizationEditLocationViewModel> organizationEditLocationViewModels = new ArrayList<>();

        for (Location location : locations) {

            organizationEditLocationViewModels.add(translateEditLocation(location));
        }
        return organizationEditLocationViewModels;
    }

    private OrganizationEditLocationViewModel translateEditLocation(Location location) {
        OrganizationEditLocationViewModel organizationEditLocationViewModel = new OrganizationEditLocationViewModel();
        organizationEditLocationViewModel.setId(location.getLocationId());
        organizationEditLocationViewModel.setName(location.getName());

        return organizationEditLocationViewModel;
    }
}
