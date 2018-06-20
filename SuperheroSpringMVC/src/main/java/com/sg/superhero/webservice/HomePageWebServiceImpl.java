package com.sg.superhero.webservice;

import com.sg.dto.Hero;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.model.viewmodel.homepage.HomePageHeroViewModel;
import com.sg.model.viewmodel.homepage.HomePageLocationViewModel;
import com.sg.model.viewmodel.homepage.HomePageSightingViewModel;
import com.sg.model.viewmodel.homepage.HomePageViewModel;
import com.sg.service.HeroService;
import com.sg.service.LocationService;
import com.sg.service.SightingService;
import com.sg.webservice.HomePageWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HomePageWebServiceImpl implements HomePageWebService {

    HeroService heroService;

    SightingService sightingService;

    LocationService locationService;

    @Inject
    public HomePageWebServiceImpl(HeroService heroService, SightingService sightingService, LocationService locationService) {
        this.heroService = heroService;
        this.sightingService = sightingService;
        this.locationService = locationService;
    }

    @Override
    public HomePageViewModel retrieveHomePageViewModel(Integer limit) {

        //Instantiate
        HomePageViewModel homePageViewModel = new HomePageViewModel();

        //Look up stuff (Lists)
        List<Sighting> sightingList = sightingService.retrieveAllSightings(limit, 0);

        //Put stuff in
        homePageViewModel.setSightings(translateSightingList(sightingList));

        return homePageViewModel;
    }

    //#1
    private List<HomePageSightingViewModel> translateSightingList(List<Sighting> sightingList) {
        List<HomePageSightingViewModel> homePageSightingViewModels = new ArrayList<>();

        for(Sighting sighting : sightingList) {
            homePageSightingViewModels.add(translateSighting(sighting));
        }
        return homePageSightingViewModels;
    }

    //#2
    private HomePageSightingViewModel translateSighting(Sighting sighting) {
        HomePageSightingViewModel homePageSightingViewModel = new HomePageSightingViewModel();
        List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

        Location location = locationService.retrieveLocation(sighting.getLocation().getLocationId());

        homePageSightingViewModel.setId(sighting.getSightingId());
        homePageSightingViewModel.setDate(sighting.getDate());
        homePageSightingViewModel.setDescription(sighting.getDescription());

        homePageSightingViewModel.setLocation(translateLocation(location));

        homePageSightingViewModel.setHeroes(translateHeroes(heroList));

        return homePageSightingViewModel;
    }

    //Hero #1
    private List<HomePageHeroViewModel> translateHeroes(List<Hero> heroList) {

        List <HomePageHeroViewModel> homePageHeroViewModels = new ArrayList<>();

        for(Hero hero : heroList) {
            homePageHeroViewModels.add(translateHeroList(hero));
        }
        return homePageHeroViewModels;
    }

    //Hero #2
    private HomePageHeroViewModel translateHeroList(Hero hero) {

        HomePageHeroViewModel homePageHeroViewModel = new HomePageHeroViewModel();
        homePageHeroViewModel.setId(hero.getHeroId());
        homePageHeroViewModel.setName(hero.getName());
        return homePageHeroViewModel;
    }

    //Location
    private HomePageLocationViewModel translateLocation(Location location) {

        HomePageLocationViewModel homePageLocationViewModel = new HomePageLocationViewModel();
        homePageLocationViewModel.setId(location.getLocationId());
        homePageLocationViewModel.setName(location.getName());
        return homePageLocationViewModel;
    }
}
