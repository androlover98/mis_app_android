package com.android.mis.utils;

/**
 * Created by rajat on 3/3/17.
 */

public class Urls {

    public static final String host = "10.0.2.2";
    public static final String host_mobile = "172.16.100.205";
    public static final String server_port_no = "3000";
    public static final String server_protocol = "http";
    public static final String base_url = server_protocol+"://"+host+":"+server_port_no+"/";
    public static final String sub_base = "api/v1/";
    public static final String parsing_error_message = "Parsing Error";
    public static final String empty_message = "Please fill all the fields";
    public static final String error_connection_message = "Error in Connection";
    public static final String no_post_message = "No related posts found";
    public static final String image_base_path = server_protocol+"://"+host+"/mis_45/assets/images/";
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


    /*
        Parameters :None
        Method GET
    */
    public static final String session_year_url = base_url+sub_base+"attendance/sessionyear";

    /*
        Parameters : session(Monsoon,Winter,Summer),sessionyear
        Method GET
     */
    public static final String semester_url = base_url+sub_base+"attendance/semester";

    /*
        Parameters : session,session_year,semester
        Method GET
     */
    public static final String view_attendance_url = base_url+sub_base+"attendance/studentattendance";

    /*
        Parameters : map_id,subject_id
         Method GET
     */
    public static final String view_detailed_attendance_url = base_url+sub_base+"attendance/subjectattendance";

    /*
        Parameters : none
        Method GET
     */
    public static final String new_post_count_url = base_url+sub_base+"information/counts";

    /*
        Parameters : none
        Method GET
     */
    public static final String post_details_url = base_url+sub_base+"information/postdetails";

    public Urls(){

    }
}
