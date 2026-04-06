package api.endpoints;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static io.restassured.RestAssured.given;

public class teacherEndpoints {

    public static Response getTeacherStudents(String teacher_token, String teacher_id)
    {
        Response response =given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .when()
                .get(Routes.teacherStudentsUrl);
        return response;
    }

    public static Response getSessionSummary(String teacher_token, String teacher_id)
    {
        Response response =given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .queryParam("page","1")
                .queryParam("limit","10")
                .queryParam("start_time","2025-01-01")
                .queryParam("end_time","2026-03-30")

                .when()
                .get(Routes.sessionSummaryListUrl);
        return response;
    }

    public static Response approveSessionSummary(String teacher_token, String teacher_id)
    {

        File f = new File(".//body.json");
        FileReader fr = null;
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JSONTokener jt = new JSONTokener(fr);
        JSONObject data = new JSONObject(jt);

        Response response =given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .pathParam("summary_session_id","session_id")
                .pathParam("session_summary_id","summary_id")
                .body(data)

                .when()
                .post(Routes.approveSessionSummaryUrl);
        return response;
    }

    public static Response getFutureSessions(String teacher_token, String teacher_id)
    {
        Response response =given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .queryParam("time_frame","future")
                .queryParam("limit","1")
                .queryParam("page","1")
                .queryParam("sort_order","asc")

                .when()
                .get(Routes.sessionsURL);
        return response;
    }

    public static Response getClassSchedule(String teacher_token, String teacher_id)
    {
        Response response =given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .queryParam("time_frame","range")
                .queryParam("sort_order","desc")
                .queryParam("fromDate","2026-03-30")
                .queryParam("toDate","2026-03-30")
                .queryParam("page","1")
                .queryParam("limit","20")


                .when()
                .get(Routes.sessionsURL);
        return response;
    }

    public static Response userProfile(String teacher_token, String teacher_id)
    {
        Response response = given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .when()
                .get(Routes.profileUrl);
        return response;
    }

    public static Response tokenInfinity(String teacher_token, String teacher_id)
    {
        Response response =given()
                .header("token",teacher_token)
                .header("user-id",teacher_id)
                .header("user-type","teacher")
                .when()
                .get(Routes.tokenUrl);
        return response;
    }



}
