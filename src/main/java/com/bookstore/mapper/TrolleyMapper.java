package com.bookstore.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Trolley;

@Repository
public interface TrolleyMapper {
	
	public int insertTrolley(@Param("user_name")String userName, 
			@Param("isbn")String isbn, @Param("degree")int degree, @Param("num")int num);
	
	public int deleteTrolley(@Param("user_name")String userName, 
			@Param("isbn")String isbn, @Param("degree")int degree);
	
	public int updateTrolley(@Param("user_name")String userName, 
			@Param("isbn")String isbn, @Param("degree")int degree, @Param("num")int num);
	
	public ArrayList<Trolley> selectTrolley(String userName);

}
