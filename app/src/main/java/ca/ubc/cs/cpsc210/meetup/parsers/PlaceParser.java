package ca.ubc.cs.cpsc210.meetup.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import ca.ubc.cs.cpsc210.meetup.model.EatingPlace;
import ca.ubc.cs.cpsc210.meetup.model.Place;
import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;
import ca.ubc.cs.cpsc210.meetup.util.LatLon;

/**
 * Created by vincentchan on 15-03-23.
 */
public class PlaceParser {
    JSONTokener tokener;
    JSONObject object;

    /**
     * Parse JSON from Foursquare output stored into a file REQUIRES: input is a
     * file with valid data EFFECTS: parsed data is put into PlaceFactory
     */


    public void parse(String input) {

        tokener = new JSONTokener(input);

        try {
            object = new JSONObject(tokener);
            JSONObject responses = object.getJSONObject("response");
            JSONArray groupArray = responses.getJSONArray("groups");
            JSONObject items = groupArray.getJSONObject(0);
            JSONArray itemArray = items.getJSONArray("items");


            for (int i = 0; i < itemArray.length(); i++) {
                String name = itemArray.getJSONObject(i).getJSONObject("venue").getString("name");

                double lat = itemArray.getJSONObject(i).getJSONObject("venue").getJSONObject("location")
                        .getDouble("lat");
                double lon = itemArray.getJSONObject(i).getJSONObject("venue").getJSONObject("location")
                        .getDouble("lng");

                LatLon latlon = new LatLon(lat, lon);
                Place place = new EatingPlace(name, latlon);

                PlaceFactory places = PlaceFactory.getInstance();
                places.add(place);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
