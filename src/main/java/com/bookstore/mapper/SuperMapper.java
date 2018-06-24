package com.bookstore.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bookstore.entity.Admin;

@Repository
public interface SuperMapper {
	/**
	 * 根据管理员账号获得管理员
	 * @param adminName 管理员账号
	 * @return 管理员实体对象
	 */
	public Admin getAdminByName(String adminName);

	/**
	 * 插入管理员
	 * @param admin 管理员实体对象
	 * @return 影响的记录数,成功返回1,失败返回0
	 */
	public int insertAdmin(Admin admin);

	/**
	 * 根据管理员账号删除管理员
	 * @param adminName 管理员账号
	 * @return 成功返回1,失败返回0
	 */
	public int deleteAdmin(String adminName);
	
	/**
	 * 列出所有管理员账号
	 * @return
	 */
	public ArrayList<Admin> listAdmins();
}
