package com.eussi.dao;

import org.unitils.spring.annotation.SpringBean;

/**
 * Post的DAO类
 * 
 */
public class PostDaoTest extends BaseDaoTest {

	@SpringBean("postDao")
	private PostDao postDao;
}
