package ca.ubc.cs.cpsc210.meetup.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;
import java.util.List;

import ca.ubc.cs.cpsc210.meetup.model.PlaceFactory;

/**
 * Created by vincentchan on 15-03-18.
 */
public class GeoParser {

    JSONTokener tokener;
    JSONObject object;
    List<GeoPoint> geoPoints = new ArrayList<GeoPoint>();

    public List<GeoPoint> parse(String input) {

        tokener = new JSONTokener(input);
        tokener.skipTo('{');

        try {
            object = new JSONObject(tokener);
            JSONObject route = object.getJSONObject("route");
            JSONObject shape = route.getJSONObject("shape");
            JSONArray shapePoints = shape.getJSONArray("shapePoints");

//            double lat = 0;
//            double lon;
//            for (int i = 0; i < shapePoints.length(); i++) {
//
//                if (i % 2 == 0) {
//                    lat = shapePoints.getDouble(i);
//                } else {
//                    lon = shapePoints.getDouble(i);
//                    GeoPoint geoPoint = new GeoPoint(lat, lon);
//                }
//
//            }

            for (int j = 0; j < shapePoints.length() - 1; j+=2) {
                double lt = shapePoints.getDouble(j);
                double ln = shapePoints.getDouble(j+1);

                GeoPoint geoPoint = new GeoPoint(lt, ln);

                geoPoints.add(geoPoint);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return geoPoints;
    }

}
