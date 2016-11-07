package fi.jamk.h3090.golfcourseswithmaterialdesign;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FetchDataTask fetchData = new FetchDataTask();
        fetchData.execute("http://ptm.fi/jamk/android/golfcourses/golf_courses.json");

        recyclerView = (RecyclerView) findViewById(R.id.golfCourseRecyclerView);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsing = (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsing.setTitle("Golf courses");
        collapsing.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
        collapsing.setExpandedTitleColor(getResources().getColor(R.color.colorAccent));
    }

    protected class FetchDataTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... urls) {
            HttpURLConnection urlConnection = null;
            JSONObject json = null;
            try {
                URL url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder builder = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                }
                reader.close();
                json = new JSONObject(builder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null) { urlConnection.disconnect(); }
            }
            return json;
        }
        protected void onPostExecute(JSONObject json) {
            GolfCourses manager = new GolfCourses();
            try {
                JSONArray coursesJSON = json.getJSONArray("kentat");
                manager.initializeData(coursesJSON);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            setAdapter(manager.getCourses());
        }
    }

    protected void setAdapter(List<GolfCourse> courses) {
        adapter = new CourseAdapter(this, courses);
        recyclerView.setAdapter(adapter);
    }
}
