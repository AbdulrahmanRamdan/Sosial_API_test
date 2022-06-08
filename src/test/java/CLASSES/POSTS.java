package CLASSES;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userid","id","title","body"})
public class POSTS {
    private int userid;
    public POSTS(){
    }
    public int getUserid() {
        return userid;
    }
    @JsonProperty
    public void setUserid(int userid) {
        this.userid = userid;
    }

    private String title;
    private String body;


    public String getTitle() {
        return title;
    }
@JsonProperty
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }
    @JsonProperty
    public void setBody(String body) {
        this.body = body;
    }
}
