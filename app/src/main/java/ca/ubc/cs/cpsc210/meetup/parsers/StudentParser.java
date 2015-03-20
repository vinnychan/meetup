package ca.ubc.cs.cpsc210.meetup.parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.SortedSet;
import java.util.TreeSet;

import ca.ubc.cs.cpsc210.meetup.model.CourseFactory;
import ca.ubc.cs.cpsc210.meetup.model.Section;
import ca.ubc.cs.cpsc210.meetup.model.Student;


/**
 * Created by vincentchan on 15-03-19.
 */
public class StudentParser {

    private JSONTokener tokener;
    private JSONObject object;
    CourseFactory courseFactory = CourseFactory.getInstance();
    int id;
    private String firstName;
    private String lastName;




    public SortedSet<Section> parse(String input) {

        SortedSet<Section> sectionList = new TreeSet<Section>();
        tokener = new JSONTokener(input);

        try {
            object = new JSONObject(tokener);
            firstName = object.getString("FirstName");
            lastName = object.getString("LastName");
            id = object.getInt("Id");


            JSONArray sections = object.getJSONArray("Sections");

            String sectionName;
            String courseName;
            String courseNumber;

            for (int i = 0; i < sections.length(); i++) {
                sectionName = sections.getJSONObject(i).getString("SectionName");
                courseName = sections.getJSONObject(i).getString("CourseName");
                courseNumber = sections.getJSONObject(i).getString("CourseNumber");

                Section randSection = courseFactory.getCourse(courseName, Integer.parseInt(courseNumber)).getSection(sectionName);

                sectionList.add(randSection);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sectionList;

    }


    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
