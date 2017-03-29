package com.android.mis.utils;

/**
 * Created by rajat on 3/3/17.
 */

public class Urls {

    public static final String host = "10.0.2.2";
    public static final String host_mobile = "192.168.43.30";
    public static final String server_port_no = "3000";
    public static final String server_protocol = "http";
    public static final String base_url = server_protocol+"://"+host_mobile+":"+server_port_no+"/";
    public static final String sub_base = "api/v1/";
    public static final String parsing_error_message = "Parsing Error";
    public static final String error_connection_message = "Error in Connection";
    public static final String image_base_path = server_protocol+"://"+host_mobile+"/mis_45/assets/images/";
    /*
        Login parameters username and password
        Method POST
     */
    public static final String login_url = base_url+"login";

    /*
        Menu parameters : None
        Method GET
     */
    public static final String menu_url = base_url+sub_base+"menu";

    /*
       Parameters : None
       Method GET
     */
    public static final String view_details_url = base_url+sub_base+"viewdetails";

    /*
        Parameters : None
        Method GET
     */
    public static final String departments_url = base_url+sub_base+"coursestructure/departments";

    /*
        Parameters : dept_id
        Method GET
     */
    public static final String courses_url = base_url+sub_base+"coursestructure/coursesdetails";

    /*
       Parameters :dept_id,session(e.g 2014_2015),semester,branch_id,course
       Method GET
     */
    public static final String view_course_url = base_url+sub_base+"coursestructure/viewcourse";

    public Urls(){

    }
}
