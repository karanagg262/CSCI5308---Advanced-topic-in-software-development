package com.triplify.app.controller;

import com.triplify.app.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.triplify.app.database.PostDatabaseConstant.*;

public class PostQueryBuilder {
    public static int insertPostQuery(final Post post, Connection connection) {
        String query = "INSERT INTO " + post_table + "(" +
                post_table_destination + ", " +
                post_table_image + ", " +
                post_table_details + ", " +
                post_table_posted_date + ", " +
                post_table_userid + ") " +
                "VALUES (?,?,?,?,?);";
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, post.getDestination());
            pstmt.setBlob(2, post.getPostImage());
            pstmt.setString(3, post.getDetails());
            pstmt.setDate(4, post.getPostedDate());
            pstmt.setLong(5, post.getUserid());
            return pstmt.executeUpdate();

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
