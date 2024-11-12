package vn.iotstar.entity;

import java.io.Serializable;
import java.util.List;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="categories")  // This refers to the table name in the database
@NamedQuery(name="Category.findAll", query="SELECT c FROM Category c")  // The entity class name should be 'Category'
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryId;
    
    @Column(name = "CategoryCode")
    private String categorycode;
    
    @Column(name = "CategoryName", columnDefinition="NVARCHAR(200) NOT NULL") 
    private String categoryname;
    
    @Column(name = "Images")
    private String images;
    
    @Column(name = "Status")
    private boolean status;
    
    @OneToMany(mappedBy="category")
    private List<Video> videos;

    // Constructors, Getters, Setters, and other methods
    
    public Category() {}


    public boolean isStatus() {
        return status;
    }


    public List<Video> getVideos() {
        return videos;
    }

    public Video addVideo(Video  video) {
        getVideos().add(video);
        video.setCategory(this);
        return video;
    }

    public Video removeVideo(Video video) {
        getVideos().remove(video);
        video.setCategory(null);
        return video;
    }


	public int getCategoryId() {
		return categoryId;
	}


	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}


	public String getCategorycode() {
		return categorycode;
	}


	public void setCategorycode(String categorycode) {
		this.categorycode = categorycode;
	}


	public String getCategoryname() {
		return categoryname;
	}


	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	public String getImages() {
		return images;
	}


	public void setImages(String images) {
		this.images = images;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}
    
}
