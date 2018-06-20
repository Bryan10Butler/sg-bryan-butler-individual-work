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
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.service.*;
import com.sg.superhero.webservice.util.PagingUtils;
import com.sg.webservice.HeroWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class HeroWebServiceImpl implements HeroWebService{

    HeroOrganizationService heroOrganizationService;
    HeroPowerService heroPowerService;
    HeroService heroService;
    PowerService powerService;
    OrganizationService organizationService;
    HeroSightingService heroSightingService;

    @Inject
    public HeroWebServiceImpl(HeroOrganizationService heroOrganizationService, HeroPowerService heroPowerService, HeroService heroService, PowerService powerService, OrganizationService organizationService, HeroSightingService heroSightingService) {
        this.heroOrganizationService = heroOrganizationService;
        this.heroPowerService = heroPowerService;
        this.heroService = heroService;
        this.powerService = powerService;
        this.organizationService = organizationService;
        this.heroSightingService = heroSightingService;
    }

    @Override
    public HeroPageViewModel retrieveHeroPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        //Instantiate
        HeroPageViewModel heroPageViewModel = new HeroPageViewModel();

        //Look up stuff
        List<Hero> heroList = heroService.retrieveAllHero(limit, offset);
        List<Power> powerList = powerService.retrieveAllPowers(limit, offset);
        List<Organization> organizationList = organizationService.retrieveAllOrganizations(limit, offset);

        //command model
        HeroPageCreateCommandModel commandModel = new HeroPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        //Put stuff in
        heroPageViewModel.setPageNumber(currentPage);
        heroPageViewModel.setPageNumbers(pages);

        heroPageViewModel.setHeroPageCreateCommandModel(commandModel);

        heroPageViewModel.setHeroes(translateHeroList(heroList));
        heroPageViewModel.setPowers(translatePowers(powerList));
        heroPageViewModel.setOrganizations(translateOrganizations(organizationList));

        return heroPageViewModel;
    }

    @Override
    public Hero saveHeroPageCreateCommandModel(HeroPageCreateCommandModel heroPageCreateCommandModel) {

        //Instantiate
        Hero hero =  new Hero();

        //Look stuff up
        List<Power> powerList = new ArrayList<>();
        List<Organization> organizationList = new ArrayList<>();

        for(Long powerId : heroPageCreateCommandModel.getPowerIds()) {
            powerList.add(powerService.retrievePower(powerId));
        }

        for(Long organizationId : heroPageCreateCommandModel.getOrganizationIds()) {
            organizationList.add(organizationService.retrieveOrganization(organizationId));
        }

        hero.setName(heroPageCreateCommandModel.getName());
        hero.setDescription(heroPageCreateCommandModel.getDescription());

        //create hero
        Hero createdHero = heroService.addHero(hero);

        //create relationships
        for(Power power : powerList) {
            HeroPower heroPower = new HeroPower();
            heroPower.setPower(power);
            heroPower.setHero(createdHero);
            heroPowerService.addHeroPower(heroPower);
        }

        for(Organization organization : organizationList) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setOrganization(organization);
            heroOrganization.setHero(createdHero);
            heroOrganizationService.addHeroOrganization(heroOrganization);
        }
        return createdHero;
    }

    @Override
    public HeroEditViewModel retrieveHeroEditViewModel(Long HeroId) {
        
        //Instantiate
        HeroEditViewModel heroEditViewModel = new HeroEditViewModel();
        
        //Look up Stuff
        Hero existingHero = heroService.retrieveHero(HeroId);
        List<Power> allPowers = powerService.retrieveAllPowers(Integer.MAX_VALUE, 0);
        List<Organization> allOrganizations = organizationService.retrieveAllOrganizations(Integer.MAX_VALUE, 0);
        
        //Bridge Lists
        List<Power> existingPowers = powerService.retrievePowerByHero(existingHero,Integer.MAX_VALUE, 0 );
        List<Organization> existingOrganizations = organizationService.retrieveOrganizationByHero(existingHero, Integer.MAX_VALUE, 0);
        
        //Translate
        heroEditViewModel.setPowers(translateEditPowerViewModel(allPowers));
        heroEditViewModel.setOrganizations(translateEditOrganizationViewModel(allOrganizations));

        //Populate the command model
        HeroEditCommandModel heroEditCommandModel = new HeroEditCommandModel();
        heroEditCommandModel.setId(existingHero.getHeroId());
        heroEditCommandModel.setName(existingHero.getName());
        heroEditCommandModel.setDescription(existingHero.getDescription());

        if (existingPowers.size() > 0) {
            Long[] powerIds = new Long[existingPowers.size()];

            int counter = 0;
            for(Power power : existingPowers) {
                powerIds[counter] = power.getPowerId();
                counter ++;
            }
            heroEditCommandModel.setPowerIds(powerIds);
        }

        if(existingOrganizations.size() > 0) {
            Long[] organizationIds = new Long[existingOrganizations.size()];

            int counter = 0;
            for(Organization organization : existingOrganizations) {
                organizationIds[counter] = organization.getOrganizationId();
                counter ++;
            }
            heroEditCommandModel.setOrganizationIds(organizationIds);
        }

        heroEditViewModel.setHeroEditCommandModel(heroEditCommandModel);

        return heroEditViewModel;
    }

    @Override
    public Hero saveHeroEditCommandModel(HeroEditCommandModel heroEditCommandModel) {

        //Instantiate a new Hero
        Hero hero = heroService.retrieveHero(heroEditCommandModel.getId());

        //Look stuff up
        List<Power> powers = new ArrayList<>();
        List<Organization> organizations = new ArrayList<>();

        for (Long powerId : heroEditCommandModel.getPowerIds()) {
            powers.add(powerService.retrievePower(powerId));
        }

        for (Long organizationId : heroEditCommandModel.getOrganizationIds()) {
            organizations.add(organizationService.retrieveOrganization(organizationId));
        }

        //put stuff in
        hero.setName(heroEditCommandModel.getName());
        hero.setDescription(heroEditCommandModel.getDescription());

        //Save stuff
        heroService.updateHero(hero);

        //Delete existing relationships
        List<HeroPower> existingPowerRelationships = heroPowerService.retrieveHeroPowerByHero(hero.getHeroId());

        for(HeroPower heroPower : existingPowerRelationships) {
            heroPowerService.removeHeroPower(heroPower);
        }

        List<HeroOrganization> existingOrganizationRelationships = heroOrganizationService.retrieveHeroOrganizationByHero(hero.getHeroId());

        for(HeroOrganization heroOrganization : existingOrganizationRelationships) {
            heroOrganizationService.removeHeroOrganization(heroOrganization);
        }

        //Create relationships
        for(Power power : powers) {
            HeroPower heroPower = new HeroPower();
            heroPower.setHero(hero);
            heroPower.setPower(power);
            heroPowerService.addHeroPower(heroPower);
        }

        for(Organization organization : organizations) {
            HeroOrganization heroOrganization = new HeroOrganization();
            heroOrganization.setHero(hero);
            heroOrganization.setOrganization(organization);
            heroOrganizationService.addHeroOrganization(heroOrganization);
        }
        return hero;
    }

    @Override
    public HeroDetailsViewModel retrieveHeroDetailsViewModel(Long heroId) {

        //Instantiate
        HeroDetailsViewModel heroDetailsViewModel = new HeroDetailsViewModel();

        //Look stuff up
        Hero hero = heroService.retrieveHero(heroId);

        List<Power> powers = powerService.retrievePowerByHero(hero, Integer.MAX_VALUE, 0);
        List<Organization> organizations = organizationService.retrieveOrganizationByHero(hero, Integer.MAX_VALUE, 0);


        //Put stuff
        heroDetailsViewModel.setId(hero.getHeroId());
        heroDetailsViewModel.setName(hero.getName());
        heroDetailsViewModel.setDescription(hero.getDescription());

        heroDetailsViewModel.setOrganizations(translateHeroDetailsViewModelOrganizationList(organizations));
        heroDetailsViewModel.setPowers(translateHeroDetailsViewModelPowersList(powers));

        return heroDetailsViewModel;
    }

    @Override
    public void removeHeroViewModel(Long HeroId) {

        Hero hero = heroService.retrieveHero(HeroId);

        List<HeroSighting> heroSightingList = heroSightingService.retrieveHeroSightingByHero(hero.getHeroId());
        for(HeroSighting heroSighting : heroSightingList) {
            heroSightingService.removeHeroSighting(heroSighting);
        }

        List<HeroOrganization> heroOrganizationList = heroOrganizationService.retrieveHeroOrganizationByHero(hero.getHeroId());
        for(HeroOrganization heroOrganization : heroOrganizationList) {
            heroOrganizationService.removeHeroOrganization(heroOrganization);
        }

        List<HeroPower> heroPowerList = heroPowerService.retrieveHeroPowerByHero(hero.getHeroId());
        for(HeroPower heroPower : heroPowerList) {
            heroPowerService.removeHeroPower(heroPower);
        }

        heroService.removeHero(hero);
    }

    //TRANSLATE METHODS FOR RETRIEVE HERO DETAILS VIEW MODEL

    private List<HeroDetailsPowerViewModel> translateHeroDetailsViewModelPowersList(List<Power> powers) {
        List<HeroDetailsPowerViewModel> heroDetailsPowerViewModels = new ArrayList<>();
        for(Power power : powers) {
            heroDetailsPowerViewModels.add(translatePowerDetailsViewModelList(power));
        }
        return heroDetailsPowerViewModels;
    }

    private HeroDetailsPowerViewModel translatePowerDetailsViewModelList(Power power) {
        HeroDetailsPowerViewModel heroDetailsPowerViewModel = new HeroDetailsPowerViewModel();
        heroDetailsPowerViewModel.setId(power.getPowerId());
        heroDetailsPowerViewModel.setName(power.getName());
        return heroDetailsPowerViewModel;
    }

    private List<HeroDetailsOrganizationViewModel> translateHeroDetailsViewModelOrganizationList(List<Organization> organizations) {
        List<HeroDetailsOrganizationViewModel> heroDetailsOrganizationViewModels = new ArrayList();
        for(Organization organization : organizations) {
            heroDetailsOrganizationViewModels.add(translateOrganizationHeroDetailsViewModel(organization));
        }
        return heroDetailsOrganizationViewModels;
    }

    private HeroDetailsOrganizationViewModel translateOrganizationHeroDetailsViewModel(Organization organization) {

        HeroDetailsOrganizationViewModel heroDetailsOrganizationViewModel = new HeroDetailsOrganizationViewModel();
        heroDetailsOrganizationViewModel.setId(organization.getOrganizationId());
        heroDetailsOrganizationViewModel.setName(organization.getName());
        return heroDetailsOrganizationViewModel;
    }

    //TRANSLATE FOR ORGANIZATION LIST PAGE VIEW MODEL

    private List<HeroPageOrganizationViewModel> translateOrganizations(List<Organization> organizationList) {

        List<HeroPageOrganizationViewModel> heroPageOrganizationViewModels = new ArrayList<>();

        for(Organization organization : organizationList) {
            heroPageOrganizationViewModels.add(translateOrganization(organization));
        }
        return heroPageOrganizationViewModels;
    }

    private HeroPageOrganizationViewModel translateOrganization(Organization organization) {

        HeroPageOrganizationViewModel heroPageOrganizationViewModel = new HeroPageOrganizationViewModel();
        heroPageOrganizationViewModel.setId(organization.getOrganizationId());
        heroPageOrganizationViewModel.setName(organization.getName());
        return heroPageOrganizationViewModel;
    }

    //TRANSLATE FOR HERO LIST PAGE VIEW MODEL

    private List<HeroViewModel> translateHeroList(List<Hero> heroList) {

        List<HeroViewModel> heroViewModels = new ArrayList<>();

        for (Hero hero :heroList) {
            heroViewModels.add(translateHero(hero));
        }
        return heroViewModels;
    }

    private HeroViewModel translateHero(Hero hero) {
        HeroViewModel heroViewModel = new HeroViewModel();
        heroViewModel.setId(hero.getHeroId());
        heroViewModel.setName(hero.getName());
        return heroViewModel;
    }

    //TRANSLATE FOR POWER LIST PAGE VIEW MODEL

    private List<HeroPagePowerViewModel> translatePowers(List<Power> powerList) {

        List<HeroPagePowerViewModel> heroPagePowerViewModels = new ArrayList<>();

        for(Power power : powerList) {
            heroPagePowerViewModels.add(translatePower(power));
        }
        return heroPagePowerViewModels;
    }

    private HeroPagePowerViewModel translatePower(Power power) {

        HeroPagePowerViewModel heroPagePowerViewModel = new HeroPagePowerViewModel();
        heroPagePowerViewModel.setId(power.getPowerId());
        heroPagePowerViewModel.setName(power.getName());
        return heroPagePowerViewModel;
    }

    //TRANSLATE METHODS FOR RETRIEVE HERO EDIT VIEW MODEL
    private List<HeroEditOrganizationViewModel> translateEditOrganizationViewModel(List<Organization> allOrganizations) {
        List<HeroEditOrganizationViewModel> heroEditOrganizationViewModels = new ArrayList<>();

        for(Organization organization : allOrganizations) {
            HeroEditOrganizationViewModel heroEditOrganizationViewModel = new HeroEditOrganizationViewModel();
            heroEditOrganizationViewModel.setId(organization.getOrganizationId());
            heroEditOrganizationViewModel.setName(organization.getName());
            heroEditOrganizationViewModels.add(heroEditOrganizationViewModel);
        }
        return heroEditOrganizationViewModels;
    }

    private List<HeroEditPowerViewModel> translateEditPowerViewModel(List<Power> allPowers) {
        List<HeroEditPowerViewModel> heroEditPowerViewModels = new ArrayList<>();

        for(Power powers  : allPowers) {
            HeroEditPowerViewModel heroEditPowerViewModel = new HeroEditPowerViewModel();
            heroEditPowerViewModel.setId(powers.getPowerId());
            heroEditPowerViewModel.setName(powers.getName());
            heroEditPowerViewModels.add(heroEditPowerViewModel);
        }
        return heroEditPowerViewModels;
    }
}
