package fi.jamk.h3090.golfcourseswithmaterialdesign;

/**
 * Created by Crowmoore on 27-Oct-16.
 */

public class GolfCourse {

    protected String type;
    protected long latitude;
    protected long longitude;
    protected String course;
    protected String address;
    protected String phone;
    protected String email;
    protected String url;
    protected String image;
    protected String details;

    public GolfCourse(String type, long latitude, long longitude,
                      String course, String address, String phone,
                      String email, String url, String image, String details) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.course = course;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.url = url;
        this.image = image;
        this.details = details;
    }
}
