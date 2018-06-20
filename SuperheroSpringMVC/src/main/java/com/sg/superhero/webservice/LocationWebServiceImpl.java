package com.sg.superhero.webservice;

import com.sg.dto.*;
import com.sg.model.commandmodel.location.editlocation.LocationEditCommandModel;
import com.sg.model.commandmodel.location.locationpage.LocationPageCreateCommandModel;
import com.sg.model.viewmodel.location.detailslocation.LocationDetailsViewModel;
import com.sg.model.viewmodel.location.editlocation.LocationEditViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationPageViewModel;
import com.sg.model.viewmodel.location.locationpage.LocationViewModel;
import com.sg.service.HeroSightingService;
import com.sg.service.LocationService;
import com.sg.service.OrganizationService;
import com.sg.service.SightingService;
import com.sg.superhero.webservice.util.PagingUtils;
import com.sg.webservice.DependencyException;
import com.sg.webservice.LocationWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class LocationWebServiceImpl implements LocationWebService{

    LocationService locationService;

    SightingService sightingService;

    OrganizationService organizationService;

    @Inject
    public LocationWebServiceImpl(LocationService locationService, SightingService sightingService, OrganizationService organizationService) {

        this.sightingService = sightingService;
        this.locationService = locationService;
        this.organizationService = organizationService;
    }

    @Override
    public LocationPageViewModel retrieveLocationPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        //Instantiate
        LocationPageViewModel locationPageViewModel = new LocationPageViewModel();

        //Look stuff up
        List<Location> locations = locationService.retrieveAllLocations(limit, offset);
        LocationPageCreateCommandModel locationPageCreateCommandModel = new LocationPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        //put stuff in
        locationPageViewModel.setPageNumber(currentPage);
        locationPageViewModel.setPageNumbers(pages);

        locationPageViewModel.setLocationPageCreateCommandModel(locationPageCreateCommandModel);

        //translate
        locationPageViewModel.setLocations(translateLocationList(locations));

        return locationPageViewModel;
    }

    @Override
    public Location saveLocationPageCreateCommandModel(LocationPageCreateCommandModel locationPageCreateCommandModel) {

        //Instantiate
        Location location = new Location();

        //Put stuff in
        location.setName(locationPageCreateCommandModel.getName());
        location.setDescription(locationPageCreateCommandModel.getDescription());
        location.setCity(locationPageCreateCommandModel.getCity());
        location.setState(locationPageCreateCommandModel.getState());
        location.setStreet(locationPageCreateCommandModel.getStreet());
        location.setZip(locationPageCreateCommandModel.getZip());
        if(locationPageCreateCommandModel.getLatitude() != null) {
            location.setLatitude(locationPageCreateCommandModel.getLatitude());
        }
        if(locationPageCreateCommandModel.getLongitude() != null) {
            location.setLongitude(locationPageCreateCommandModel.getLongitude());
        }
        return locationService.addLocation(location);
    }

    @Override
    public LocationEditViewModel retrieveLocationEditViewModel(Long locationId) {

        LocationEditViewModel locationEditViewModel = new LocationEditViewModel();
        Location existingLocation = locationService.retrieveLocation(locationId);

        LocationEditCommandModel locationEditCommandModel = new LocationEditCommandModel();

        locationEditCommandModel.setLocationId(existingLocation.getLocationId());
        locationEditCommandModel.setName(existingLocation.getName());
        locationEditCommandModel.setDescription(existingLocation.getDescription());
        locationEditCommandModel.setCity(existingLocation.getCity());
        locationEditCommandModel.setStreet(existingLocation.getStreet());
        locationEditCommandModel.setState(existingLocation.getState());
        locationEditCommandModel.setZip(existingLocation.getZip());
        if(existingLocation.getLatitude() != null) {
            locationEditCommandModel.setLatitude(existingLocation.getLatitude());
        }
        if(existingLocation.getLongitude() != null) {
            locationEditCommandModel.setLongitude(existingLocation.getLongitude());
        }

        locationEditViewModel.setLocationEditCommandModel(locationEditCommandModel);

        return locationEditViewModel;
    }

    @Override
    public Location saveLocationEditCommandModel(LocationEditCommandModel locationEditCommandModel) {

        Location location = locationService.retrieveLocation(locationEditCommandModel.getLocationId());


        location.setName(locationEditCommandModel.getName());
        location.setDescription(locationEditCommandModel.getDescription());
        location.setCity(locationEditCommandModel.getCity());
        location.setState(locationEditCommandModel.getState());
        location.setStreet(locationEditCommandModel.getStreet());
        location.setZip(locationEditCommandModel.getZip());
        if(locationEditCommandModel.getLatitude() != null) {
            location.setLatitude(locationEditCommandModel.getLatitude());
        }
        if(locationEditCommandModel.getLongitude() != null) {
            location.setLongitude(locationEditCommandModel.getLongitude());
        }

        locationService.updateLocation(location);

        return location;
    }

    @Override
    public LocationDetailsViewModel retrieveLocationDetailsViewModel(Long locationId) {

        //Instantiate
        LocationDetailsViewModel locationDetailsViewModel = new LocationDetailsViewModel();
        Location location = locationService.retrieveLocation(locationId);

        //Put Stuff
        locationDetailsViewModel.setId(location.getLocationId());
        locationDetailsViewModel.setName(location.getName());
        locationDetailsViewModel.setDescription(location.getDescription());
        locationDetailsViewModel.setCity(location.getCity());
        locationDetailsViewModel.setStreet(location.getStreet());
        locationDetailsViewModel.setState(location.getState());
        locationDetailsViewModel.setZip(location.getZip());
        if(location.getLatitude() != null) {
            locationDetailsViewModel.setLatitude(location.getLatitude());
        }
        if(location.getLongitude() != null) {
            locationDetailsViewModel.setLongitude(location.getLongitude());
        }

        return locationDetailsViewModel;
    }

    @Override
    public void removeLocationViewModel(Long locationId) throws DependencyException {

        Location location = locationService.retrieveLocation(locationId);

        List<Organization> organizationList = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);
        for(Organization organization : organizationList) {
            if(organization.getLocation().getLocationId().equals(location.getLocationId())) {
                throw new DependencyException("Sorry, unable to delete due to existing dependency");
            }
        }

        List<Sighting> sightingL = sightingService.retrieveSightingByLocation(location, Integer.MAX_VALUE, 0);

        if(sightingL.size() > 0) {
            throw new DependencyException("Sorry, unable to delete due to existing dependency");
        }

        locationService.removeLocation(location);
    }

    //TRANSLATE METHODS FOR LOCATION PAGE VIEW MODELS

    private List<LocationViewModel> translateLocationList(List<Location> locations) {

        List<LocationViewModel>  locationViewModels = new ArrayList<>();

        for (Location location : locations) {

            locationViewModels.add(translateLocation(location));
        }
        return locationViewModels;
    }

    private LocationViewModel translateLocation (Location location) {
        LocationViewModel locationViewModel =new LocationViewModel();
        locationViewModel.setId(location.getLocationId());
        locationViewModel.setName(location.getName());

        return locationViewModel;
    }

}
