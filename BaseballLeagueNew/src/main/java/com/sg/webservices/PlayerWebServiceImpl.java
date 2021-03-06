package com.sg.webservices;

import com.sg.commandmodel.player.createplayer.CreatePlayerCommandModel;
import com.sg.commandmodel.player.editplayer.EditPlayerCommandModel;
import com.sg.dto.Player;
import com.sg.dto.PlayerPosition;
import com.sg.dto.Position;
import com.sg.dto.Team;
import com.sg.service.PlayerPositionService;
import com.sg.service.PlayerService;
import com.sg.service.PositionService;
import com.sg.service.TeamService;
import com.sg.viewmodel.player.createplayer.CreatePlayerViewModel;
import com.sg.viewmodel.player.createplayer.CreatePositionViewModel;
import com.sg.viewmodel.player.createplayer.CreateTeamViewModel;
import com.sg.viewmodel.player.editplayer.EditPlayerViewModel;
import com.sg.viewmodel.player.editplayer.EditPositionViewModel;
import com.sg.viewmodel.player.editplayer.EditTeamViewModel;
import com.sg.viewmodel.player.playerlist.PlayerListViewModel;
import com.sg.viewmodel.player.playerlist.PlayerViewModel;
import com.sg.viewmodel.player.playerprofile.PlayerProfileViewModel;
import com.sg.viewmodel.player.playerprofile.PositionViewModel;
import com.sg.webservices.interfaces.PlayerWebService;
import com.sg.webservices.util.PagingUtils;
import org.omg.PortableInterceptor.INACTIVE;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PlayerWebServiceImpl implements PlayerWebService {

    PlayerService playerService;
    TeamService teamService;
    PositionService positionService;
    PlayerPositionService playerPositionService;

    @Inject
    public PlayerWebServiceImpl(PlayerService playerService, TeamService teamService, PositionService positionService, PlayerPositionService playerPositionService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.positionService = positionService;
        this.playerPositionService = playerPositionService;
    }

    @Override
    public PlayerListViewModel getPlayerListViewModel(Integer limit, Integer offset, Integer pageNumbers) {

        //Set defaults
        if(limit == null) limit = 5;
        if(offset == null) offset = 0;
        if(pageNumbers == null) pageNumbers = 5;

        //Instantiate
        PlayerListViewModel playerListViewModel = new PlayerListViewModel();

        //look stuff up
        List<Player> players = playerService.list(limit, offset);

        Integer currentPage = PagingUtils.calculatePageNumber(limit, offset);
        List<Integer> pages = PagingUtils.getPageNumbers(currentPage, pageNumbers);

        //put stuff in
        playerListViewModel.setPageNumber(currentPage);
        playerListViewModel.setPageNumbers(pages);
        playerListViewModel.setPlayers(translate(players));


        return playerListViewModel;
    }




    @Override
    public PlayerProfileViewModel getPlayerProfileViewModel(Long id) {

        //Instantiate
        PlayerProfileViewModel playerProfileViewModel = new PlayerProfileViewModel();

        //Look up stuff

        Player player = playerService.read(id);

        Team team = null;
        if (player.getTeam() != null) {
            team = teamService.read(player.getTeam().getId());
        }

        List<Position> positions = positionService.getPositionsByPlayer(player, Integer.MAX_VALUE,0);


        //put stuff
        playerProfileViewModel.setId(player.getId());
        playerProfileViewModel.setFirst(player.getFirstName());
        playerProfileViewModel.setLast(player.getLastName());
        playerProfileViewModel.setHometown(player.getHomeTown());

        if (team != null) {
            playerProfileViewModel.setTeamId(team.getId());
            playerProfileViewModel.setTeamName(team.getCity() + " " + team.getNickname());
        }

        playerProfileViewModel.setPositions(translatePosition(positions));



        return playerProfileViewModel;
    }

    @Override
    public CreatePlayerViewModel getCreatePlayerViewModel() {

        //Instantiate
        CreatePlayerViewModel createPlayerViewModel = new CreatePlayerViewModel();

        CreatePlayerCommandModel commandModel = new CreatePlayerCommandModel();
        createPlayerViewModel.setCreatePlayerCommandModel(commandModel);

        List<Team> teams = teamService.list(Integer.MAX_VALUE, 0);
        List<Position> positions =positionService.list(Integer.MAX_VALUE, 0);

        //populate
        createPlayerViewModel.setPositions(translateCreatePositionViewModel(positions));
        createPlayerViewModel.setTeams(translateCreateTeamViewModel(teams));

        //
        return createPlayerViewModel;
    }

    @Override
    public Player saveCreatePlayerCommandModel(CreatePlayerCommandModel createPlayerCommandModel) {

        //Instantiate player to save
        Player player = new Player();

        //Look up stuff
        Team team = teamService.read(createPlayerCommandModel.getTeamId());
        List<Position> positions = new ArrayList<>();

        for(Long positionId : createPlayerCommandModel.getPositionIds()) {
            positions.add(positionService.read(positionId));
        }

        //put stuff
        player.setFirstName(createPlayerCommandModel.getFirst());
        player.setLastName(createPlayerCommandModel.getLast());
        player.setHomeTown(createPlayerCommandModel.getHometown());

        if (team != null) {
            player.setTeam(team);
        }

        //Save stuff
        player = playerService.create(player);

        //create relationships
        for(Position postion : positions) {
            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(player);
            playerPosition.setPosition(postion);
            playerPositionService.create(playerPosition);

        }
        return player;
    }

    @Override
    public Player saveEditPlayerCommandModel(EditPlayerCommandModel editPlayerCommandModel) {

        //Instantiate player to save
        Player player = playerService.read(editPlayerCommandModel.getId());

        //Look up stuff
        Team team = teamService.read(editPlayerCommandModel.getTeamId());
        List<Position> positions = new ArrayList<>();

        for(Long positionId : editPlayerCommandModel.getPositionIds()) {
            positions.add(positionService.read(positionId));
        }

        //put stuff
        player.setFirstName(editPlayerCommandModel.getFirst());
        player.setLastName(editPlayerCommandModel.getLast());
        player.setHomeTown(editPlayerCommandModel.getHometown());
        player.setTeam(team);

        //Save stuff
        playerService.update(player);

        //Delete existing relationships
        //only delete if involves the bridge table
        List<PlayerPosition> existingRelationships = playerPositionService.getPlayerPositionByPlayer(player, Integer.MAX_VALUE, 0);

        for (PlayerPosition playerPosition : existingRelationships) {
            playerPositionService.delete(playerPosition);
        }

        //create relationships
        for(Position position : positions) {
            PlayerPosition playerPosition = new PlayerPosition();
            playerPosition.setPlayer(player);
            playerPosition.setPosition(position);
            playerPositionService.create(playerPosition);

        }
        return player;
    }

    @Override
    public EditPlayerViewModel getEditPlayerViewModel(Long id) {

        //Instantiate
        EditPlayerViewModel editPlayerViewModel = new EditPlayerViewModel();

        Player existingPlayer = playerService.read(id);
        List<Team> allTeams = teamService.list(Integer.MAX_VALUE, 0);
        List<Position> allPositions =positionService.list(Integer.MAX_VALUE, 0);

        Team selectedTeam = null;
        if(existingPlayer.getTeam() != null) {
            selectedTeam = teamService.read(existingPlayer.getTeam().getId());
        }

        List<Position> existingPositions = positionService.getPositionsByPlayer(existingPlayer, Integer.MAX_VALUE, 0);
        //populate
        editPlayerViewModel.setPositions(translateEditPositionViewModel(allPositions));
        editPlayerViewModel.setTeams(translateEditTeamViewModel(allTeams));

        //populate command model
        EditPlayerCommandModel commandModel = new EditPlayerCommandModel();
        commandModel.setId(existingPlayer.getId());
        commandModel.setFirst(existingPlayer.getFirstName());
        commandModel.setLast(existingPlayer.getLastName());
        commandModel.setHometown(existingPlayer.getHomeTown());

        if (selectedTeam != null) {
            commandModel.setTeamId(selectedTeam.getId());
        }

        if (existingPositions.size()>0) {
            Long[] positionIds = new Long[existingPositions.size()];

            int counter = 0;
            for(Position position : existingPositions) {
                positionIds[counter] = position.getId();

                counter ++;
            }

            commandModel.setPositionIds(positionIds);
        }
        editPlayerViewModel.setEditPlayerCommandModel(commandModel);

        return editPlayerViewModel;
    }


    private List<CreateTeamViewModel> translateCreateTeamViewModel(List<Team> teams){
        List<CreateTeamViewModel> createTeamViewModels = new ArrayList<>();

        for(Team team : teams) {
            CreateTeamViewModel createTeamViewModel = new CreateTeamViewModel();
            createTeamViewModel.setId(team.getId());
            createTeamViewModel.setName(team.getCity());
            createTeamViewModels.add(createTeamViewModel);

        }
        return createTeamViewModels;

    }

    private List<CreatePositionViewModel> translateCreatePositionViewModel(List<Position> positions){
        List<CreatePositionViewModel> viewModels = new ArrayList<>();

        for (Position position: positions) {
            CreatePositionViewModel viewModel = new CreatePositionViewModel();
            viewModel.setId(position.getId());
            viewModel.setName(position.getName());
            viewModels.add(viewModel);
        }
        return viewModels;
    }

    private List<EditTeamViewModel> translateEditTeamViewModel(List<Team> teams){
        List<EditTeamViewModel> editTeamViewModels = new ArrayList<>();

        for(Team team : teams) {
            EditTeamViewModel editTeamViewModel = new EditTeamViewModel();
            editTeamViewModel.setId(team.getId());
            editTeamViewModel.setName(team.getCity());
            editTeamViewModels.add(editTeamViewModel);

        }
        return editTeamViewModels;

    }

    private List<EditPositionViewModel> translateEditPositionViewModel(List<Position> positions){
        List<EditPositionViewModel> viewModels = new ArrayList<>();

        for (Position position: positions) {
            EditPositionViewModel viewModel = new EditPositionViewModel();
            viewModel.setId(position.getId());
            viewModel.setName(position.getName());
            viewModels.add(viewModel);
        }
        return viewModels;
    }

    private List<PositionViewModel> translatePosition(List <Position> positions){

        List<PositionViewModel> positionViewModels = new ArrayList<>();
        for (Position position : positions) {
            positionViewModels.add(translate(position));
        }
        return positionViewModels;

    }

    private PositionViewModel translate(Position position) {
        PositionViewModel positionViewModel = new PositionViewModel();
        positionViewModel.setId(position.getId());
        positionViewModel.setName(position.getName());
        return positionViewModel;
    }

    private List<PlayerViewModel> translate(List<Player> players) {

        List<PlayerViewModel>playerViewModels = new ArrayList<>();

        for (Player player : players) {
            playerViewModels.add(translate(player));
        }
        return playerViewModels;
    }

    private PlayerViewModel translate(Player player) {
        PlayerViewModel playerViewModel = new PlayerViewModel();

        //Note that we don't care if the name match.
        //Designers can be free to name things weird and wear fedoras
        playerViewModel.setFirst(player.getFirstName());
        playerViewModel.setLast(player.getLastName());
        playerViewModel.setId(player.getId());

        //Since we've separated the UI from the business services, this is the only place
        //we need to care about lazy loading vs eager fetching
        if(player.getTeam()!=null){
            Team team = teamService.read(player.getTeam().getId());

            if (team != null) {
                playerViewModel.setTeamId(team.getId());
                playerViewModel.setTeamName(team.getNickname());
            }
        }

        return playerViewModel;
    }
}
