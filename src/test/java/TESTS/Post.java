package TESTS;
import CLASSES.POSTS;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
@RunWith(DataProviderRunner.class)
public class Post {
    @DataProvider
    public static Object[]IDS(){
        return new Object[]{"1","2","5","80","100","45","55","65"};
    }
    @Test
    public void GET_ALL_POSTS(){
        given().when().get("http://jsonplaceholder.typicode.com/posts").then().log().body();
    }
    @Test
    @UseDataProvider("IDS")
    public void GET_POST_BY_ID(String Id){
        given().when().pathParams("ID",Id).get("http://jsonplaceholder.typicode.com/posts/{ID}").then().log().body()
                .assertThat().body("id",equalTo(Integer.parseInt(Id)));
    }
    @Test
    public void POST_NEW_POST(){
        POSTS posts=new POSTS();
        posts.setUserid(1);posts.setTitle("TTTTbbbbbbbbbbbbbb");posts.setBody("1212121212121hhhhhhhhhhhhhhhhh");
        given().when().body(posts).log().body().post("http://jsonplaceholder.typicode.com/posts").then().log().body()
               .assertThat().body("id",not(isEmptyOrNullString())).contentType(ContentType.JSON).statusCode(201);
    }
    @Test
    @UseDataProvider("IDS")
    public void UPDATE_POST_BY_ID(String Id){
        POSTS posts=new POSTS();
        posts.setUserid(1);posts.setTitle("TTTTbbbbbbbbbbbbbb");posts.setBody("1212121212121hhhhhhhhhhhhhhhhh");
        given().when().pathParams("ID",Id).body(posts).log().body().put("http://jsonplaceholder.typicode.com/posts/{ID}").then().log().body()
                .assertThat().body("id",equalTo(Integer.parseInt(Id))).contentType(ContentType.JSON).statusCode(200);
    }
    @Test
    @UseDataProvider("IDS")
    public void DELETE_POST_BY_ID(String Id){
        given().when().pathParams("ID",Id).delete("http://jsonplaceholder.typicode.com/posts/{ID}").then().log().body()
                .assertThat().body("id",isEmptyOrNullString());
    }
}
