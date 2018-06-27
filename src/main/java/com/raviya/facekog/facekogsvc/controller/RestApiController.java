package com.raviya.facekog.facekogsvc.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.raviya.facekog.facekogsvc.UtilBase64Image;
import com.raviya.facekog.facekogsvc.model.FaceResponse;
import com.raviya.facekog.facekogsvc.model.Image;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.rekognition.AmazonRekognition;
import com.amazonaws.services.rekognition.AmazonRekognitionAsyncClient;
import com.amazonaws.services.rekognition.AmazonRekognitionClient;
import com.amazonaws.services.rekognition.AmazonRekognitionClientBuilder;

@RestController
@RequestMapping("/facekog")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	//@Autowired
	//UserService userService; //Service which will do all data retrieval/manipulation work

	
	@RequestMapping(value = "/faces", method = RequestMethod.POST)
	public List<FaceResponse> post(@RequestBody Image image) {
		//System.out.println("/POST request with " + image.toString());
		List<FaceResponse> list = new ArrayList<FaceResponse>();
		// save Image to C:\\server folder
		String path = "/Users/4seasons/Documents/" + image.getName();
		byte[] originalImage = UtilBase64Image.decoder(image.getData(), path);
		BufferedImage img=null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(originalImage));
			BufferedImage croppedImage = img.getSubimage(0, 0, 20, 20);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( croppedImage, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			String base64croppedImg = UtilBase64Image.encodeByteArray(imageInByte);
			
			
			/*FaceResponse(String cropedData,
					 int ageRangeLow,
					 int ageRangeHigh,
					 double smileConfidence,
					 double eyeGlassConfidence,
					 double sunGlassConfidence,
					 double genderFemaleConfidence,
					 double genderMaleConfidence,

					 double happyConfidence,
					 double sadConfidence,
					 double calmConfidence,
					 boolean needToRetake)*/
			
			FaceResponse resp = new FaceResponse(base64croppedImg,
					 3,9,0, 0, 0,99.99,0,99,56,46,false);
			list.add(resp);
			FaceResponse resp1 = new FaceResponse(base64croppedImg,
					 1,2,0, 0, 0, 99.99,0,99,56,46,false);
			list.add(resp1);
			FaceResponse resp2 = new FaceResponse(base64croppedImg,
					 23,29,0, 0, 0, 99.99,0,99,56,46,false);
			list.add(resp2);
			FaceResponse resp3 = new FaceResponse(base64croppedImg,
					 25,30,0, 0, 0, 0,99.99,99,56,46,false);
			list.add(resp3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AWSCredentials awsCredentials = new AWSCredentials() {
			
			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		AWSCredentials aws = new AWSCredentials() {
			
			@Override
			public String getAWSSecretKey() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getAWSAccessKeyId() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
		AmazonRekognition client = AmazonRekognitionClientBuilder
				.standard()
				.withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.build();
		
		return list;
	}
 
	@RequestMapping(value = "/getface", method = RequestMethod.GET)
	public Image get(@RequestParam("name") String name) {
		System.out.println(String.format("/GET info: imageName = %s", name));
		String imagePath = "C:\\server\\" + name;
		String imageBase64 = UtilBase64Image.encoder(imagePath);
		
		if(imageBase64 != null){
			Image image = new Image(name, imageBase64);
			return image;
		}
		return null;
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String gethello() {
		System.out.println(String.format("/GET info: hello "));
		
		return "hello world";
	}
	
	// -------------------Retrieve All Users---------------------------------------------

	/*@RequestMapping(value = "/user/", method = RequestMethod.GET)
	public ResponseEntity<List<String>> listAllUsers() {
		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single User------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@PathVariable("id") long id) {
		logger.info("Fetching User with id {}", id);
		User user = userService.findById(id);
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id 
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a User-------------------------------------------

	@RequestMapping(value = "/faces", method = RequestMethod.POST)
	public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist", user.getName());
			return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
			user.getName() + " already exist."),HttpStatus.CONFLICT);
		}
		userService.saveUser(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	// ------------------- Update a User ------------------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.findById(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());
		currentUser.setAge(user.getAge());
		currentUser.setSalary(user.getSalary());

		userService.updateUser(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a User-----------------------------------------

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.findById(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUserById(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}

	// ------------------- Delete All Users-----------------------------

	@RequestMapping(value = "/user/", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteAllUsers() {
		logger.info("Deleting All Users");

		userService.deleteAllUsers();
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}*/


	
}
