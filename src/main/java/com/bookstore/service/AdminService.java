package com.bookstore.service;

import com.bookstore.message.ResponseMes;

public interface AdminService {
	/**根据账号删除用户
	 * @param userName 账号
	 * @return 成功或失败信息
	 */
	public ResponseMes deleteUser(String userName);

	/**管理员修改userName的密码
	 * @param userName 账号
	 * @param password 新密码
	 * @return 成功或失败信息
	 */
	public ResponseMes modifyUserPwd(String userName, String password);

	/**管理员将书籍信息插入数据库
	 * @param isbn 书籍的ISBN
	 * @param title 书的名字
	 * @param author 书的作者
	 * @param publisher 出版社
	 * @param summary 摘要
	 * @param imgUrl 图像URL
	 * @param original_price 原价
	 * @param degree 新旧程度(0,1,2)
	 * @param num 数量
	 * @return 新增书籍成功或失败信息
	 */
	public ResponseMes addBook(String isbn, String title, String author, String publisher, String summary,
			String imgUrl, double original_price, int degree, int num);

	/**根据ISBN和degree删除书籍
	 * @param isbn 书籍的ISBN
	 * @param degree 新旧程度
	 * @return 删除书籍的成功或失败信息
	 */
	public ResponseMes deleteBook(String isbn,int degree);
	
	public ResponseMes updateBookInfo(String isbn,int degree,int num,Double actualPrice);

}
