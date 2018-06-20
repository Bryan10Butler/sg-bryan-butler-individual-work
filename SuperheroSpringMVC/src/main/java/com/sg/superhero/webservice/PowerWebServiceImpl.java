package com.sg.superhero.webservice;

import com.sg.dto.HeroPower;
import com.sg.dto.Power;
import com.sg.model.commandmodel.power.editpower.PowerEditCommandModel;
import com.sg.model.commandmodel.power.powerpage.PowerPageCreateCommandModel;
import com.sg.model.viewmodel.power.detailspower.PowerDetailsViewModel;
import com.sg.model.viewmodel.power.editpower.PowerEditViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerPageViewModel;
import com.sg.model.viewmodel.power.powerpage.PowerViewModel;
import com.sg.service.HeroPowerService;
import com.sg.service.PowerService;
import com.sg.superhero.webservice.util.PagingUtils;
import com.sg.webservice.PowerWebService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PowerWebServiceImpl implements PowerWebService{

    PowerService powerService;

    HeroPowerService heroPowerService;

    @Inject
    public PowerWebServiceImpl(PowerService powerService, HeroPowerService heroPowerService) {
        this.powerService = powerService;
        this.heroPowerService = heroPowerService;
    }

    @Override
    public PowerPageViewModel retrievePowerPageViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        //Instantiate
        PowerPageViewModel powerPageViewModel = new PowerPageViewModel();

        //Look stuff up
        List<Power> powers = powerService.retrieveAllPowers(limit, offset);
        PowerPageCreateCommandModel powerPageCreateCommandModel = new PowerPageCreateCommandModel();

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        //Put stuff in
        powerPageViewModel.setPageNumber(currentPage);
        powerPageViewModel.setPageNumbers(pages);
        powerPageViewModel.setPowerPageCreateCommandModel(powerPageCreateCommandModel);
        //translate
        powerPageViewModel.setPowers(translatePowerList(powers));

        return powerPageViewModel;
    }

    @Override
    public PowerEditViewModel retrievePowerEditViewModel(Long powerId) {

        //Instantiate
        PowerEditViewModel powerEditViewModel = new PowerEditViewModel();

        //Look up stuff
        Power existingPower = powerService.retrievePower(powerId);

        //Put stuff in
        PowerEditCommandModel powerEditCommandModel = new PowerEditCommandModel();

        //existing power id as we may be updating fields
        powerEditCommandModel.setPowerId(existingPower.getPowerId());
        powerEditCommandModel.setName(existingPower.getName());
        powerEditCommandModel.setDescription(existingPower.getDescription());

        powerEditViewModel.setPowerEditCommandModel(powerEditCommandModel);

        return powerEditViewModel;
    }

    @Override
    public Power savePowerEditCommandModel(PowerEditCommandModel powerEditCommandModel) {

        //retrieve power tha was passed in
        Power power = powerService.retrievePower(powerEditCommandModel.getPowerId());

        //set fields with new fields input from user
        power.setName(powerEditCommandModel.getName());
        power.setDescription(powerEditCommandModel.getDescription());

        //update power
        powerService.updatePower(power);

        //return power
        return power;
    }

    @Override
    public Power savePowerPageCreateCommandModel(PowerPageCreateCommandModel powerPageCreateCommandModel) {

        //Instantiate
        Power power = new Power();

        //Put stuff in (set fields from passed in object with user input upon clicking "create power"
        power.setName(powerPageCreateCommandModel.getName());
        power.setDescription(powerPageCreateCommandModel.getDescription());

        return powerService.addPower(power);
    }

    @Override
    public PowerDetailsViewModel retrievePowerDetailsViewModel(Long powerId) {

        //Instantiate
        PowerDetailsViewModel powerDetailsViewModel = new PowerDetailsViewModel();
        Power power = powerService.retrievePower(powerId);

        //Put stuff in
        powerDetailsViewModel.setId(power.getPowerId());
        powerDetailsViewModel.setName(power.getName());
        powerDetailsViewModel.setDescription(power.getDescription());

        return powerDetailsViewModel;
    }

    @Override
    public void removePowerViewModel(Long powerId) {

        Power power = powerService.retrievePower(powerId);

        List<HeroPower> heroPowerList = heroPowerService.retrieveHeroPowerByPower(power.getPowerId());
        for(HeroPower heroPower : heroPowerList) {
            heroPowerService.removeHeroPower(heroPower);
        }
        powerService.removePower(power);
    }

    //TRANSLATE METHODS FOR LOCATION PAGE VIEW MODELS

    private List<PowerViewModel> translatePowerList(List<Power> powers) {

        List<PowerViewModel> powerViewModels = new ArrayList<>();

        for (Power power : powers) {

            powerViewModels.add(translatePower(power));
        }
        return powerViewModels;
    }

    private PowerViewModel translatePower (Power power) {
        PowerViewModel powerViewModel =new PowerViewModel();
        powerViewModel.setId(power.getPowerId());
        powerViewModel.setName(power.getName());

        return powerViewModel;
    }
}
