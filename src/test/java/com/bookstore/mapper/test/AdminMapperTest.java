package com.bookstore.mapper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.entity.Book;
import com.bookstore.entity.BookPrice;
import com.bookstore.entity.User;
import com.bookstore.mapper.AdminMapper;
import com.bookstore.mapper.CommonMapper;
import com.bookstore.utils.BaseJUnit;

public class AdminMapperTest extends BaseJUnit {
	@Autowired
	private AdminMapper adminMapper;
	@Autowired
	private CommonMapper commonMapper;
	
	@Test
	public void testDeleteUser() {
		User user = new User();
		user.setUserName("xiaoxiong");
		user.setPassword("123456");
		user.setPhone("18880207329");
		user.setRealName("张三");
		user.setAddress("湘潭大学琴湖18栋");
		commonMapper.insertUser(user);
		assertEquals(1, adminMapper.deleteUser("xiaoxiong"));
	}

	
	@Test
	public void testModifyUserPwd() {
		assertEquals(1, adminMapper.modifyUserPwd("xiyou", "123456"));
	}

	@Test
	public void testAddBook() {
		Book book = new Book();
		book.setIsbn("9787506391542");
		book.setTitle("我喜欢生命本来的样子");
		book.setAuthor("周国平");
		book.setPublisher("作家出版社");
		book.setSummary(
				"在茫茫宇宙间，每个人都只有一次生存的机会，都是一个独一无二、不可重复的存在。名声、财产、知识等等是身外之物，人人都可求而得之，但没有人能够代替你感受人生。你死之后，没有人能够代替你再活一次。那么仅有一次的人生到底应该怎么度过？周国平说，每个个体要尊重生命本来的样子，当好自然之子。\n"
						+ "当面对爱、逆境、孤独、死亡、变故等人生大命题的时候，人应当保持在什么心理状态，是本书最大的意义所在。希望本书能为在繁杂的世界里找不到头绪的你，建立强大的灵魂世界。\n"
						+ "本书为哲学家周国平先生毕生散文精华，全书采用四色印刷，...");
		book.setOriginalPrice(45.0);
		book.setImgUrl("https://img3.doubanio.com/view/subject/s/public/s29417905.jpg");
		BookPrice bookPrice = new BookPrice();
		bookPrice.setIsbn("9787506391542");
		bookPrice.setDegree(1);
		bookPrice.setActualPrice(10.0);
		bookPrice.setNum(88);
		assertEquals(1, adminMapper.addBook(book, bookPrice));
	}

	
	@Test
	public void testUpdateBookNum() {
		testAddBook();
		assertEquals(1, adminMapper.updateBook("9787506391542", 1, 10,null));
	}
	
	@Test
	public void testDeleteBook() {
		testAddBook();
		assertEquals(1, adminMapper.deleteBook("9787506391542", 1));
	}
}
