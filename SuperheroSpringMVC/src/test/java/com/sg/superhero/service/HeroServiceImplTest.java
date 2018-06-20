package com.sg.superhero.service;

import com.sg.dto.*;
import com.sg.service.*;
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
public class HeroServiceImplTest {

    @Inject
    HeroService heroService;

    @Inject
    PowerService powerService;

    @Inject
    HeroPowerService heroPowerService;

    @Inject
    LocationService locationService;

    @Inject
    HeroSightingService heroSightingService;

    @Inject
    SightingService sightingService;

    @Inject
    OrganizationService organizationService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addHero() {

        //Arrange and Act
        Hero createdHero = createHero();

        //Assert
        assertEquals("Wolverine", createdHero.getName());
        assertEquals("A beast with extremely sharp claws", createdHero.getDescription());
    }

    @Test
    public void removeHero() {

        //Arrange
        Hero createdHero = createHero();

        //Act
        heroService.removeHero(createdHero);

        //Assert
        Hero readHero = heroService.retrieveHero(createdHero.getHeroId());
        assertNull(readHero);
    }

    @Test
    public void updateHero() {

        //Arrange
        Hero createdHero = createHero();

        Hero readHero = heroService.retrieveHero(createdHero.getHeroId());

        //Update Fields
        readHero.setName("Thor");
        readHero.setDescription("Thor is from asgard");

        //Act
        heroService.updateHero(readHero);

        //Assert
        Hero updatedHero = heroService.retrieveHero(readHero.getHeroId());
        assertEquals("Thor", updatedHero.getName());
        assertEquals("Thor is from asgard", updatedHero.getDescription());
        assertEquals(createdHero.getHeroId(), updatedHero.getHeroId());
    }

    @Test
    public void retrieveAllHero() {

        //Arrange
        Hero createdHero = createHero();

        //Second Location
        Hero createdHeroTwo = createHero();

        Hero readHero = heroService.retrieveHero(createdHeroTwo.getHeroId());

        readHero.setName("Thor");
        readHero.setDescription("Thor is from asgard");

        heroService.updateHero(readHero);

        //Act and Assert
        assertNotEquals(createdHero.getHeroId(), readHero.getHeroId());
        assertEquals(2, heroService.retrieveAllHero(Integer.MAX_VALUE, 0).size());
    }

    @Test
    public void retrieveHero() {

        //Arrange
        Hero createdHero = createHero();

        //Act
        Hero readHero = heroService.retrieveHero(createdHero.getHeroId());

        //Assert
        assertEquals(createdHero.getHeroId(), readHero.getHeroId());
    }

    @Test
    public void retrieveHeroByPower() {

        //Retrieve all heroes by the same power

        //Arrange
        //number of heroes
        int numberOfHeroes = 2;

        //create power
        Power createdPower = createPower();

        //create two heroes
        Hero createdHero = createHero();
        Hero createdHeroTwo = createHero();

        //create hero power
        createHeroPower(createdHero, createdPower);
        createHeroPower(createdHeroTwo, createdPower);

        //Act
        List<Hero> heroList = heroService.retrieveHeroByPower(createdPower, Integer.MAX_VALUE, 0);

        //Assert
        assertHeroesByPower(numberOfHeroes, heroList ,createdPower);
    }

    @Test
    public void retrieveHeroByPowerPage() {

        //Retrieve all heroes by the same power

        //Arrange
        //number of heroes
        int numberOfHeroes = 1;

        //create power
        Power createdPower = createPower();

        //create two heroes
        Hero createdHero = createHero();
        Hero createdHeroTwo = createHero();

        //create hero power
        createHeroPower(createdHero, createdPower);
        createHeroPower(createdHeroTwo, createdPower);

        //Act
        List<Hero> heroList = heroService.retrieveHeroByPower(createdPower, 1, 0);

        //Assert
        assertHeroesByPower(numberOfHeroes, heroList ,createdPower);
    }

    @Test
    public void retrieveHeroBySighting() {

        //Retrieve all heroes by sighting (starbucks for example)

        //Arrange
        //number of heroes
        int numberOfHeroes = 2;

        //create location
        Location createdLocation = createLocation();

        //create sighting
        Sighting createdSighting = createSighting(createdLocation);

        //create two heroes
        Hero createdHero = createHero();
        Hero createdHeroTwo = createHero();

        //create two hero sighting
        createHeroSighting(createdHero, createdSighting);
        createHeroSighting(createdHeroTwo, createdSighting);

        //Act
        List<Hero> heroList = heroService.retrieveHeroBySighting(createdSighting, Integer.MAX_VALUE, 0);

        //Assert
        assertHeroesBySighting(numberOfHeroes, heroList, createdSighting);
    }

    @Test
    public void retrieveHeroBySightingPage() {

        //Retrieve all heroes by sighting (starbucks for example)

        //Arrange
        //number of heroes
        int numberOfHeroes = 1;

        //create location
        Location createdLocation = createLocation();

        //create sighting
        Sighting createdSighting = createSighting(createdLocation);

        //create two heroes
        Hero createdHero = createHero();
        Hero createdHeroTwo = createHero();

        //create two hero sighting
        createHeroSighting(createdHero, createdSighting);
        createHeroSighting(createdHeroTwo, createdSighting);

        //Act
        List<Hero> heroList = heroService.retrieveHeroBySighting(createdSighting, 1, 0);

        //Assert
        assertHeroesBySighting(numberOfHeroes, heroList, createdSighting);
    }

