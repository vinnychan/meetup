package ca.ubc.cs.cpsc210.meetup.model;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/*
 * A place represents a location at which people can meet
 */
public class Place extends Location {

    // The name of the place
    private String name;

    private String status;
    private String rating;
    private String url;


    // Tags describing place
    private Set<String> tags;

    /**
     * Constructor
     * @param placeName tThe name of the place
     */
    public Place(String placeName) {
        this(placeName, new LatLon());
    }

    /**
     * Constructor
     * @param placeName The name of the place
     * @param latLon The latitude and longitude of the place
     */
    public Place(String placeName, LatLon latLon) {
        super(latLon);
        name = placeName;
        displayText = placeName;
        tags = new HashSet<String>();
    }


    /**
     * Add a tag describing what can be done in the place
     * @param tag The tag to add, non-null
     */
    public void addTag(String tag) {
        tags.add(tag.toLowerCase().trim());
    }

    /**
     * Determine if this place has the specified tag
     * @param tag The tag to look for, non-null
     * @return true if found, false otherwise
     */
    public boolean containsTag(String tag) {
        return tags.contains(tag.toLowerCase().trim());
    }


	// ************** Getters ****************

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
