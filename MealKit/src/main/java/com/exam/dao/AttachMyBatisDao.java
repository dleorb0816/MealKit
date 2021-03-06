package com.exam.dao;

import com.exam.mapper.AttachMapper;
import com.exam.vo.AttachVo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AttachMyBatisDao {

	private static AttachMyBatisDao instance = new AttachMyBatisDao();
	
	public static AttachMyBatisDao getInstance() {
		return instance;
	}
	
	/////////////////////////////////////////////////
	
	private SqlSessionFactory sqlSessionFactory;

	private AttachMyBatisDao() {
		sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
	}
	
	
	public void insertAttach(AttachVo attachVo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.insertAttach(attachVo);
		}
	}
	
	
	public AttachVo getAttachByNum(int num) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			AttachVo attachVo = mapper.getAttachByNum(num);
			return attachVo;
		}
	}
	
	public List<AttachVo> getAttachesByNoNum(int noNum) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			List<AttachVo> list = mapper.getAttachesByNoNum(noNum);
			return list;
		}
	}
	
	public void deleteAttachesByNoNum(int noNum) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.deleteAttachesByNoNum(noNum);
		}
	}
	
	public void deleteAttachByNum(int num) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			AttachMapper mapper = sqlSession.getMapper(AttachMapper.class);
			mapper.deleteAttachByNum(num);
		}
	}
}



