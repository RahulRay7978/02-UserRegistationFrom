package com.nt.repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nt.entity.UserRegistrationDetailsEntity;

@Repository
public interface UserRegistartionDetailsRepositry extends JpaRepository<UserRegistrationDetailsEntity, Integer> {
	
	@Query(value="FROM UserRegistrationDetailsEntity WHERE uEmail=:email AND uPwd=:pwd")
	public UserRegistrationDetailsEntity getRecordByEmailAndPwd(String email,String pwd);
	@Query(value="SELECT uPwd FROM UserRegistrationDetailsEntity WHERE uEmail=:email")
	public String getRecordByEmailId(String email);
	@Query(value="from UserRegistrationDetailsEntity where uEmail=:email")
	public UserRegistrationDetailsEntity getByuEmail(String email);
}