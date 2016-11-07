package fi.jamk.h3090.golfcourseswithmaterialdesign;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Crowmoore on 01-Nov-16.
 */

public class GolfCourses {

    private List<GolfCourse> courseList;

    public void initializeData(JSONArray courses) {
        courseList = new ArrayList<>();
        for(int i = 0; i < courses.length(); i++) {
            addGolfCourseToList(courses, i);
        }
    }

    private void addGolfCourseToList(JSONArray courses, int courseID) {
        try {
            JSONObject course = courses.getJSONObject(courseID);
            courseList.add(new GolfCourse(course.getString("Tyyppi"),
                                        course.getLong("lat"),
                                        course.getLong("lng"),
                                        course.getString("Kentta"),
                                        course.getString("Osoite"),
                                        course.getString("Puhelin"),
                                        course.getString("Sahkoposti"),
                                        course.getString("Webbi"),
                                        course.getString("Kuva"),
                                        course.getString("Kuvaus")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<GolfCourse> getCourses() {
        return courseList;
    }
}
