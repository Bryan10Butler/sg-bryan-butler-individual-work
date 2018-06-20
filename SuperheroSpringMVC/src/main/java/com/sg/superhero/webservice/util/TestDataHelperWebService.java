package com.sg.superhero.webservice.util;

import com.sg.dto.*;
import com.sg.service.*;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class TestDataHelperWebService {

    LocationService locationService;

    OrganizationService organizationService;

    PowerService powerService;

    SightingService sightingService;

    HeroService heroService;

    HeroSightingService heroSightingService;

    HeroPowerService heroPowerService;

    HeroOrganizationService heroOrganizationService;

    @Inject
    public TestDataHelperWebService(LocationService locationService, OrganizationService organizationService, PowerService powerService,
                                    HeroService heroService, SightingService sightingService,
                                    HeroSightingService heroSightingService, HeroPowerService heroPowerService,
                                    HeroOrganizationService heroOrganizationService) {
        this.locationService = locationService;
        this.organizationService = organizationService;
        this.powerService = powerService;
        this.heroService = heroService;
        this.sightingService = sightingService;
        this.heroSightingService = heroSightingService;
        this.heroPowerService = heroPowerService;
        this.heroOrganizationService = heroOrganizationService;
    }

    //LOCATION HELPER METHODS
    //Create one location
    public Location createTestLocation() {

        Location location = new Location();
        location.setName("Heroes Inc");
        location.setDescription("Tall Building");
        location.setCity("Boston");
        location.setState("MA");
        location.setStreet("100 Hero Ave");
        location.setZip("01234");
        location.setLatitude("23.10");
        location.setLongitude("45.50");

        return locationService.addLocation(location);
    }

    //Create multiple test locations
    public List<Location> createTestLocations(int numberOfLocations) {

        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < numberOfLocations; i++) {

            Location location = new Location();
            location.setName("Heroes Inc" + i);
            location.setDescription("Tall Building");
            location.setCity("Boston");
            location.setState("MA");
            location.setStreet("100 Hero Ave");
            location.setZip("01234");
            location.setLatitude("23.10");
            location.setLongitude("45.50");

            Location createdLocation = locationService.addLocation(location);
            locations.add(createdLocation);

        }
        return locations;
    }

    //ORGANIZATION HELPER METHODS
    //Create one organization
    public Organization createTestOrganization(Location location) {

        Organization organization = new Organization();
        organization.setName("Avengers");
        organization.setDescription("League of extraordinary people");

        organization.setLocation(location);

        return organizationService.addOrganization(organization);
    }

    //Create multiple organizations
    public List<Organization> createTestOrganizations(int numberOfOrganizations, Location location) {

        List<Organization> organizationList = new ArrayList<>();

        for (int i = 0; i < numberOfOrganizations; i++) {

            Organization organization = new Organization();
            organization.setName("Avengers" + i);
            organization.setDescription("League of extraordinary people" + i);

            organization.setLocation(location);

            Organization createdOrganization = organizationService.addOrganization(organization);

            organizationList.add(createdOrganization);
        }
        return organizationList;
    }

    //Create multiple organizations
    public List<Organization> createListOfSecondTestOrganizations(int numberOfOrganizations, Location location) {

        List<Organization> organizationList = new ArrayList<>();

        for (int i = 0; i < numberOfOrganizations; i++) {

            Organization organization = new Organization();
            organization.setName("The League" + i);
            organization.setDescription("Some really awesome people" + i);

            organization.setLocation(location);

            Organization createdOrganization = organizationService.addOrganization(organization);

            organizationList.add(createdOrganization);
        }
        return organizationList;
    }

    //POWER HELPER METHODS
    //Create one power
    public Power createTestPower() {

        Power power = new Power();

        power.setName("Fly");
        power.setDescription("Hero can fly around the world");

        return powerService.addPower(power);
    }

    //Create multiple powers
    public List<Power> createTestPowers(int numberOfPowers) {

        //Create a power list
        List<Power> powerList = new ArrayList<>();

        for (int i = 0; i < numberOfPowers; i++) {

            Power power = new Power();

            power.setName("Fly" + i);
            power.setDescription("Hero can fly around the world" +i);

            Power createdPower = powerService.addPower(power);

            //Add to the list of powers for however many passed in
            powerList.add(createdPower);

        }
        return powerList;
    }

    //Create multiple powers
    public List<Power> createListOfSecondTestPowers(int numberOfPowers) {

        //Create a power list
        List<Power> powerList = new ArrayList<>();

        for (int i = 0; i < numberOfPowers; i++) {

            Power power = new Power();

            power.setName("Laser Eye" + i);
            power.setDescription("Can shoot laser beams from eyes");

            Power createdPower = powerService.addPower(power);

            //Add to the list of powers for however many passed in
            powerList.add(createdPower);

        }
        return powerList;
    }

    public Sighting createTestSighting(Location location) {
        Sighting sighting = new Sighting();
        sighting.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
        sighting.setDescription("It's a bird, it's a plane, it's just Bryan");
        sighting.setLocation(location);
        sightingService.addSighting(sighting);
        return sighting;
    }

    public List<Sighting> createTestSightings(int numberOfSightings, Location location) {

        //Create a sighing list
        List<Sighting> sightingList = new ArrayList<>();

        for (int i = 0; i < numberOfSightings; i++) {

            Sighting sighting = new Sighting();
            sighting.setDate(LocalDate.parse("2019-04-04", DateTimeFormatter.ISO_DATE));
            sighting.setDescription("It's a bird, it's a plane, it's just Bryan");
            sighting.setLocation(location);
            sightingService.addSighting(sighting);

            //Add to the listing of sightings
            sightingList.add(sighting);
        }
        return sightingList;
    }

    public Hero createTestHero() {

        //Arrange and Act
        Hero hero = new Hero();
        hero.setName("Wolverine");
        hero.setDescription("A beast with extremely sharp claws");

        //Act
        return heroService.addHero(hero);
    }

    public List<Hero> createTestHeroes(int numberOfHeroes) {
        //Create a hero list
        List<Hero> heroList = new ArrayList<>();

        for (int i = 0; i < numberOfHeroes; i++) {

            Hero hero = new Hero();
            hero.setName("Wolverine" + i);
            hero.setDescription("A beast with extremely sharp claws");
            heroService.addHero(hero);

            heroList.add(hero);
        }
        return heroList;
    }

    public List<Hero> createTestSecondListOfTestHeroes(int numberOfHeroes) {
        //Create a hero list
        List<Hero> heroList = new ArrayList<>();

        for (int i = 0; i < numberOfHeroes; i++) {

            Hero hero = new Hero();
            hero.setName("Loki" + i);
            hero.setDescription("Thor's brother... Or is he?!");
            heroService.addHero(hero);

            heroList.add(hero);
        }
        return heroList;
    }

    public Sighting createTestSightingWithHeroAndLocation(Location createdLocation, List<Hero> createdHeroes) {
        Sighting existingSighting = createTestSighting(createdLocation);

        for (Hero hero : createdHeroes) {
            HeroSighting heroSighting = new HeroSighting();
            heroSighting.setHero(hero);
            heroSighting.setSighting(existingSighting);
            heroSightingService.addHeroSighting(heroSighting);
        }
        return existingSighting;
    }

    public Hero createTestHeroWithPowerAndOrganization(List<Power> createdPowers, List<Organization> createdOrganizations) {
        Hero existingHero = createTestHero();

        for (Power power : createdPowers) {
            HeroPower heroPower = new HeroPower();
            heroPower.setHero(existingHero);
            heroPower.setPower(power);
            heroPowerService.addHeroPower(heroPower);
        }
        for (Organization organization : createdOrganizations) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHero(existingHero);
            heroOrganization.setOrganization(organization);
            heroOrganizationService.addHeroOrganization(heroOrganization);
        }
        return existingHero;
    }

}

