package tweettest;

import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.io.FileNotFoundException;
import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI() {
        this.tweetAPIClient = new TweetAPIClient();
    }
    // hello

    //************ azul

    @Test
    public void testUserCanTweetSuccessfully() {
        // User sent a tweet tweet
        String tweet = "This is my first tweet" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        System.out.println(response.extract().body().asPrettyString());
        // Verify that the tweet is successful
        response.statusCode(200);
        // Verity tweet value
        String actualTweet = response.extract().body().path("text");
        // Long id= response.extract().body().path("id");
        //System.out.println(id);
        Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow() {
        // User sent a tweet
        String tweet = "I'm learning Rest API using Rest Assured and my second Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        String expectedMessage = "Status is a duplicate.";
        String actualTweet = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet is match");
        Assert.assertNotEquals("403", 200);
    }

    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow2() {
        // User sent a tweet
        String tweet = "I'm learning Rest API using Rest Assured and my second Tweet Same Tweet";
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet is successful
        response.statusCode(403);
        // Verity Retweet
        System.out.println(response.extract().body().asPrettyString());
        int expectedMessage = 187;
        int actualTweet = response.extract().body().path("errors[0].code");
        Assert.assertEquals(actualTweet, expectedMessage, "Tweet is match");
        Assert.assertNotEquals("403", 200);
    }


    @Test
    public void testDeleteTweet() {
        String tweet = "I'm learning Rest API using Rest Assured and my second Tweet Same Tweet";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteTweet(1379440056599990277L);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    }


    @Test
    public void testResponseTime() {
        ValidatableResponse response = this.tweetAPIClient.responseTime();
    }

    @Test
    public void testHeaderValue() {
        this.tweetAPIClient.headerValue();
    }

    @Test
    public void testPropertyFromResponse() {
        //1. User send a tweet
        String tweet = "We are learning Rest API Automation with WeekdaysEvening_Selenium_NY_Summer2020" + UUID.randomUUID().toString();
        ValidatableResponse response = this.tweetAPIClient.createTweet(tweet);
        //2. Verify that the tweet was successful
        // response.statusCode(200);

        //this.tweetAPIClient.checkProperty();
        //JsonPath pathEvaluator=response.;
        //System.out.println(response.extract().body().asPrettyString());
        System.out.println(response.extract().body().asPrettyString().contains("id"));

        //String actualTweet = response.extract().body().path("text");
        //Assert.assertEquals(actualTweet, tweet, "Tweet is not match");
    }

    @Test
    public void getTimelineTest() {
        String tweet = "This is my first tweetea6ad6e4-cd84-4302-b608-3de29005e684";
        ValidatableResponse response = this.tweetAPIClient.getTimeline();
        System.out.println(response.extract().body().asPrettyString());

        response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    } @Test
    public void getTimelineTest1() {
        String tweet = "This is my first tweet67233cb6-4042-4b21-b330-192177952926";
        ValidatableResponse response = this.tweetAPIClient.getTimeline1();
        System.out.println(response.extract().body().asPrettyString());

//        response.statusCode(200);
//        String actualTweet = response.extract().body().path("text");
//        Assert.assertEquals(tweet, actualTweet);
    }

    @Test
    public void testPostFavoriteTweet() {
        ValidatableResponse response = this.tweetAPIClient.postFavorite(1379434456721674243l);
        System.out.println(response.extract().body().asPrettyString());
        String actualList = response.extract().body().path("text");
        Assert.assertEquals(actualList,"","screen name doesn't match");

    }

    @Test
    public void testFavoriteDelete() {
        ValidatableResponse response = this.tweetAPIClient.postDeleteFavorite(1379434456721674243l);
        System.out.println(response.extract().body().asPrettyString());
        String actualList = response.extract().body().path("text");
        Assert.assertEquals(actualList,"","screen name doesn't match");


    }

    @Test
    public void testPostUnReTweet() {
        // User sent a tweet tweet
        String unretweet="This is my first tweet67233cb6-4042-4b21-b330-192177952926";
        ValidatableResponse response = this.tweetAPIClient.postUnRetweet(1379434456721674243l);
        System.out.println(response.extract().body().asPrettyString());
        //response.statusCode(200);
        String actualTweet = response.extract().body().path("text");
        Assert.assertEquals(actualTweet,unretweet);
    }


//======================================

    @Test
    public void testPosMessage() throws FileNotFoundException {
       // String message="";
        ValidatableResponse response = this.tweetAPIClient.postDirectMessages();
        response.statusCode(200);
        System.out.println(response.extract().body().asPrettyString());
//        String actualTweet = response.extract().body().path("text");
//        Assert.assertEquals(actualTweet,message);
    }
//    ==============================================================get list
    @Test
    public void testGetFrendList(){
    String screenName="DALAL";
    ValidatableResponse response = this.tweetAPIClient.getFreindList(screenName);
    System.out.println(response.extract().body().asPrettyString());
   // String actualTweet = response.extract().body().path("");
   // Assert.assertEquals(actualTweet,screenName);
    }
    @Test
    public void testPostList(){
    String screenName="lamia";
    ValidatableResponse response = this.tweetAPIClient.creatFreindList(screenName);
    System.out.println(response.extract().body().asPrettyString());
        int expectedList = 85;
        int actualList = response.extract().body().path("errors[0].code");
     Assert.assertEquals(actualList,expectedList,"screen name doesn't match");

        }
    @Test
    public void testDelete() {
        String tweet = "DALAL";
        ValidatableResponse deleteResponse = this.tweetAPIClient.deleteMemeberlIST(1380478276842446854l);
        deleteResponse.statusCode(200);
        String actualTweet = deleteResponse.extract().body().path("text");
        Assert.assertEquals(tweet, actualTweet);

    }
        @Test
    public void testPostListMember(){
    String name= "Vice President Kamala Harris";
    ValidatableResponse response = this.tweetAPIClient.creatListMember(1380553288358236164l, name);
    System.out.println(response.extract().body().asPrettyString());
//        int expectedList = 25;
//        int actualList = response.extract().body().path("errors[0].code");
//     Assert.assertEquals(actualList,expectedList,"screen name doesn't match");
       }
//=========================================
    @Test
    public void testPostListSubscriber(){
    ValidatableResponse response = this.tweetAPIClient.creatListSubscriber(1380478276842446854l);
    System.out.println(response.extract().body().asPrettyString());
    int expectedList = 34;
    int actualList = response.extract().body().path("errors[0].code");
    Assert.assertEquals(actualList,expectedList,"screen name doesn't match");
}
    @Test
    public void testGetListSubscriber(){
    ValidatableResponse response = this.tweetAPIClient.getListSubscriber(1380478276842446854l);
    System.out.println(response.extract().body().asPrettyString());
    int expectedList = 112;
    int actualList = response.extract().body().path("errors[0].code");
    Assert.assertEquals(actualList,expectedList,"screen name doesn't match");
}
    @Test
    public void testGetListShow(){
    ValidatableResponse response = this.tweetAPIClient.getListShow(1380478276842446854l);
    System.out.println(response.extract().body().asPrettyString());
    int expectedList = 112;
    int actualList = response.extract().body().path("errors[0].code");
    Assert.assertEquals(actualList,expectedList,"screen name doesn't match");
}
    @Test
    public void testDestroyMembersAll() {
        String slug = "POTUS";
        ValidatableResponse deleteResponse = this.tweetAPIClient.destroyMembersAll(1380553288358236164l,"POTUS");
       // deleteResponse.statusCode(200);
        String actualList = deleteResponse.extract().body().path("text");
        Assert.assertEquals(actualList, slug,"screen name doesn't match");

    }
//    =========================
@Test
public void testPostStatus() {
    ValidatableResponse response = this.tweetAPIClient.postStatus("Hi this is me, wish you a positive day!");
    System.out.println(response.extract().body().asPrettyString());
    String actualList = response.extract().body().path("text");
    Assert.assertEquals(actualList,"Hi this is me, wish you a positive day!","screen name doesn't match");
}

    @Test
    public void testGetStatus() {
        ValidatableResponse response = this.tweetAPIClient.getStatues("1380889792762806275");
        System.out.println(response.extract().body().asPrettyString());
        String actualList = response.extract().body().path("text");
        Assert.assertEquals(actualList,"Hi this is Lamia, have a positive day!","screen name doesn't match");
    }

    @Test
    public void testDeleteStatus() {
        ValidatableResponse response = this.tweetAPIClient.deleteStatus("1380889221167259651");
        System.out.println(response.extract().body().asPrettyString());
        String actualList = response.extract().body().path("text");
        Assert.assertEquals(actualList,"Hi this is me, how are you?","screen name doesn't match");
    }






}