    @Test
    public void retrieveHeroByOrganization() {

        //Retrieve all heroes by organization

        //Arrange
        //number of heroes
        int numberOfHeroes = 2;

        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        //create two heroes
        Hero createdHero = createHero();
        Hero createdHeroTwo = createHero();

        //create two hero organizations
        createdHeroOrganization(createdHero, createdOrganization);
        createdHeroOrganization(createdHeroTwo, createdOrganization);

        //Act
        List<Hero> heroList = heroService.retrieveHeroByOrganization(createdOrganization, Integer.MAX_VALUE, 0);

        //Assert
        assertHeroesByOrganization(numberOfHeroes, heroList, createdOrganization);
    }

    @Test
    public void retrieveHeroByOrganizationPage() {

        //Retrieve all heroes by organization

        //Arrange
        //number of heroes
        int numberOfHeroes = 1;

        Location createdLocation = createLocation();
        Organization createdOrganization = createOrganization(createdLocation);

        //create two heroes
        Hero createdHero = createHero();
        Hero createdHeroTwo = createHero();

        //create two hero organizations
        createdHeroOrganization(createdHero, createdOrganization);
        createdHeroOrganization(createdHeroTwo, createdOrganization);

        //Act
        List<Hero> heroList = heroService.retrieveHeroByOrganization(createdOrganization, 1, 0);

        //Assert
        assertHeroesByOrganization(numberOfHeroes, heroList, createdOrganization);
    }

    //Helper Methods
    private Hero createHero() {

        //Arrange and Act
        Hero hero = new Hero();
        hero.setName("Wolverine");
        hero.setDescription("A beast with extremely sharp claws");

        //Act
        return heroService.addHero(hero);
    }

    private Power createPower() {

        //Arrange
        Power power = new Power();
        power.setName("Fly");
        power.setDescription("The hero is able to fly");

        //Act
        powerService.addPower(power);
        return power;
    }

    private HeroPower createHeroPower(Hero createdHero, Power createdPower) {
        HeroPower heroPower = new HeroPower();

        heroPower.setHero(createdHero);
        heroPower.setPower(createdPower);

        heroPowerService.addHeroPower(heroPower);

        return heroPower;
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

    private Sighting createSighting(Location location) {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sighting.setDescription("It's a bird, it's a plane, it's just Bryan");
        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        return sighting;
    }

    private HeroSighting createHeroSighting(Hero createdHero, Sighting createdSighting) {
        HeroSighting heroSighting = new HeroSighting();

        heroSighting.setHero(createdHero);
        heroSighting.setSighting(createdSighting);

        heroSightingService.addHeroSighting(heroSighting);
        return heroSighting;
    }

    private Organization createOrganization(Location location) {

        //Arrange
        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("League of extraordinary people");
        organization.setLocation(location);

        //Act
        organizationService.addOrganization(organization);
        return organization;
    }

    private HeroOrganization createdHeroOrganization(Hero createdHero, Organization createdOrganization) {
        HeroOrganization heroOrganization = new HeroOrganization();
        heroOrganization.setHero(createdHero);
        heroOrganization.setOrganization(createdOrganization);

        heroOrganizationService.addHeroOrganization(heroOrganization);
        return heroOrganization;
    }

    private void assertHeroesByPower(int numberOfHeroes, List<Hero> heroList, Power power) {

        assert heroList.size() == numberOfHeroes;
        //for each hero in heroList
        for (Hero hero : heroList) {
            //retrieve hero powers
            List<Power> heroPowers = powerService.retrievePowerByHero(hero, Integer.MAX_VALUE, 0);

            boolean containsPower = false;
            //for each power in heroPowers
            for (Power pow : heroPowers) {
                //make sure the power we added is contained contained in the list
                if (pow.getPowerId().equals(power.getPowerId())) containsPower = true;
            }
            assert containsPower == true;
        }
    }

    private void assertHeroesBySighting(int numberOfHeroes, List<Hero> heroList, Sighting sighting) {

        assert heroList.size() == numberOfHeroes;
        //for each hero in heroList
        for (Hero hero : heroList) {
            //retrieve hero sightings
            List<Sighting> heroSightings = sightingService.retrieveSightingByHero(hero, Integer.MAX_VALUE, 0);

            boolean containsSighting = false;
            //for each sighting in heroSightings
            for (Sighting sight : heroSightings) {
                //make sure the sighting we added is contained contained in the list
                if (sight.getSightingId().equals(sighting.getSightingId())) containsSighting = true;
            }
            assert containsSighting == true;
        }
    }

    private void assertHeroesByOrganization(int numberOfHeroes, List<Hero> heroList, Organization organization) {

        assert heroList.size() == numberOfHeroes;
        //for each hero in heroList
        for (Hero hero : heroList) {
            //retrieve hero sightings
            List<Organization> heroOrganizations = organizationService.retrieveOrganizationByHero(hero, Integer.MAX_VALUE, 0);

            boolean containsOrganization = false;
            //for each sighting in heroOrganization
            for (Organization org : heroOrganizations) {
                //make sure the organization we added is contained in the list
                if (org.getOrganizationId().equals(organization.getOrganizationId())) containsOrganization = true;
            }
            assert containsOrganization == true;
        }
    }

}