package com.eussi.dao;

import org.springframework.stereotype.Repository;

import com.eussi.domain.Post;

/**
 * Post的DAO类
 *
 */
@Repository
public class PostDao extends BaseDao<Post> {

	private static final String GET_PAGED_POSTS = "from Post where Topic.topicId =? order by createTime desc";

	private static final String DELETE_TOPIC_POSTS = "delete from Post where Topic.topicId=?";
	
	public Page getPagedPosts(int topicId, int pageNo, int pageSize) {
		return pagedQuery(GET_PAGED_POSTS,pageNo,pageSize,topicId);
	}
    
	/**
	 * 删除主题下的所有帖子
	 * @param topicId 主题ID
	 */
	public void deleteTopicPosts(int topicId) {
		getHibernateTemplate().bulkUpdate(DELETE_TOPIC_POSTS,topicId);
	}	
}
