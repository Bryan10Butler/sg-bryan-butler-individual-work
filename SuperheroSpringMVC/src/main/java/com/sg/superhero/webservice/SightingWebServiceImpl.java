package com.sg.superhero.webservice;

import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.model.commandmodel.sighting.editsighting.SightingEditCommandModel;
import com.sg.model.commandmodel.sighting.sightingpage.SightingPageCreateCommandModel;
import com.sg.model.viewmodel.sighting.detailsighting.SightingDetailsHeroViewModel;
import com.sg.model.viewmodel.sighting.detailsighting.SightingDetailsViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditHeroViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditLocationViewModel;
import com.sg.model.viewmodel.sighting.editsighting.SightingEditViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageHeroViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageLocationViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingPageViewModel;
import com.sg.model.viewmodel.sighting.sightingpage.SightingViewModel;
import com.sg.service.HeroService;
import com.sg.service.HeroSightingService;
import com.sg.service.LocationService;
import com.sg.service.SightingService;
import com.sg.superhero.webservice.util.PagingUtils;
import com.sg.webservice.PowerWebService;
import com.sg.webservice.SightingWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class SightingWebServiceImpl implements SightingWebService {

    SightingService sightingService;

    LocationService locationService;

    HeroService heroService;

    HeroSightingService heroSightingService;

    @Inject
    public SightingWebServiceImpl(SightingService sightingService, LocationService locationService, HeroService heroService, HeroSightingService heroSightingService) {
        this.sightingService = sightingService;
        this.locationService = locationService;
        this.heroService = heroService;
        this.heroSightingService = heroSightingService;
    }

    @Override
    public SightingPageViewModel retrieveSightingPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        //Instantiate
        SightingPageViewModel sightingPageViewModel = new SightingPageViewModel();

        //Look up stuff (Lists)
        List<Sighting> sightingList = sightingService.retrieveAllSightings(limit, offset);
        List<Hero> heroList = heroService.retrieveAllHero(Integer.MAX_VALUE, 0);
        List<Location> locationList = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);

        //Command Model
        SightingPageCreateCommandModel commandModel = new SightingPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        //Put stuff in
        sightingPageViewModel.setPageNumber(currentPage);
        sightingPageViewModel.setPageNumbers(pages);

        sightingPageViewModel.setSightingPageCreateCommandModel(commandModel);

        sightingPageViewModel.setSightings(translateSightingList(sightingList));
        sightingPageViewModel.setLocations(translateSightingLocationList(locationList));
        sightingPageViewModel.setHeroes(translateSightingHeroesList(heroList));

        return sightingPageViewModel;
    }

    @Override
    public Sighting saveSightingPageCreateCommandModel(SightingPageCreateCommandModel sightingPageCreateCommandModel) {

        //Instantiate
        Sighting sighting = new Sighting();

        //Look up stuff
        List<Hero> heroList = new ArrayList<>();

        for (Long heroId : sightingPageCreateCommandModel.getHeroId()) {
            heroList.add(heroService.retrieveHero(heroId));
        }

        //Put stuff in(set fields using values from passed in object (user input)
        sighting.setDate(sightingPageCreateCommandModel.getDate());
        sighting.setDescription(sightingPageCreateCommandModel.getDescription());

        //Lazy Loading
        if (sightingPageCreateCommandModel.getLocationId() != null) {
            Location location = locationService.retrieveLocation(sightingPageCreateCommandModel.getLocationId());
            sighting.setLocation(location);
        }

        //create sighting
        Sighting createdSighting = sightingService.addSighting(sighting);

        //create relationships
        for (Hero hero : heroList) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHero(hero);
            heroSighting.setSighting(createdSighting);
            heroSightingService.addHeroSighting(heroSighting);
        }

        return createdSighting;
    }

    @Override
    public SightingEditViewModel retrieveSightingEditViewModel(Long sightingId) {

        //Instantiate
        SightingEditViewModel sightingEditViewModel = new SightingEditViewModel();

        //Look stuff up
        Sighting existingSighting = sightingService.retrieveSighting(sightingId);
        List<Location> allLocations = locationService.retrieveAllLocations(Integer.MAX_VALUE, 0);
        List<Hero> allHeroes = heroService.retrieveAllHero(Integer.MAX_VALUE, 0);

        Location selectedLocation = null;
        if (existingSighting.getLocation() != null) {
            selectedLocation = locationService.retrieveLocation(existingSighting.getLocation().getLocationId());
        }

        List<Hero> existingHeroes = heroService.retrieveHeroBySighting(existingSighting, Integer.MAX_VALUE, 0);

        //TRANSLATE
        sightingEditViewModel.setHeroes(translateEditHeroViewModel(allHeroes));
        sightingEditViewModel.setLocations(translateEditLocationViewModel(allLocations));

        //Populate the command model
        SightingEditCommandModel sightingEditCommandModel = new SightingEditCommandModel();
        sightingEditCommandModel.setSightingId(existingSighting.getSightingId());
        sightingEditCommandModel.setLocationId(existingSighting.getLocation().getLocationId());
        sightingEditCommandModel.setDescription(existingSighting.getDescription());
        sightingEditCommandModel.setDate(existingSighting.getDate());

        if (selectedLocation != null) {
            sightingEditCommandModel.setLocationId(selectedLocation.getLocationId());
        }

        if (existingHeroes.size() > 0) {
            Long[] heroIds = new Long[existingHeroes.size()];

            int counter = 0;
            for (Hero hero : existingHeroes) {
                heroIds[counter] = hero.getHeroId();
                counter++;
            }
            sightingEditCommandModel.setHeroIds(heroIds);
        }
        sightingEditViewModel.setSightingEditCommandModel(sightingEditCommandModel);

        return sightingEditViewModel;
    }

    @Override
    public Sighting saveSightingEditCommandModel(SightingEditCommandModel sightingEditCommandModel) {

        //Instantiate a new sighting
        Sighting sighting = sightingService.retrieveSighting(sightingEditCommandModel.getSightingId());

        //Look up stuff
        Location existingLocation = locationService.retrieveLocation(sightingEditCommandModel.getLocationId());
        List<Hero> heroes = new ArrayList<>();

        for (Long heroId : sightingEditCommandModel.getHeroIds()) {
            heroes.add(heroService.retrieveHero(heroId));
        }

        //put stuff in
        sighting.setDate(sightingEditCommandModel.getDate());
        sighting.setDescription(sightingEditCommandModel.getDescription());
        sighting.setLocation(existingLocation);


        //Save stuff
        sightingService.updateSighting(sighting);

        //Delete existing relationships
        //only delete if involves the bridge table
        List<HeroSighting> existingRelationships = heroSightingService.retrieveHeroSightingBySighting(sighting.getSightingId());

        for (HeroSighting heroSighting : existingRelationships) {
            heroSightingService.removeHeroSighting(heroSighting);
        }

        //create relationships
        for (Hero hero : heroes) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setSighting(sighting);
            heroSighting.setHero(hero);
            heroSightingService.addHeroSighting(heroSighting);
        }
        return sighting;
    }

    @Override
    public SightingDetailsViewModel retrieveSightingDetailsViewModel(Long sightingId) {

        //Instantiate
        SightingDetailsViewModel sightingDetailsViewModel = new SightingDetailsViewModel();

        //Look stuff up
        Sighting sighting = sightingService.retrieveSighting(sightingId);


        Location location = null;
        if (sighting.getLocation() != null) {
            location = locationService.retrieveLocation(sighting.getLocation().getLocationId());
        }

        List<Hero> heroes = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

        //Put stuff
        sightingDetailsViewModel.setId(sighting.getSightingId());
        sightingDetailsViewModel.setDate(sighting.getDate());
        sightingDetailsViewModel.setDescription(sighting.getDescription());

        if(location != null) {
            sightingDetailsViewModel.setLocationName(location.getName());
        }

        sightingDetailsViewModel.setHeroes(translateSightingDetailsHeroList(heroes));

        return sightingDetailsViewModel;
    }

    @Override
    public void removeSightingViewModel(Long sightingId) {

        Sighting sighting = sightingService.retrieveSighting(sightingId);

        List<HeroSighting> heroSightingList = heroSightingService.retrieveHeroSightingBySighting(sighting.getSightingId());
        for(HeroSighting heroSighting : heroSightingList) {
            heroSightingService.removeHeroSighting(heroSighting);
        }
        sightingService.removeSighting(sighting);
    }

    //TRANSLATE METHODS FOR SIGHTING DETAILS

    private List<SightingDetailsHeroViewModel> translateSightingDetailsHeroList(List<Hero> heroes) {
        List<SightingDetailsHeroViewModel> sightingDetailsHeroViewModels = new ArrayList<>();
        for (Hero hero : heroes) {
            sightingDetailsHeroViewModels.add(translateDetailsHero(hero));
        }
        return sightingDetailsHeroViewModels;
    }

    private SightingDetailsHeroViewModel translateDetailsHero(Hero hero) {

        SightingDetailsHeroViewModel sightingDetailsHeroViewModel = new SightingDetailsHeroViewModel();
        sightingDetailsHeroViewModel.setId(hero.getHeroId());
        sightingDetailsHeroViewModel.setName(hero.getName());
        return sightingDetailsHeroViewModel;
    }

    //TRANSLATE METHOD FOR SIGHTING
    private List<SightingViewModel> translateSightingList(List<Sighting> sightingList) {
        List<SightingViewModel> sightingViewModels = new ArrayList<>();
        for (Sighting sighting : sightingList) {
            sightingViewModels.add(translateSighting(sighting));
        }
        return sightingViewModels;
    }

    private SightingViewModel translateSighting(Sighting sighting) {
        SightingViewModel sightingViewModel = new SightingViewModel();

        Location location = locationService.retrieveLocation(sighting.getLocation().getLocationId());

        sightingViewModel.setId(sighting.getSightingId());
        sightingViewModel.setName(location.getName());

        return sightingViewModel;
    }

    //TRANSLATE METHOD FOR LOCATIONS
    private List<SightingPageLocationViewModel> translateSightingLocationList(List<Location> locationList) {

        List<SightingPageLocationViewModel> sightingPageLocationViewModels = new ArrayList<>();

        for (Location location : locationList) {
            sightingPageLocationViewModels.add(translateLocation(location));
        }
        return sightingPageLocationViewModels;
    }

    private SightingPageLocationViewModel translateLocation(Location location) {
        SightingPageLocationViewModel sightingPageLocationViewModel = new SightingPageLocationViewModel();
        sightingPageLocationViewModel.setId(location.getLocationId());
        sightingPageLocationViewModel.setName(location.getName());

        return sightingPageLocationViewModel;
    }

    //TRANSLATE METHOD FOR HEROES
    private List<SightingPageHeroViewModel> translateSightingHeroesList(List<Hero> heroList) {
        List<SightingPageHeroViewModel> sightingPageHeroViewModels = new ArrayList<>();

        for (Hero hero : heroList) {
            sightingPageHeroViewModels.add(translateHero(hero));
        }
        return sightingPageHeroViewModels;
    }

    private SightingPageHeroViewModel translateHero(Hero hero) {
        SightingPageHeroViewModel sightingPageHeroViewModel = new SightingPageHeroViewModel();
        sightingPageHeroViewModel.setId(hero.getHeroId());
        sightingPageHeroViewModel.setName(hero.getName());

        return sightingPageHeroViewModel;
    }

    private List<SightingEditLocationViewModel> translateEditLocationViewModel(List<Location> allLocations) {

        List<SightingEditLocationViewModel> sightingEditLocationViewModels = new ArrayList<>();

        for (Location locations : allLocations) {
            SightingEditLocationViewModel sightingEditLocationViewModel = new SightingEditLocationViewModel();
            sightingEditLocationViewModel.setId(locations.getLocationId());
            sightingEditLocationViewModel.setName(locations.getName());
            sightingEditLocationViewModels.add(sightingEditLocationViewModel);
        }
        return sightingEditLocationViewModels;
    }

    private List<SightingEditHeroViewModel> translateEditHeroViewModel(List<Hero> allHeroes) {

        List<SightingEditHeroViewModel> sightingEditHeroViewModels = new ArrayList<>();

        for (Hero heroes : allHeroes) {
            SightingEditHeroViewModel sightingEditHeroViewModel = new SightingEditHeroViewModel();
            sightingEditHeroViewModel.setId(heroes.getHeroId());
            sightingEditHeroViewModel.setName(heroes.getName());
            sightingEditHeroViewModels.add(sightingEditHeroViewModel);
        }
        return sightingEditHeroViewModels;
    }

}
