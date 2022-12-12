package com.triplify.app.exploreFeature.controller;

import com.triplify.app.controller.*;
import com.triplify.app.database.DatabaseConnection;
import com.triplify.app.database.DatabaseExceptionHandler;
import com.triplify.app.exploreFeature.database.ExplorationTableInsertQueryBuilder;
import com.triplify.app.exploreFeature.exception.ExplorationException;
import com.triplify.app.exploreFeature.model.Exploration;
import com.triplify.app.model.GroupDetails;
import com.triplify.app.model.GroupMembersDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class ExplorationController implements IExplorationController {

    ExplorationTableInsertQueryBuilder explorationTableInsertQueryBuilder =
            new ExplorationTableInsertQueryBuilder();

    private void validateSearchLocationString(final String location)
        throws ExplorationException {
        final boolean isStringValid = (location != null) &&
                (!location.trim().isEmpty());
        if(!isStringValid){
            throw new ExplorationException("Invalid location was searched there!!");
        }
    }

    @PostMapping("/explore/searchLocation")
    @Override
    public List<Exploration> searchGroups(@RequestParam("location") String location)
            throws ExplorationException, SQLException {

        List<Exploration> explorationList = new ArrayList<>();
        Exploration exploration = new Exploration();

        validateSearchLocationString(location);

        List<GroupDetails> groupDetailsList = null;
        try {
            groupDetailsList = new GroupController().getAllGroupDetails();
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }

        List<GroupMembersDetails> groupMembersDetailsList = null;
        try {
            groupMembersDetailsList = new GroupAddMemberController().getAllMembers();
        } catch (DatabaseExceptionHandler e) {
            throw new RuntimeException(e);
        }

        int countMembers = 0;

        for(int i = 0 ; i < groupDetailsList.size() ; i++){

            if(groupDetailsList.get(i).getDestination().equalsIgnoreCase(location)){
                exploration.setGroupName(groupDetailsList.get(i).getGroupName());
                exploration.setPlaceDescription(groupDetailsList.get(i).getGroupDescription());

                countMembers = 0;
                for(int j = 0 ; j < groupMembersDetailsList.size() ; j++){
                    if(groupMembersDetailsList.get(j).getGroupName()
                            .equalsIgnoreCase(groupDetailsList.get(i).getGroupName())){
                        countMembers++;
                    }
                }

                exploration.setNumberOfMembers(countMembers);
                exploration.setGroupId(groupDetailsList.get(i).getId());
                explorationList.add(exploration);
            }
        }


        for(int i = 0 ; i < explorationList.size() ; i++) {

            try (final Connection connection = DatabaseConnection.getInstance().getDatabaseConnection();
                 final Statement statement = connection.createStatement()) {

                final String insertSearchLocationQuery =
                        explorationTableInsertQueryBuilder.explorationTableInsertQuery(explorationList.get(i));

                final int rowInserted =
                        statement.executeUpdate(insertSearchLocationQuery, Statement.RETURN_GENERATED_KEYS);

                if (rowInserted > 0) {
                    System.out.println("Successfully retrieved the groups going for this trip");
                }

            } catch (DatabaseExceptionHandler e) {
                throw new RuntimeException(e);
            }
        }

        return explorationList;
    }
}
