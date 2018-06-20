package com.sg.superhero.service;

import com.sg.dto.Hero;
import com.sg.dto.HeroSighting;
import com.sg.dto.Location;
import com.sg.dto.Sighting;
import com.sg.service.HeroService;
import com.sg.service.HeroSightingService;
import com.sg.service.LocationService;
import com.sg.service.SightingService;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class SightingServiceImplTest {

    @Inject
    SightingService sightingService;

    @Inject
    LocationService locationService;

    @Inject
    HeroService heroService;

    @Inject
    HeroSightingService heroSightingService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addSighting() {

        //Arrange and Act
        Location location = createLocation();
        Sighting sighting = createSighting(location);

        //Assert
        assertNotNull(sighting.getSightingId());
        assertEquals((LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE)), sighting.getDate());
        assertEquals("It's a bird, it's a plane, it's just Bryan", sighting.getDescription());
        assertEquals("Tall Building", sighting.getLocation().getDescription());
        assertEquals("Boston", sighting.getLocation().getCity());
        assertEquals("MA", sighting.getLocation().getState());
        assertEquals("100 Hero Ave", sighting.getLocation().getStreet());
        assertEquals("01234", sighting.getLocation().getZip());
        assertEquals("23.10", sighting.getLocation().getLatitude());
        assertEquals("45.50", sighting.getLocation().getLongitude());
    }

    @Test
    public void removeSighting() {

        //Arrange
        Location location = createLocation();
        Sighting sighting = createSighting(location);

        //Act
        sightingService.removeSighting(sighting);
        Sighting readSighting = sightingService.retrieveSighting(sighting.getSightingId());

        //Assert
        assertNull(readSighting);
    }

    @Test
    public void updateSighting() {

        //Arrange
        Location location = createLocation();
        Sighting sighting = createSighting(location);

        //Update Fields
        sighting.setDate(LocalDate.parse("2018-04-05", DateTimeFormatter.ISO_DATE));
        sighting.setDescription("It's the Black Panther!");

        //Act
        sightingService.updateSighting(sighting);

        //Assert
        Sighting updatedSighting = sightingService.retrieveSighting(sighting.getSightingId());

        assertEquals((LocalDate.parse("2018-04-05", DateTimeFormatter.ISO_DATE)), updatedSighting.getDate());
        assertEquals("It's the Black Panther!", sighting.getDescription());
        assertEquals(location.getLocationId(), updatedSighting.getLocation().getLocationId());
        assertEquals(sighting.getSightingId(), updatedSighting.getSightingId());
    }

    @Test
    public void retrieveAllSightings() {

        //Arrange
        Location location = createLocation();
        Sighting sighting = createSighting(location);

        Location locationTwo = createLocation();
        Sighting sightingTwo = createSighting(locationTwo);

        Sighting readingSighting = sightingService.retrieveSighting(sightingTwo.getSightingId());

        //Update Fields
        readingSighting.setDate((LocalDate.parse("2018-04-06", DateTimeFormatter.ISO_DATE)));
        readingSighting.setDescription("Hulk Smash!");

        sightingService.updateSighting(readingSighting);

        //Act and Assert
        assertNotEquals(sighting.getSightingId(), readingSighting.getSightingId());
        assertEquals(2, sightingService.retrieveAllSightings(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveSighting() {

        //Arrange
        Location location = createLocation();
        Sighting sighting = createSighting(location);

        //Act
        Sighting readSighting = sightingService.retrieveSighting(sighting.getSightingId());

        //Assert
        assertEquals(sighting.getSightingId(), readSighting.getSightingId());
    }

    @Test
    public void retrieveSightingByHero() {

        //Arrange
        //number of heroes
        int numberOfSightings = 2;

        //create hero
        Hero createdHero = createHero();

        //create location
        Location createdLocation = createLocation();
        //create sighting
        Sighting createdSighting = createSighting(createdLocation);

        //create location two
        Location createdLocationTwo = createLocation();
        //create sighting two
        Sighting createdSightingTwo = createSighting(createdLocationTwo);

        //create two hero sighting
        createHeroSighting(createdHero, createdSighting);
        createHeroSighting(createdHero, createdSightingTwo);

        //Act
        List<Sighting> sightingList = sightingService.retrieveSightingByHero(createdHero, Integer.MAX_VALUE, 0);

        //Assert
        assertSightingsByHero(numberOfSightings, sightingList, createdHero);
    }

    @Test
    public void retrieveSightingByHeroPage() {

        //Arrange
        //number of heroes
        int numberOfSightings = 1;

        //create hero
        Hero createdHero = createHero();

        //create location
        Location createdLocation = createLocation();
        //create sighting
        Sighting createdSighting = createSighting(createdLocation);

        //create location two
        Location createdLocationTwo = createLocation();
        //create sighting two
        Sighting createdSightingTwo = createSighting(createdLocationTwo);

        //create two hero sighting
        createHeroSighting(createdHero, createdSighting);
        createHeroSighting(createdHero, createdSightingTwo);

        //Act
        List<Sighting> sightingList = sightingService.retrieveSightingByHero(createdHero, 1, 0);

        //Assert
        assertSightingsByHero(numberOfSightings, sightingList, createdHero);
    }

    @Test
    public void retrieveSightingByLocation() {

        //Arrange
        int numberOfSightings = 2;

        //create location
        Location createdLocation = createLocation();

        //create sighting One
        createSighting(createdLocation);

        //create sighting two
        createSighting(createdLocation);

        //Act
        List <Sighting> sightingList = sightingService.retrieveSightingByLocation(createdLocation, Integer.MAX_VALUE, 0);

        //Assert
        assertSightingsByLocation(numberOfSightings, sightingList, createdLocation);

    }

    @Test
    public void retrieveSightingByLocationPage() {

        //Arrange
        int numberOfSightings = 1;

        //create location
        Location createdLocation = createLocation();

        //create sighting One
        createSighting(createdLocation);

        //create sighting two
        createSighting(createdLocation);

        //Act
        List <Sighting> sightingList = sightingService.retrieveSightingByLocation(createdLocation, 1, 0);

        //Assert
        assertSightingsByLocation(numberOfSightings, sightingList, createdLocation);

    }

    @Test
    public void retrieveSightingByDate() {

        //Arrange
        int numberOfSightings = 2;

        //create location
        Location createdLocation = createLocation();

        //create sighting One
        createSighting(createdLocation);

        //create sighting two
        createSighting(createdLocation);

        //Act
        List <Sighting> sightingList = sightingService.retrieveSightingByDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE), Integer.MAX_VALUE, 0);

        //Assert
        assertSightingsByDate(numberOfSightings, sightingList, (LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE)));

    }

    @Test
    public void retrieveSightingByDatePage() {

        //Arrange
        int numberOfSightings = 1;

        //create location
        Location createdLocation = createLocation();

        //create sighting One
        createSighting(createdLocation);

        //create sighting two
        createSighting(createdLocation);

        //Act
        List <Sighting> sightingList = sightingService.retrieveSightingByDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE), 1, 0);

        //Assert
        assertSightingsByDate(numberOfSightings, sightingList, (LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE)));

    }

    //Helper Methods
    private Sighting createSighting(Location location) {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sighting.setDescription("It's a bird, it's a plane, it's just Bryan");
        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        return sighting;
    }

    private Location createLocation() {
        Location location = new Location();
        location.setName("Heroes");
        location.setDescription("Tall Building");
        location.setCity("Boston");
        location.setState("MA");
        location.setStreet("100 Hero Ave");
        location.setZip("01234");
        location.setLatitude("23.10");
        location.setLongitude("45.50");
        locationService.addLocation(location);
        return location;
    }

    private Hero createHero() {

        //Arrange and Act
        Hero hero = new Hero();
        hero.setName("Wolverine");
        hero.setDescription("A beast with extremely sharp claws");

        //Act
        return heroService.addHero(hero);
    }

    private HeroSighting createHeroSighting(Hero createdHero, Sighting createdSighting) {
        HeroSighting heroSighting = new HeroSighting();

        heroSighting.setHero(createdHero);
        heroSighting.setSighting(createdSighting);

        heroSightingService.addHeroSighting(heroSighting);
        return heroSighting;
    }

    private void assertSightingsByHero(int numberOfSightings, List<Sighting> sightingList, Hero hero) {

        assert sightingList.size() == numberOfSightings;
        //for each organization in organizationList
        for (Sighting sighting : sightingList) {
            //retrieve heroes
            List<Hero> heroList = heroService.retrieveHeroBySighting(sighting, Integer.MAX_VALUE, 0);

            boolean containsHero = false;
            //for each hero in heroOrganization
            for (Hero h : heroList) {
                //make sure the organization we added is contained in the list
                if (h.getHeroId().equals(hero.getHeroId())) containsHero = true;
            }
            assert containsHero == true;
        }
    }

    private void assertSightingsByLocation(int numberOfSightings, List<Sighting> sightingList, Location location) {

        assert sightingList.size() == numberOfSightings;
        //for each sighting in sightingList
        for (Sighting sighting : sightingList) {
            //retrieve sighting
            //List<Hero> heroList = heroService.retrieveHeroByOrganization(organization, Integer.MAX_VALUE, 0);

            boolean containsLocation = false;
            if (sighting.getLocation().getLocationId().equals(location.getLocationId())) containsLocation = true;
            assert containsLocation == true;
        }
    }

    private void assertSightingsByDate(int numberOfSightings, List<Sighting> sightingList, LocalDate Date) {

        assert sightingList.size() == numberOfSightings;
        //for each sighting in sightingList
        for (Sighting sighting : sightingList) {

            boolean containsDate = false;
            if (sighting.getDate().equals(Date)) containsDate = true;
            assert containsDate == true;
        }
    }
}