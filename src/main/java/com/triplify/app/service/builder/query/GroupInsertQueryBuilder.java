package com.triplify.app.service.builder.query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.triplify.app.Group.database.GroupDetailsDatabaseConstant.*;

public class GroupInsertQueryBuilder implements QueryBuilder{
    private final String baseQuery = "INSERT INTO "+ group_details_table + "(" +
            group_name + ", " +
            group_trip_start_date + ", " +
            group_trip_end_date + ", " +
            group_destination + ", " +
            group_type + ", " +
            group_creater_user_id + ") " +
            "VALUES (?,?,?,?,?,?);";
    private String type;
    private String table;
    private List<Object> columns;
    private String user;
    private Connection connection;

    public PreparedStatement getPreparedStatement() {
        int count = 1;
        if(null != columns){
            for(Object column: columns){
                try {
                    preparedStatement.setObject(count,column);
                    count += 1;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return preparedStatement;
    }

    private PreparedStatement preparedStatement;

    public GroupInsertQueryBuilder(QueryBuilder builder) {
    }

    @Override
    public GroupInsertQueryBuilder setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public GroupInsertQueryBuilder setTable(String table) {
        this.table = table;
        return this;
    }

    @Override
    public GroupInsertQueryBuilder setValues(List<Object> columns) {
        this.columns=columns;
        return this;
    }

    @Override
    public GroupInsertQueryBuilder setConnection(Connection connection) {
        this.connection = connection;
        try {
            preparedStatement = connection.prepareStatement(baseQuery);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    public GroupInsertQueryBuilder build(){
        return new GroupInsertQueryBuilder(this);
    }
}
