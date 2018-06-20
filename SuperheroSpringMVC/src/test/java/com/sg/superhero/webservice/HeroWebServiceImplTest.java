package com.sg.superhero.webservice;

import com.sg.dto.*;
import com.sg.model.commandmodel.hero.edithero.HeroEditCommandModel;
import com.sg.model.commandmodel.hero.heropage.HeroPageCreateCommandModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsOrganizationViewModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsPowerViewModel;
import com.sg.model.viewmodel.hero.detailshero.HeroDetailsViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditOrganizationViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditPowerViewModel;
import com.sg.model.viewmodel.hero.edithero.HeroEditViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPageOrganizationViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPagePowerViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroPageViewModel;
import com.sg.model.viewmodel.hero.heropage.HeroViewModel;
import com.sg.service.*;
import com.sg.superhero.webservice.util.TestDataHelperWebService;
import com.sg.webservice.HeroWebService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class HeroWebServiceImplTest {

    @Inject
    TestDataHelperWebService testData;

    @Inject
    HeroPowerService heroPowerService;

    @Inject
    HeroOrganizationService heroOrganizationService;

    @Inject
    HeroWebService heroWebService;

    @Inject
    PowerService powerService;

    @Inject
    OrganizationService organizationService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveHeroPageViewModel() {

        //Arrange
        Location location = testData.createTestLocation();
        List<Hero> heroList = testData.createTestHeroes(3);
        List<Power> powerList = testData.createTestPowers(3);
        List<Organization> organizationList = testData.createTestOrganizations(3, location);

        //Act
        HeroPageViewModel heroPageViewModel = heroWebService.retrieveHeroPageViewModel(Integer.MAX_VALUE, 0, 1);

        //Assert
        assertEquals(heroList.size(), heroPageViewModel.getHeroes().size());
        assertEquals(powerList.size(), heroPageViewModel.getPowers().size());
        assertEquals(organizationList.size(), heroPageViewModel.getOrganizations().size());
        //Object is present
        assertNotNull(heroPageViewModel.getHeroPageCreateCommandModel());

        //assert for ID and Name
        for(HeroViewModel hvm : heroPageViewModel.getHeroes()) {
            hvm.getId();
            hvm.getName();
        }

        for(HeroPagePowerViewModel hppvm : heroPageViewModel.getPowers()) {
            hppvm.getId();
            hppvm.getName();
        }

        for(HeroPageOrganizationViewModel hpovm : heroPageViewModel.getOrganizations()) {
            hpovm.getId();
            hpovm.getName();
        }
    }

    @Test
    public void retrieveHeroPageViewModelPaging() {

        //Arrange

        //number of heroes it 3, but we created 12. Assert we only get 3
        int numberOfHeroes = 3;

        Location location = testData.createTestLocation();
        List<Hero> heroList = testData.createTestHeroes(12);
        List<Power> powerList = testData.createTestPowers(3);
        List<Organization> organizationList = testData.createTestOrganizations(3, location);

        //Act
        HeroPageViewModel heroPageViewModel = heroWebService.retrieveHeroPageViewModel(numberOfHeroes, 0, 1);

        //Assert
        assertEquals(numberOfHeroes, heroPageViewModel.getHeroes().size());
        assertEquals(powerList.size(), heroPageViewModel.getPowers().size());
        assertEquals(organizationList.size(), heroPageViewModel.getOrganizations().size());
        //Object is present
        assertNotNull(heroPageViewModel.getHeroPageCreateCommandModel());

        //assert for ID and Name
        for(HeroViewModel hvm : heroPageViewModel.getHeroes()) {
            hvm.getId();
            hvm.getName();
        }

        for(HeroPagePowerViewModel hppvm : heroPageViewModel.getPowers()) {
            hppvm.getId();
            hppvm.getName();
        }

        for(HeroPageOrganizationViewModel hpovm : heroPageViewModel.getOrganizations()) {
            hpovm.getId();
            hpovm.getName();
        }
    }

    @Test
    public void saveHeroPageCreateCommandModel() {

        //Arrange
        List<Power> powerList = testData.createTestPowers(2);
        Long[] powerIds = new Long[2];
        powerIds[0] = powerList.get(0).getPowerId();
        powerIds[1] = powerList.get(1).getPowerId();

        //pass location into organization
        Location location = testData.createTestLocation();
        List<Organization> organizationList = testData.createTestOrganizations(2, location);
        Long[] organizationIds = new Long[2];
        organizationIds[0] = organizationList.get(0).getOrganizationId();
        organizationIds[1] = organizationList.get(1).getOrganizationId();

        //Instantiate the command model
        HeroPageCreateCommandModel heroPageCreateCommandModel = new HeroPageCreateCommandModel();

        //set data on the command model
        heroPageCreateCommandModel.setName("Batman");
        heroPageCreateCommandModel.setDescription("Batman lives in Gotham City");
        heroPageCreateCommandModel.setPowerIds(powerIds);
        heroPageCreateCommandModel.setOrganizationIds(organizationIds);

        //Act
        //save hero
        Hero createdHero = heroWebService.saveHeroPageCreateCommandModel(heroPageCreateCommandModel);

        //Assert
        assertNotNull(createdHero.getHeroId());
        assertEquals(heroPageCreateCommandModel.getName(), createdHero.getName());
        assertEquals(heroPageCreateCommandModel.getDescription(), createdHero.getDescription());

        //Assert for Powers
        List<Power> powers = powerService.retrievePowerByHero(createdHero, Integer.MAX_VALUE, 0);
        boolean savedPowerOne = false;
        boolean savedPowerTwo = false;

        for(Power power : powers) {
            if(power.getPowerId().equals(powerList.get(0).getPowerId())) savedPowerOne = true;
            if(power.getPowerId().equals(powerList.get(1).getPowerId())) savedPowerTwo = true;
        }
        assertTrue(savedPowerOne);
        assertTrue(savedPowerTwo);

        //Assert for Organizations
        List<Organization> organizations = organizationService.retrieveOrganizationByHero(createdHero, Integer.MAX_VALUE, 0);
        boolean savedOrganizationOne = false;
        boolean savedOrganizationTwo = false;

        for(Organization organization : organizations) {
            if(organization.getOrganizationId().equals(organizationList.get(0).getOrganizationId())) savedOrganizationOne = true;
            if(organization.getOrganizationId().equals(organizationList.get(1).getOrganizationId())) savedOrganizationTwo = true;
        }
        assertTrue(savedOrganizationOne);
        assertTrue(savedOrganizationTwo);
    }

    @Test
    public void retrieveHeroEditViewModel() {

        //Arrange
        List<Power> powerList = testData.createTestPowers(2);

        //Pass location in
        Location location = testData.createTestLocation();
        List<Organization> organizationList = testData.createTestOrganizations(2, location);

        //Selected Power
        List<Power> selectedPowers = new ArrayList<>();
        selectedPowers.add(powerList.get(0));
        selectedPowers.add(powerList.get(1));

        //Selected Organization
        List<Organization> selectedOrganization = new ArrayList<>();
        selectedOrganization.add(organizationList.get(0));
        selectedOrganization.add(organizationList.get(1));

        Hero existingHero = testData.createTestHeroWithPowerAndOrganization(selectedPowers, selectedOrganization);

        //Act
        HeroEditViewModel heroEditViewModel = heroWebService.retrieveHeroEditViewModel(existingHero.getHeroId());

        //Assert
        assertEquals(heroEditViewModel.getOrganizations().size(), organizationList.size());
        assertEquals(heroEditViewModel.getPowers().size(), powerList.size());

        //Assert HeroViewModel ID and Name

        for(HeroEditPowerViewModel heroEditPowerViewModel : heroEditViewModel.getPowers()) {
            assertNotNull(heroEditPowerViewModel.getId());
            assertNotNull(heroEditPowerViewModel.getName());
        }

        for(HeroEditOrganizationViewModel heroEditOrganizationViewModel : heroEditViewModel.getOrganizations()) {
            assertNotNull(heroEditOrganizationViewModel.getId());
            assertNotNull(heroEditOrganizationViewModel.getName());
        }

        //Command Model
        HeroEditCommandModel heroEditCommandModel = heroEditViewModel.getHeroEditCommandModel();

        assertEquals(heroEditCommandModel.getId(), existingHero.getHeroId());
        assertEquals(heroEditCommandModel.getDescription(), existingHero.getDescription());
        assertEquals(heroEditCommandModel.getName(), existingHero.getName());

        //Boolean checks on IDs Power and Organization

        boolean containsFirstOrganization = false;
        boolean containsSecondOrganization = false;
        for(Long organizationId : heroEditCommandModel.getOrganizationIds()) {
            if(organizationId.equals(organizationList.get(0).getOrganizationId())) containsFirstOrganization = true;
            if(organizationId.equals(organizationList.get(1).getOrganizationId())) containsSecondOrganization = true;
        }
        assertTrue(containsFirstOrganization);
        assertTrue(containsSecondOrganization);

        boolean containsFirstPower = false;
        boolean containsSecondPower = false;
        for(Long powerId : heroEditCommandModel.getPowerIds()) {
            if(powerId.equals(powerList.get(0).getPowerId())) containsFirstPower = true;
            if(powerId.equals(powerList.get(1).getPowerId())) containsSecondPower = true;
        }
        assertTrue(containsFirstPower);
        assertTrue(containsSecondPower);
    }

    @Test
    public void saveHeroEditCommandModel() {

        //Arrange
        List<Power> powerList = testData.createTestPowers(2);

        //Pass location in
        Location location = testData.createTestLocation();
        List<Organization> organizationList = testData.createTestOrganizations(2, location);

        //create hero with powers and organizations
        Hero existingHero = testData.createTestHeroWithPowerAndOrganization(powerList, organizationList);

        //Instantiate the command model
        HeroEditCommandModel heroEditCommandModel =  new HeroEditCommandModel();

        heroEditCommandModel.setId(existingHero.getHeroId());
        heroEditCommandModel.setName("The Human Spider");
        heroEditCommandModel.setDescription("A human that is also a spider");

        //Different Powers
        List<Power> updatedPowers = testData.createListOfSecondTestPowers(2);

        Long[] powerIds = new Long[2];
        powerIds[0] = updatedPowers.get(0).getPowerId();
        powerIds[1] = updatedPowers.get(1).getPowerId();

        //Different Organizations
        List<Organization> updatedOrganizations = testData.createListOfSecondTestOrganizations(2, location);

        Long[] organizationIds = new Long[2];
        organizationIds[0] = updatedOrganizations.get(0).getOrganizationId();
        organizationIds[1] = updatedOrganizations.get(1).getOrganizationId();

        //Set on command model
        heroEditCommandModel.setPowerIds(powerIds);
        heroEditCommandModel.setOrganizationIds(organizationIds);

        //Act
        Hero updatedHero = heroWebService.saveHeroEditCommandModel(heroEditCommandModel);

        //Assert
        assertNotNull(updatedHero.getHeroId());
        assertEquals(updatedHero.getHeroId(), existingHero.getHeroId());
        assertEquals("The Human Spider", updatedHero.getName());
        assertEquals("A human that is also a spider", updatedHero.getDescription());

        List<Power> powers = powerService.retrievePowerByHero(updatedHero, Integer.MAX_VALUE, 0);
        boolean savedFirstPower = false;
        boolean savedSecondPower = false;
        boolean deletedFirstPower = true;

        for(Power power : powers) {
            if(power.getPowerId().equals(updatedPowers.get(0).getPowerId())) savedFirstPower = true;
            if(power.getPowerId().equals(updatedPowers.get(1).getPowerId())) savedSecondPower = true;
            if(power.getPowerId().equals(powerList.get(0).getPowerId())) deletedFirstPower = false;
        }
        assertTrue(savedFirstPower);
        assertTrue(savedSecondPower);
        assertTrue(deletedFirstPower);

        List<Organization> organizations = organizationService.retrieveOrganizationByHero(updatedHero, Integer.MAX_VALUE, 0);
        boolean savedFirstOrganization = false;
        boolean savedSecondOrganization = false;
        boolean deletedFirstOrganization = true;

        for(Organization organization : organizations) {
            if(organization.getOrganizationId().equals(updatedOrganizations.get(0).getOrganizationId())) savedFirstOrganization = true;
            if(organization.getOrganizationId().equals(updatedOrganizations.get(1).getOrganizationId())) savedSecondOrganization = true;
            if(organization.getOrganizationId().equals(organizationList.get(0).getOrganizationId())) deletedFirstOrganization = false;
        }
        assertTrue(savedFirstOrganization);
        assertTrue(savedSecondOrganization);
        assertTrue(deletedFirstOrganization);
    }

    @Test
    public void retrieveHeroDetailsViewModel() {

        //Create Location
        Location createdLocation = testData.createTestLocation();

        //Create Organization
        List<Organization> organizationList = testData.createTestOrganizations(2, createdLocation);

        //Create Power
        List<Power> powerList = testData.createTestPowers(2);

        //Associate with Hero
        Hero createdHero = testData.createTestHero();

        for(Power eachPower : powerList) {
            HeroPower heroPower = new HeroPower();
            heroPower.setHero(createdHero);
            heroPower.setPower(eachPower);
            heroPowerService.addHeroPower(heroPower);
        }
        for(Organization eachOrganization : organizationList) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHero(createdHero);
            heroOrganization.setOrganization(eachOrganization);
            heroOrganizationService.addHeroOrganization(heroOrganization);
        }

        //Act
        HeroDetailsViewModel heroDetailsViewModel = heroWebService.retrieveHeroDetailsViewModel(createdHero.getHeroId());

        //Assert
        assertEquals(heroDetailsViewModel.getId(), createdHero.getHeroId());
        assertEquals(heroDetailsViewModel.getName(), createdHero.getName());
        assertEquals(heroDetailsViewModel.getDescription(), createdHero.getDescription());

        boolean containsFirstPower = false;
        boolean containsSecondPower = false;
        for(HeroDetailsPowerViewModel heroDetailsPowerViewModel : heroDetailsViewModel.getPowers()){
            if("Fly0".equals(heroDetailsPowerViewModel.getName())) containsFirstPower = true;
            if("Fly1".equals(heroDetailsPowerViewModel.getName())) containsSecondPower = true;
        }
        assertTrue(containsFirstPower);
        assertTrue(containsSecondPower);

        boolean containsFirstOrganization = false;
        boolean containsSecondOrganization = false;
        for(HeroDetailsOrganizationViewModel heroDetailsOrganizationViewModel : heroDetailsViewModel.getOrganizations()){
            if("Avengers0".equals(heroDetailsOrganizationViewModel.getName())) containsFirstOrganization = true;
            if("Avengers1".equals(heroDetailsOrganizationViewModel.getName())) containsSecondOrganization = true;
        }
        assertTrue(containsFirstOrganization);
        assertTrue(containsSecondOrganization);
    }
}