package TESTS;

import CLASSES.COMMENTS;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
@RunWith(DataProviderRunner.class)
public class Comment {
    @DataProvider
    public static Object[]IDS(){
        return new Object[]{"1","2","5","80","100","45","55","65"};
    }
    @Test
    public void GET_ALL_COMMENTS(){
        given().when().get("http://jsonplaceholder.typicode.com/comments").then().log().body();
    }
    @Test
    @UseDataProvider("IDS")
    public void GET_ALL_COMMENTS_BY_POST_ID(String ID){
        given().when().pathParams("ID",ID).get("http://jsonplaceholder.typicode.com/comments?postId={ID}").then().log().body()
                .assertThat().body("[0].postId",equalTo(Integer.parseInt(ID))).statusCode(200).contentType(ContentType.JSON);
    }
    @Test
    @UseDataProvider("IDS")
    public void GET_COMMENT_BY_ID(String ID){
        given().when().pathParams("ID",ID).get("http://jsonplaceholder.typicode.com/comments/{ID}").then().log().body()
                .assertThat().body("id",equalTo(Integer.parseInt(ID))).statusCode(200).contentType(ContentType.JSON);
    }
    @Test
    @UseDataProvider("IDS")
    public void POST_COMMENT( String Id){
        COMMENTS comments=new COMMENTS();
        comments.setPostId(Integer.parseInt(Id));comments.setBody("111111111155555");comments.setEmail("Abdo@gmail.com");comments.setName("ABDo");
        given().when().body(comments).post("http://jsonplaceholder.typicode.com/comments").then().statusCode(201).contentType(ContentType.JSON)
                .assertThat().body("id",not(isEmptyOrNullString()));
    }
    @Test
    @UseDataProvider("IDS")
    public void UPDATE_COMMENT( String Id){
        COMMENTS comments=new COMMENTS();
        comments.setPostId(Integer.parseInt(Id));comments.setBody("111111111155555");comments.setEmail("Abdo@gmail.com");comments.setName("ABDo");
        given().when().pathParams("id",Id).body(comments).put("http://jsonplaceholder.typicode.com/comments/{id}").then().statusCode(200).contentType(ContentType.JSON)
                .assertThat().body("id",equalTo(Integer.parseInt(Id)));
    }
}
