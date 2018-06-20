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
import com.sg.superhero.webservice.util.TestDataHelperWebService;
import com.sg.webservice.SightingWebService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SightingWebServiceImplTest {

    @Inject
    TestDataHelperWebService testData;

    @Inject
    SightingWebService sightingWebService;

    @Inject
    HeroService heroService;

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Inject
    HeroSightingService heroSightingService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveSightingPageViewModel() {

        //Arrange
        List<Location> locationList = testData.createTestLocations(3);
        List<Sighting> sightingList = testData.createTestSightings(3, locationList.get(0));
        List<Hero> heroList = testData.createTestHeroes(3);

        //Act
        SightingPageViewModel sightingPageViewModel = sightingWebService.retrieveSightingPageViewModel(Integer.MAX_VALUE,0,1);

        //Assert
        assertEquals(locationList.size(), sightingPageViewModel.getLocations().size());
        assertEquals(sightingList.size(), sightingPageViewModel.getSightings().size());
        assertEquals(heroList.size(), sightingPageViewModel.getHeroes().size());
        assertNotNull(sightingPageViewModel.getSightingPageCreateCommandModel());

        //Assert for ID and Name
        //One row (Fenway park) is one view model
        for (SightingViewModel svm : sightingPageViewModel.getSightings()) {
            assertNotNull(svm.getId());
            //assertNotNull(svm.getName());
        }

        for (SightingPageHeroViewModel sphvm : sightingPageViewModel.getHeroes()) {
            assertNotNull(sphvm.getId());
            assertNotNull(sphvm.getName());
        }

        for (SightingPageLocationViewModel splvm : sightingPageViewModel.getLocations()) {
            assertNotNull(splvm.getId());
            assertNotNull(splvm.getName());
        }
    }

    @Test
    public void retrieveSightingPageViewModelPaging() {

        int numberOfSightings = 2;
        //Arrange
        List<Location> locationList = testData.createTestLocations(3);
        List<Sighting> sightingList = testData.createTestSightings(3, locationList.get(0));
        List<Hero> heroList = testData.createTestHeroes(3);

        //Act
        SightingPageViewModel sightingPageViewModel = sightingWebService.retrieveSightingPageViewModel(numberOfSightings,0,1);

        //Assert
        assertEquals(locationList.size(), sightingPageViewModel.getLocations().size());
        assertEquals(numberOfSightings, sightingPageViewModel.getSightings().size());
        assertEquals(heroList.size(), sightingPageViewModel.getHeroes().size());
        assertNotNull(sightingPageViewModel.getSightingPageCreateCommandModel());

        //Assert for ID and Name
        //One row (Fenway park) is one view model
        for (SightingViewModel svm : sightingPageViewModel.getSightings()) {
            assertNotNull(svm.getId());
            //assertNotNull(svm.getName());
        }

        for (SightingPageHeroViewModel sphvm : sightingPageViewModel.getHeroes()) {
            assertNotNull(sphvm.getId());
            assertNotNull(sphvm.getName());
        }

        for (SightingPageLocationViewModel splvm : sightingPageViewModel.getLocations()) {
            assertNotNull(splvm.getId());
            assertNotNull(splvm.getName());
        }
    }

    @Test
    public void saveSightingPageCreateCommandModel() {

        //Arrange
        List<Hero> heroList = testData.createTestHeroes(2);
        Long[] heroIds = new Long[2];
        heroIds[0] = heroList.get(0).getHeroId();
        heroIds[1] = heroList.get(1).getHeroId();

        Location location = testData.createTestLocation();
        SightingPageCreateCommandModel sightingPageCreateCommandModel = new SightingPageCreateCommandModel();

        //Set Data on command model
        sightingPageCreateCommandModel.setHeroId(heroIds);
        sightingPageCreateCommandModel.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sightingPageCreateCommandModel.setDescription("Boston Common");
        sightingPageCreateCommandModel.setLocationId(location.getLocationId());

        //Act
        Sighting createdSighting = sightingWebService.saveSightingPageCreateCommandModel(sightingPageCreateCommandModel);

        //Assert
        assertNotNull(createdSighting.getSightingId());
        assertEquals(sightingPageCreateCommandModel.getDate(), createdSighting.getDate());
        assertEquals(sightingPageCreateCommandModel.getDescription(), createdSighting.getDescription());
        assertEquals(sightingPageCreateCommandModel.getLocationId(), createdSighting.getLocation().getLocationId());

        List<Hero> heroes = heroService.retrieveHeroBySighting(createdSighting, Integer.MAX_VALUE, 0);
        boolean savedHeroOne = false;
        boolean savedHeroTwo = false;

        for (Hero hero : heroes) {
            if(hero.getHeroId().equals(heroList.get(0).getHeroId())) savedHeroOne = true;
            if(hero.getHeroId().equals(heroList.get(1).getHeroId())) savedHeroTwo = true;
        }
        assertTrue(savedHeroOne);
        assertTrue(savedHeroTwo);
    }

    @Test
    public void retrieveSightingEditViewModel() {

        //Arrange
        //could have a list of heroes
        List<Hero> heroList = testData.createTestHeroes(2);
        //could have a list of locations
        List<Location> locationList = testData.createTestLocations(2);

        //Get first location as user is selecting one location
        Location createdLocation = locationList.get(0);

        //Heroes seen at that one location
        List<Hero> selectedHeroes = new ArrayList<>();
        selectedHeroes.add(heroList.get(0));
        selectedHeroes.add(heroList.get(1));

        Sighting existingSighting = testData.createTestSightingWithHeroAndLocation(createdLocation, selectedHeroes);

        //Act
        SightingEditViewModel sightingEditViewModel = sightingWebService.retrieveSightingEditViewModel(existingSighting.getSightingId());

        //Assert
        assertEquals(sightingEditViewModel.getHeroes().size(), heroList.size());
        assertEquals(sightingEditViewModel.getLocations().size(), locationList.size());

        //assert sightingEditHeroViewModel id and name not null
        for(SightingEditHeroViewModel sightingEditHeroViewModel : sightingEditViewModel.getHeroes()) {
            assertNotNull(sightingEditHeroViewModel.getId());
            assertNotNull(sightingEditHeroViewModel.getName());
        }

        //assert sightingEditLocationViewModel id and name not null
        for(SightingEditLocationViewModel sightingEditLocationViewModel : sightingEditViewModel.getLocations()) {
            assertNotNull(sightingEditLocationViewModel.getId());
            assertNotNull(sightingEditLocationViewModel.getName());
        }

        SightingEditCommandModel sightingEditCommandModel = sightingEditViewModel.getSightingEditCommandModel();

        assertEquals(sightingEditCommandModel.getDate(), existingSighting.getDate());
        assertEquals(sightingEditCommandModel.getDescription(), existingSighting.getDescription());
        assertEquals(sightingEditCommandModel.getLocationId(), existingSighting.getLocation().getLocationId());
        assertEquals(sightingEditCommandModel.getSightingId(), existingSighting.getSightingId());

        boolean containsFirstHero = false;
        boolean containsSecondHero = false;
        for(Long heroId : sightingEditCommandModel.getHeroIds()) {
            if(heroId.equals(heroList.get(0).getHeroId())) containsFirstHero = true;
            if(heroId.equals(heroList.get(1).getHeroId())) containsSecondHero = true;
        }
        assertTrue(containsFirstHero);
        assertTrue(containsSecondHero);
    }

    @Test
    public void saveSightingEditCommandModel() {

        //Arrange
        //could have a list of heroes
        List<Hero> heroList = testData.createTestHeroes(2);
        //could have a list of locations
        Location location = testData.createTestLocation();

        //create an existing sighting with single location and hero list
        Sighting existingSighting = testData.createTestSightingWithHeroAndLocation(location, heroList);

        //instantiate command model
        SightingEditCommandModel sightingEditCommandModel = new SightingEditCommandModel();

        sightingEditCommandModel.setSightingId(existingSighting.getSightingId());
        sightingEditCommandModel.setLocationId(existingSighting.getLocation().getLocationId());
        sightingEditCommandModel.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sightingEditCommandModel.setDescription("Mall");

        //Select different location
        Location updatedLocation = testData.createTestLocation();
        sightingEditCommandModel.setLocationId(updatedLocation.getLocationId());

        //Different Heroes
        List<Hero> updatedHeroes = testData.createTestSecondListOfTestHeroes(2);

        Long[] heroIds = new Long[2];
        heroIds[0] = updatedHeroes.get(0).getHeroId();
        heroIds[1] = updatedHeroes.get(1).getHeroId();

        //set on command model
        sightingEditCommandModel.setHeroIds(heroIds);

        //Act
        Sighting updatedSighting = sightingWebService.saveSightingEditCommandModel(sightingEditCommandModel);

        //Assert
        assertNotNull(updatedSighting.getSightingId());
        assertEquals(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE), updatedSighting.getDate());
        assertEquals("Mall", updatedSighting.getDescription());
        assertEquals(updatedSighting.getLocation().getLocationId(), updatedLocation.getLocationId());

        List<Hero> heroes = heroService.retrieveHeroBySighting(updatedSighting, Integer.MAX_VALUE, 0);
        boolean savedFirstHero = false;
        boolean savedSecondHero = false;
        boolean deletedFirstHero = true;

        for(Hero hero : heroes) {
            if(hero.getHeroId().equals(updatedHeroes.get(0).getHeroId())) savedFirstHero = true;
            if(hero.getHeroId().equals(updatedHeroes.get(1).getHeroId())) savedSecondHero = true;
            if(hero.getHeroId().equals(heroList.get(0).getHeroId())) deletedFirstHero = false;
        }
        assertTrue(savedFirstHero);
        assertTrue(savedSecondHero);
        assertTrue(deletedFirstHero);
    }

    @Test
    public void retrieveSightingDetailsViewModel() {

        //Create Location
        Location location = new Location();
        location.setName("Heroes Inc");
        location.setDescription("Tall Building");
        location.setCity("Boston");
        location.setState("MA");
        location.setStreet("100 Hero Ave");
        location.setZip("01234");
        location.setLatitude("23.10");
        location.setLongitude("45.50");

        Location createdLocation = locationService.addLocation(location);

        //Create new sighting
        Sighting sighting = new Sighting();
        sighting.setLocation(createdLocation);
        sighting.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sighting.setDescription("Downtown Portsmouth");

        Sighting createdSighting = sightingService.addSighting(sighting);

        //create test heroes
        Hero heroOne = new Hero();
        heroOne.setName("Thor");
        heroOne.setDescription("Throws a hammer");

        Hero heroTwo = new Hero();
        heroTwo.setName("Batman");
        heroTwo.setDescription("Lives in Gotham City");

        //save test heroes
        Hero savedHeroOne = heroService.addHero(heroOne);
        Hero savedHeroTwo = heroService.addHero(heroTwo);

        //Associate with Sighting
        HeroSighting heroSightingSavedHeroOne = new HeroSighting();
        heroSightingSavedHeroOne.setHero(savedHeroOne);
        heroSightingSavedHeroOne.setSighting(createdSighting);

        HeroSighting heroSightingSavedHeroTwo = new HeroSighting();
        heroSightingSavedHeroTwo.setHero(savedHeroTwo);
        heroSightingSavedHeroTwo.setSighting(createdSighting);

        heroSightingService.addHeroSighting(heroSightingSavedHeroOne);
        heroSightingService.addHeroSighting(heroSightingSavedHeroTwo);

        //Act
        SightingDetailsViewModel sightingDetailsViewModel = sightingWebService.retrieveSightingDetailsViewModel(createdSighting.getSightingId());

        assertEquals(sightingDetailsViewModel.getId(), createdSighting.getSightingId());
        assertEquals(sightingDetailsViewModel.getDate(), createdSighting.getDate());
        assertEquals(sightingDetailsViewModel.getDescription(), createdSighting.getDescription());

        assertEquals(sightingDetailsViewModel.getLocationName(), createdSighting.getLocation().getName());

        boolean containsFirstHero = false;
        boolean containsSecondHero = false;
        for(SightingDetailsHeroViewModel sightingDetailsHeroViewModel : sightingDetailsViewModel.getHeroes()) {
            if("Thor".equals(sightingDetailsHeroViewModel.getName())) containsFirstHero = true;
            if("Batman".equals(sightingDetailsHeroViewModel.getName())) containsSecondHero = true;
        }
        assertTrue(containsFirstHero);
        assertTrue(containsSecondHero);
    }
}