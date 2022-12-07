package api;

import org.testng.Assert;
import org.testng.annotations.Test;

import javax.security.auth.login.AccountLockedException;
import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    @Test
    public void checkAvatarAndIdTest() {
        Specification.InstallSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());
        List<UserData> users = given()
                .when()
                .get(URL + "api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData.class);

         users.forEach(x-> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));


         Assert.assertTrue(users.stream().allMatch(x->x.getEmail().endsWith("@reqres.in")));


        List<String> avatars = users.stream().map(UserData::getAvatar).toList();
        List<String> ids = users.stream().map(x -> x.getId().toString()).toList();
        for(int i= 0; i<avatars.size(); i++){
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));



        }
    }

    @Test
    public void successRegister() {

        Specification.InstallSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());
        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";
        Register user = new Register("eve.holt@reqres.in", "pistol");
        SuccessReg successReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assert.assertNotNull(successReg.getId());
        Assert.assertNotNull(successReg.getToken());
        Assert.assertEquals(id, successReg.getId());
        Assert.assertEquals(token, successReg.getToken());
    }
    @Test
    public void unSuccessUserRegTest(){
        Specification.InstallSpecification(Specification.requestSpec(URL), Specification.responseSpecError400());
        Register user = new Register("sydney@fife", "-");
   unSuccessReg unSuccessReg = given()
           .body(user)
           .post("/api/register")
           .then().log().all()
           .extract().as(api.unSuccessReg.class);
   Assert.assertEquals("Missing password",unSuccessReg.getError());
    }
    @Test
    public void sortedTearsTest(){
        Specification.InstallSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());
        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data" , ColorsData.class);
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList());
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(sortedYears, years);
        System.out.println(years);
        System.out.println(sortedYears);

    }
    @Test
    public void deleteUserTest(){
        Specification.InstallSpecification(Specification.requestSpec(URL), Specification.responseSpecUniqe(240));
        given()
                .when()
                .delete( "api/users/2")
                .then().log().all();

    }
    @Test
    public void timeTest(){
        Specification.InstallSpecification(Specification.requestSpec(URL), Specification.responseSpecOK200());
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);
        String regex = "(.{5})$";
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex,"");
        Assert.assertEquals(currentTime, response.getUpdateAt();


}

