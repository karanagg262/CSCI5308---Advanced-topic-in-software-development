package com.triplify.app.model;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.validation.constraints.NotNull;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

import static com.triplify.app.database.PostDatabaseConstant.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="destination")
    private String destination;

    @Lob
    @NotNull
    @Column(name = "image")
    private Blob postImage;

    @NotNull
    private byte[] postImageBytes;

    @Column(name="details")
    private String details;
    @Column(name="postedDate")
    private Date postedDate;
    @Column(name="userid")
    private Long userid;

    public Post() {
    }
    public Post(Long id, String destination, Blob postImage, String details, Date postedDate, Long userid, byte[] postImageBytes) {
        this.id = id;
        this.destination = destination;
        this.postImage = postImage;
        this.details = details;
        this.postedDate = postedDate;
        this.userid = userid;
        this.postImageBytes = postImageBytes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Blob getPostImage() {
        try {
            Blob blob = new SerialBlob(this.postImageBytes);
            return blob;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPostImageBytes(byte[] postImageBytes) {
        this.postImageBytes = postImageBytes;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }
}
