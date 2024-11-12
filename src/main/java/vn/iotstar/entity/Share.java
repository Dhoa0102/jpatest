package vn.iotstar.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="shares")  // This refers to the table name in the database
@NamedQuery(name="Share.findAll", query="SELECT s FROM Share s")  // The entity class name should be 'Category'
public class Share implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShareId")
    private int shareId;
    
    @Column(name = "Emails")
    private String email;
    
    @Column(name = "Username") 
    private String username;
    
    @Column(name = "ShareDate")
    private Date shareDate;
    
    
    @ManyToMany
    @JoinColumn(name = "VideoId")
	private List<Video> video;

    // Constructors, Getters, Setters, and other methods
}
