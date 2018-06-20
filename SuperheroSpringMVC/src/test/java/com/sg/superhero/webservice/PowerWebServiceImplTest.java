package com.sg.superhero.webservice;

import com.sg.dto.Power;
import com.sg.model.commandmodel.power.editpower.PowerEditCommandModel;
import com.sg.model.commandmodel.power.powerpage.PowerPageCreateCommandModel;
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.model.viewmodel.power.editpower.PowerEditViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerPageViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerViewModel;
import com.sg.superhero.webservice.util.TestDataHelperWebService;
import com.sg.webservice.PowerWebService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-applicationContext.xml"})
@Rollback
@Transactional
public class PowerWebServiceImplTest {

    @Inject
    PowerWebService powerWebService;

    @Inject
    TestDataHelperWebService testDataHelperWebService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrievePowerPageViewModel() {
        //Testing we can retrieve the Power Page View Model

        //Arrange
        //Create a list of powers
        List<Power> powerList = testDataHelperWebService.createTestPowers(10);

        //Act
        PowerPageViewModel powerPageViewModel = powerWebService.retrievePowerPageViewModel(Integer.MAX_VALUE, 0, 1);

        //Assert
        assertEquals(powerList.size(), powerPageViewModel.getPowers().size());
        //Ensure the createCommandModel object is present even though fields are not populated
        assertNotNull(powerPageViewModel.getPowerPageCreateCommandModel());

        //For each pvm in Power Page View Model assert ID and Name not null
        //pvm is contained in the PowerPageViewModel list in power page DTO
        for(PowerViewModel pvm : powerPageViewModel.getPowers()) {
            assertNotNull(pvm.getId());
            assertNotNull(pvm.getName());
        }
    }

    @Test
    public void retrievePowerPageViewModelPaging() {
        //Testing we can retrieve the Power Page View Model

        int numberOfPowers = 3;
        //Arrange
        //Create a list of powers
        List<Power> powerList = testDataHelperWebService.createTestPowers(10);

        //Act
        PowerPageViewModel powerPageViewModel = powerWebService.retrievePowerPageViewModel(numberOfPowers, 0, 1);

        //Assert
        assertEquals(numberOfPowers, powerPageViewModel.getPowers().size());
        //Ensure the createCommandModel object is present even though fields are not populated
        assertNotNull(powerPageViewModel.getPowerPageCreateCommandModel());

        //For each pvm in Power Page View Model assert ID and Name not null
        //pvm is contained in the PowerPageViewModel list in power page DTO
        for(PowerViewModel pvm : powerPageViewModel.getPowers()) {
            assertNotNull(pvm.getId());
            assertNotNull(pvm.getName());
        }
    }

    @Test
    public void retrievePowerEditViewModel() {
        //Testing we can retrieve a Power Edit View Model for edit upon the user clicking edit on home page

        //Arrange
        //Create a power
        Power createdPower = testDataHelperWebService.createTestPower();

        //Act
        //Ensure when user clicks edit we get the powerEditViewModel of the createdPower Id
        PowerEditViewModel powerEditViewModel = powerWebService.retrievePowerEditViewModel(createdPower.getPowerId());

        //Assert
        assertNotNull(powerEditViewModel.getPowerEditCommandModel().getPowerId());
        assertEquals("Fly", powerEditViewModel.getPowerEditCommandModel().getName());
        assertEquals("Hero can fly around the world", powerEditViewModel.getPowerEditCommandModel().getDescription());
    }

    @Test
    public void savePowerEditCommandModel() {
        //Testing we can edit an existing power and save

        //Arrange
        //Create a power
        Power createdPower = testDataHelperWebService.createTestPower();

        PowerEditCommandModel powerEditCommandModel = new PowerEditCommandModel();
        powerEditCommandModel.setPowerId(createdPower.getPowerId());
        powerEditCommandModel.setName("Strong");
        powerEditCommandModel.setDescription("Hero has super strength");

        //Act
        Power updatedPower = powerWebService.savePowerEditCommandModel(powerEditCommandModel);

        //Assert
        //ID should not have changed - Just updated fields
        assertEquals(createdPower.getPowerId(), updatedPower.getPowerId());
        assertEquals("Strong", updatedPower.getName());
        assertEquals("Hero has super strength", updatedPower.getDescription());
    }

    @Test
    public void savePowerPageCreateCommandModel() {
        //Testing we can save Power Page Create Command Model after user hit create

        //Arrange
        //Instantiate the command model
        PowerPageCreateCommandModel powerPageCreateCommandModel = new PowerPageCreateCommandModel();
        //Build out command model as if it were user
        powerPageCreateCommandModel.setName("Fly");
        powerPageCreateCommandModel.setDescription("Hero can fly around the world");

        //Act
        Power createdPower = powerWebService.savePowerPageCreateCommandModel(powerPageCreateCommandModel);

        //Assert
        assertNotNull(createdPower.getPowerId());
        assertEquals("Fly", createdPower.getName());
        assertEquals("Hero can fly around the world", createdPower.getDescription());
    }

    @Test
    public void retrievePowerDetailsViewModel() {
        //Testing that we can retrieve a Power Details View Model when user clicks a power on home page

        //Arrange
        //Create a power
        Power createdPower = testDataHelperWebService.createTestPower();

        //Act
        PowerDetailsViewModel powerDetailsViewModel = powerWebService.retrievePowerDetailsViewModel(createdPower.getPowerId());

        //Assert
        assertEquals(powerDetailsViewModel.getId(), createdPower.getPowerId());
        assertEquals("Fly", powerDetailsViewModel.getName());
        assertEquals("Hero can fly around the world", powerDetailsViewModel.getDescription());
    }
}