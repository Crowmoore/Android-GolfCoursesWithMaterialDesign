package fi.jamk.h3090.golfcourseswithmaterialdesign;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Crowmoore on 27-Oct-16.
 */

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {

    private List<GolfCourse> courses;
    private Context context;

    public CourseAdapter(Context context, List<GolfCourse> courses) {
        this.context = context;
        this.courses = courses;
    }

    @Override
    public int getItemCount() {
        return this.courses.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        GolfCourse course = courses.get(position);
        viewHolder.courseImage.setImageResource(R.drawable.golf_logo);
        viewHolder.courseName.setText(course.course);
        viewHolder.courseAddress.setText(course.address);
        viewHolder.coursePhone.setText(course.phone);
        viewHolder.courseEmail.setText(course.email);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView courseImage;
        public TextView courseName;
        public TextView courseAddress;
        public TextView coursePhone;
        public TextView courseEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            courseImage = (ImageView) itemView.findViewById(R.id.courseImageView);
            courseName = (TextView) itemView.findViewById(R.id.courseNameTextView);
            courseAddress = (TextView) itemView.findViewById(R.id.courseAddressTextView);
            coursePhone = (TextView) itemView.findViewById(R.id.coursePhoneTextView);
            courseEmail = (TextView) itemView.findViewById(R.id.courseEmailTextView);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    String name = courses.get(position).course;
                    Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
