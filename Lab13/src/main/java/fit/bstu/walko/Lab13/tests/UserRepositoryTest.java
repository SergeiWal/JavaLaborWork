package fit.bstu.walko.Lab13.tests;

import fit.bstu.walko.Lab13.Hasher;
import fit.bstu.walko.Lab13.models.User;
import fit.bstu.walko.Lab13.repository.Repository;
import fit.bstu.walko.Lab13.repository.UserRepository;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Calendar;

import static org.testng.Assert.assertTrue;

public class UserRepositoryTest {

    User user;
    UserRepository repository;

    @BeforeSuite
    public void startTests()
    {
        System.out.println("=================== Tests start ==========================");
    }

    @BeforeTest()
    public void clientInitilization(){
        user = new User();
        user.setName("Gena");
        user.setLogin("Gena");
        user.setPassword(Hasher.getHash("1122"));
        user.setRole("User");
        try {
            repository = new UserRepository();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addUserTest(){
        repository.add(user);
        User newUser = repository.getById(user.getId());
        assertTrue(newUser != null,
                "Test 1 failed: error in add user>");
    }

    @Test
    public void removeUserTest(){
        repository.remove(user);
        User newUser = repository.getById(user.getId());
        assertTrue(newUser == null,
                "Test 2 failed: error in remove user>");
    }




}
