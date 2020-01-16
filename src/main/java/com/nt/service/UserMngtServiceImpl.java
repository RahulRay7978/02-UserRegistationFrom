package com.nt.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.domain.UserDetails;
import com.nt.domain.UserLoginDetails;
import com.nt.domain.UserUnlockDetailsBean;
import com.nt.entity.UserRegistrationDetailsEntity;
import com.nt.repositry.UserRegistartionDetailsRepositry;
import com.nt.utility.MailUtility;

@Service
public class UserMngtServiceImpl implements UserMngtService {
	@Autowired
	private UserRegistartionDetailsRepositry userRepo;
	@Autowired
	private MailUtility mailUtil;

	/**
	 * This method for saveing user details.
	 */
	@Override
	public boolean saveUserDetails(UserDetails bean) throws Exception {
		UserRegistrationDetailsEntity entity = new UserRegistrationDetailsEntity();
		bean.setStatus("Unlock");
		bean.setuPwd(getAlphaNumericString(6));
		BeanUtils.copyProperties(bean, entity);
		UserRegistrationDetailsEntity result = userRepo.save(entity);
		if (result.getUserId() > 0) {
			sendEmail(bean);
		}
		return result.getUserId() > 0;
	}

	/**
	 * this method for generating random alphanumeric value
	 * 
	 * @param n
	 * @return
	 */
	private String getAlphaNumericString(int n) {

		// length is bounded by 256 Character
		byte[] array = new byte[256];
		new Random().nextBytes(array);

		String randomString = new String(array, Charset.forName("UTF-8"));

		// Create a StringBuffer to store the result
		StringBuffer r = new StringBuffer();

		// remove all spacial char
		String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");

		// Append first 20 alphanumeric characters
		// from the generated random String into the result
		for (int k = 0; k < AlphaNumericString.length(); k++) {

			if (Character.isLetter(AlphaNumericString.charAt(k)) && (n > 0)
					|| Character.isDigit(AlphaNumericString.charAt(k)) && (n > 0)) {

				r.append(AlphaNumericString.charAt(k));
				n--;
			}
		}

		// return the resultant string
		return r.toString();
	}

	public void sendEmail(UserDetails details) throws Exception {
		String fileName = "userTemplate";
		StringBuilder sb = new StringBuilder();
		FileReader reader = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
		while (line != null) {
			if (line.contains("${NAME}")) {
				line = line.replace("${NAME}", details.getuName());
				sb.append(line);
			}
			if (line.contains("${PWD}")) {
				line = line.replace("${PWD}", details.getuPwd());
				sb.append(line);
			}
			if (line.contains("${EMAILID}")) {
				line = line.replace("${EMAILID}", details.getuEmail());
				sb.append(line);
			}
			line = br.readLine();
		}
		mailUtil.sendMail(details.getuEmail(), sb.toString(), "Greetings for the day");
	}// method

	@Override
	public boolean checkValidEmailId(UserUnlockDetailsBean bDetailsBean) {
		UserRegistrationDetailsEntity entity = new UserRegistrationDetailsEntity();
		System.out.println(bDetailsBean.getPwd());
		entity.setuEmail(bDetailsBean.getEmail());
		entity.setuPwd(bDetailsBean.getPwd());

		System.out.println(entity.getuEmail());
		entity = userRepo.getRecordByEmailAndPwd(entity.getuEmail(), entity.getuPwd());
		// System.out.println(entity);
		UserRegistrationDetailsEntity save = null;
		if(entity!=null) {
		if (entity.getUserId() > 0) {
			entity.setStatus("unlock");
			entity.setuPwd(bDetailsBean.getConfirmPwd());
			save = userRepo.save(entity);
		}
		}

		return save.getUserId() > 0;
	}

	public UserRegistrationDetailsEntity checkCredintials(UserLoginDetails details) {
		UserRegistrationDetailsEntity entity = new UserRegistrationDetailsEntity();
		entity.setuEmail(details.getEmail());
		entity.setuPwd(details.getPwd());
		UserRegistrationDetailsEntity recordByEmailAndPwd = userRepo.getRecordByEmailAndPwd(entity.getuEmail(),
				entity.getuPwd());
		return recordByEmailAndPwd;
	}

	/**
	 * This method for fetching record from DB by email id
	 */
	@Override
	public boolean fetchRecordByEmailId(String email) {
		String pwd = userRepo.getRecordByEmailId(email);
		if (pwd != null) {
			try {
				sendEmailToUser(email, pwd);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pwd != null;
	}

	
	
	
	public void sendEmailToUser(String email, String pwd) throws Exception {
		System.out.println("UserMngtServiceImpl.sendEmailToUser()");

		String fileName = "forgetEmailTemplate";
		StringBuilder sb = new StringBuilder();
		FileReader reader = new FileReader(new File(fileName));
		BufferedReader br = new BufferedReader(reader);
		String line = br.readLine();
		while (line != null) {
			if (line.contains("${EMAIL}")) {
				line = line.replace("${EMAIL}", email);
				sb.append(line);
			}
			if (line.contains("${PWD}")) {
				line = line.replace("${PWD}", pwd);
				sb.append(line);
			}
			line = br.readLine();
		}
		mailUtil.sendEmailForPwd(email, sb.toString(), "Your Password");
	}

	@Override
	public String getRecordByEmailId(String email) {
		UserRegistrationDetailsEntity findByuEmail=userRepo.getByuEmail(email);
		if(findByuEmail!=null) {
		return "duplicated";
	}
		return "Unique";
	}
}
