package qa.seanqagroup.learningApp.tests.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.servlet.config.MvcNamespaceHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import qa.seanqagroup.learningApp.LearningAppApplication;
import qa.seanqagroup.learningApp.controller.UserCreationController;
import qa.seanqagroup.learningApp.exceptions.ResourceNotFoundException;
import qa.seanqagroup.learningApp.model.User;
import qa.seanqagroup.learningApp.model.enums.E_UserType;
import qa.seanqagroup.learningApp.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {LearningAppApplication.class})

@DataJpaTest
public class UserTestingTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepo;
	
	private static ExtentHtmlReporter htmlReporter;
	private static ExtentReports extent = new ExtentReports();
	private ExtentTest test;
	
	private static final String className = "UserTesting.html";
	@BeforeClass
	public static void setUpBeforeClass() {
		htmlReporter = new ExtentHtmlReporter("C:\\Users\\Admin\\Desktop\\"+className);
		extent.attachReporter(htmlReporter);
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception{
		extent.flush();
	}
	
	@Test
	public void testCon() {
		
		User testUser = new User();
		entityManager.persist(testUser);
		entityManager.flush();
		test = extent.createTest("Test User Creation and retrieval from database by ID");
		try {
		assertTrue(userRepo.findById(testUser.getUserId()).isPresent());
		test.pass(MarkupHelper.createLabel("User(default constructor) found by ID", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User Not found by ID", ExtentColor.RED));

		}
		finally {
		entityManager.clear();
		}

		
				
	}
	@Test
	public void testCon2() {
		
	
		User testUser2 = new User("a","a","p",E_UserType.LEARNER,"a@a.a");
		entityManager.persist(testUser2);
		entityManager.flush();
		test = extent.createTest("Test User Creation 2");
		try {
		assertTrue(userRepo.findById(testUser2.getUserId()).isPresent());
		test.pass(MarkupHelper.createLabel("User found by ID with details name:a, email: a@a.a", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User Not found by ID", ExtentColor.RED));

		}
		finally {
		entityManager.clear();
		}
				
	}
	@Test
	public void findUsingEmail() {
		
	
		User testUser2 = new User("a","a","p",E_UserType.LEARNER,"a@a.a");
		entityManager.persist(testUser2);
		entityManager.flush();
		test = extent.createTest("Test User retrieval from database by email");
		try {
		assertEquals("Person Not Real",userRepo.findByEmail(testUser2.getEmail()).getFirstName(),"a");
		test.pass(MarkupHelper.createLabel("User found by Email with email: a@a.a", ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User Not found by Email", ExtentColor.RED));

		}
		finally {
			entityManager.clear();
		}

				
	}
	@Test
	public void testGetSet() {
		
	
		User testUser = new User();
		testUser.setFirstName("b");
		testUser.setLastName("b");
		testUser.setEmail("b@b.b");
		testUser.setPassword("p");
		testUser.setUserType(E_UserType.LEARNER);
		entityManager.persist(testUser);
		entityManager.flush();
		
		User persistedUser = userRepo.findByEmail("b@b.b");
		test = extent.createTest("Test User getters and setters");
		try {
		assertEquals("Person Not Real",persistedUser.getFirstName(),"b");
		assertEquals("Person Not Real",persistedUser.getLastName(),"b");
		assertEquals("Person Not Real",persistedUser.getEmail(),"b@b.b");
		assertEquals("Person Not Real",persistedUser.getPassword(),"p");
		assertEquals("Person Not Real",persistedUser.getUserType(),E_UserType.LEARNER);
		test.pass(MarkupHelper.createLabel("User getters and setters work with details: name:b, lastname:b, email:b, password:p, userType:"+E_UserType.LEARNER, ExtentColor.GREEN));
		}catch(AssertionError e) {
			test.fail(MarkupHelper.createLabel("User getters and setters don't work", ExtentColor.RED));
		}
		finally {
			entityManager.clear();
		}


}	

}
