package TESTS;

import CLASSES.ALBUMS;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
@RunWith(DataProviderRunner.class)
public class album {
    @DataProvider
    public static Object[]IDS(){
        return new Object[]{"1","2","5","80","100","45","55","65"};
    }
    @Test
    public void GET_ALL_ALBUMS(){
        given().when().log().all().get("http://jsonplaceholder.typicode.com/albums")
                .then().log().body().statusCode(200).contentType(ContentType.JSON)
                .assertThat().body("id",not(isEmptyOrNullString()));
    }
    @Test
    @UseDataProvider("IDS")
    public void GET_ALBUMS_BY_ID(String ID){
        given().when().pathParams("ID",ID).get("http://jsonplaceholder.typicode.com/albums/{ID}")
                .then().log().body().statusCode(200).contentType(ContentType.JSON)
                .assertThat().body("id",equalTo(Integer.parseInt(ID)));
    }
    @Test
    public void POST_ALBUM(){
        ALBUMS albums=new ALBUMS();
        albums.setUserId("2");albums.setTitle("ABDO");
        given().body(albums).when().post("http://jsonplaceholder.typicode.com/albums").then().statusCode(201)
                .contentType(ContentType.JSON).assertThat().body("id",not(isEmptyOrNullString()));
    }
    @Test
    @UseDataProvider("IDS")
    public void Update_ALBUM(String ID){
        ALBUMS albums=new ALBUMS();
        albums.setUserId("2");albums.setTitle("ABDO");
        given().body(albums).pathParams("ID",ID).when().put("http://jsonplaceholder.typicode.com/albums/{ID}").then().statusCode(200)
                .contentType(ContentType.JSON).assertThat().body("id",equalTo(Integer.parseInt(ID)));
    }
}
