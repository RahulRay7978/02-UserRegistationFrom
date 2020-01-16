package com.nt.service;

import com.nt.domain.UserDetails;
import com.nt.domain.UserLoginDetails;
import com.nt.domain.UserUnlockDetailsBean;
import com.nt.entity.UserRegistrationDetailsEntity;

public interface UserMngtService {
			public boolean saveUserDetails(UserDetails bean) throws Exception;
			public boolean checkValidEmailId(UserUnlockDetailsBean detailsBean);
			public UserRegistrationDetailsEntity checkCredintials(UserLoginDetails details);
			public boolean fetchRecordByEmailId(String email);
			public String getRecordByEmailId(String email);
}
