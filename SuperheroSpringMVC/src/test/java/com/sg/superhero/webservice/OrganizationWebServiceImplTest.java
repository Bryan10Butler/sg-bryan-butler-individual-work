package com.sg.superhero.webservice;

import com.sg.dto.Location;
import com.sg.dto.Organization;
import com.sg.model.commandmodel.organization.editorganization.OrganizationEditCommandModel;
import com.sg.model.commandmodel.organization.organizationpage.OrganizationPageCreateCommandModel;
import com.sg.model.viewmodel.organization.detailsorganization.OrganizationDetailsViewModel;
import com.sg.model.viewmodel.organization.editorganization.OrganizationEditViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageLocationViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationPageViewModel;
import com.sg.model.viewmodel.organization.organizationpage.OrganizationViewModel;
import com.sg.superhero.webservice.util.TestDataHelperWebService;
import com.sg.webservice.OrganizationWebService;
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
public class OrganizationWebServiceImplTest {

    @Inject
    OrganizationWebService organizationWebService;

    @Inject
    TestDataHelperWebService testData;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void retrieveOrganizationPageViewModel() {

        //Testing we can retrieve the organization page view model

        //Arrange
        List<Location> locationList = testData.createTestLocations(12);
        List<Organization> organizationList = testData.createTestOrganizations(12, locationList.get(0));

        //Act
        OrganizationPageViewModel organizationPageViewModel = organizationWebService.retrieveOrganizationPageViewModel(Integer.MAX_VALUE, 0, 1);

        //Assert
        assertEquals(organizationList.size(), organizationPageViewModel.getOrganizations().size());
        assertEquals(locationList.size(), organizationPageViewModel.getLocations().size());
        assertNotNull(organizationPageViewModel.getOrganizationPageCreateCommandModel());

        for(OrganizationViewModel ovm : organizationPageViewModel.getOrganizations()) {
            assertNotNull(ovm.getId());
            assertNotNull(ovm.getName());
        }

        for(OrganizationPageLocationViewModel lvm : organizationPageViewModel.getLocations() ) {
            assertNotNull(lvm.getId());
            assertNotNull(lvm.getName());
        }
    }

    @Test
    public void retrieveOrganizationPageViewModelPaging() {

        //Testing we can retrieve the organization page view model

        int numberOfOrganization = 3;

        //Arrange
        List<Location> locationList = testData.createTestLocations(12);
        List<Organization> organizationList = testData.createTestOrganizations(12, locationList.get(0));

        //Act
        OrganizationPageViewModel organizationPageViewModel = organizationWebService.retrieveOrganizationPageViewModel(numberOfOrganization, 0, 1);

        //Assert
        assertEquals(numberOfOrganization, organizationPageViewModel.getOrganizations().size());
        //assertEquals(locationList.size(), organizationPageViewModel.getLocations().size());
        assertNotNull(organizationPageViewModel.getOrganizationPageCreateCommandModel());

        for(OrganizationViewModel ovm : organizationPageViewModel.getOrganizations()) {
            assertNotNull(ovm.getId());
            assertNotNull(ovm.getName());
        }

        for(OrganizationPageLocationViewModel lvm : organizationPageViewModel.getLocations() ) {
            assertNotNull(lvm.getId());
            assertNotNull(lvm.getName());
        }
    }

    @Test
    public void saveOrganizationPageCreateCommandModel() {

        //Testing we can save the Organization Page Create Command Model (User clicks save)

        //Arrange
        //Instantiate the model
        Location createdLocation = testData.createTestLocation();
        OrganizationPageCreateCommandModel commandModel = new OrganizationPageCreateCommandModel();
        commandModel.setName("Marvel Inc");
        commandModel.setDescription("A bunch of awesome people");
        commandModel.setLocationId(createdLocation.getLocationId());

        //Act
        Organization createdOrganization = organizationWebService.saveOrganizationPageCreateCommandModel(commandModel);

        //Assert
        assertNotNull(createdOrganization.getOrganizationId());
        assertEquals("Marvel Inc", createdOrganization.getName());
        assertEquals("A bunch of awesome people", createdOrganization.getDescription());
        assertEquals(createdLocation.getLocationId(), createdOrganization.getLocation().getLocationId());
    }

    @Test
    public void retrieveOrganizationEditViewModel() {

        //Arrange
        List<Location> location = testData.createTestLocations(12);
        Organization organization = testData.createTestOrganization(location.get(0));

        //Act
        OrganizationEditViewModel viewModel = organizationWebService.retrieveOrganizationEditViewModel(organization.getOrganizationId());

        //Assert
        assertEquals(location.size(), viewModel.getLocations().size());
        assertEquals(organization.getName(), viewModel.getOrganizationEditCommandModel().getName());
        assertEquals(organization.getDescription(), viewModel.getOrganizationEditCommandModel().getDescription());
        assertEquals(organization.getLocation().getLocationId(), viewModel.getOrganizationEditCommandModel().getLocationId());
        assertNotNull(viewModel.getOrganizationEditCommandModel().getOrganizationId());
    }

    @Test
    public void saveOrganizationEditCommandModel() {

        Location location = testData.createTestLocation();

        //Arrange
        Organization organization = testData.createTestOrganization(location);
        Location updatedLocation = testData.createTestLocation();

        OrganizationEditCommandModel commandModel = new OrganizationEditCommandModel();
        commandModel.setOrganizationId(organization.getOrganizationId());
        commandModel.setName("Avengers Inc");
        commandModel.setDescription("A group of fantastic people");
        commandModel.setLocationId(updatedLocation.getLocationId());

        //Act
        Organization updatedOrganization = organizationWebService.saveOrganizationEditCommandModel(commandModel);

        //Assert
        assertEquals("Avengers Inc", updatedOrganization.getName());
        assertEquals("A group of fantastic people", updatedOrganization.getDescription());
        assertEquals(organization.getOrganizationId(), updatedOrganization.getOrganizationId());
        assertEquals(updatedLocation.getLocationId(), updatedOrganization.getLocation().getLocationId());

    }

    @Test
    public void retrieveOrganizationDetailsViewModel() {

        Location location = testData.createTestLocation();

        //Arrange
        Organization organization = testData.createTestOrganization(location);

        //Assert
        OrganizationDetailsViewModel organizationDetailsViewModel = organizationWebService.retrieveOrganizationDetailsViewModel(organization.getOrganizationId());

        //Act
        assertEquals(organization.getOrganizationId(), organizationDetailsViewModel.getId());
        assertEquals(organization.getName(), organizationDetailsViewModel.getName());
        assertEquals(organization.getDescription(), organizationDetailsViewModel.getDescription());
        assertEquals(organization.getLocation().getName(), organizationDetailsViewModel.getLocationName());
    }
}