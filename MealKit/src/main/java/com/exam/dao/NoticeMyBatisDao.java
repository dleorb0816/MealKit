package com.exam.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONArray;

import com.exam.mapper.FileMapper;
import com.exam.mapper.NoticeMapper;
import com.exam.vo.FileVo;
import com.exam.vo.NoticeVo;

public class NoticeMyBatisDao {

	private static NoticeMyBatisDao instance = new NoticeMyBatisDao();
	
	public static NoticeMyBatisDao getInstance() {
		return instance;
	}
	
	/////////////////////////////////////////////////
	
	private SqlSessionFactory sqlSessionFactory;

	private NoticeMyBatisDao() {
		sqlSessionFactory = MyBatisUtils.getSqlSessionFactory();
	}
	
	
	// �ֱ۾��� �޼���
	public void addNotice(NoticeVo noticeVo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			mapper.addNotice(noticeVo);
		}
	}
	
	
	public NoticeVo getNoticeByNum(int num) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			NoticeVo noticeVo = mapper.getNoticeByNum(num);
			return noticeVo;
		}
	}
	
	public void updateReadcount(int num) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			mapper.updateReadcount(num);
		}
	}
	
	// ��ü�۰��� ��������
	public int getCountAll() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			int count = mapper.getCountAll();
			return count;
		}
	}
	
	// �˻�� ������ �۰��� ��������
	public int getCountBySearch(String category, String search) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			return mapper.getCountBySearch(category, search);
		}
	}
	
	public List<NoticeVo> getNotices(int startRow, int pageSize) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			List<NoticeVo> list = mapper.getNotices(startRow, pageSize);
			return list;
		}
	}
	
	public List<NoticeVo> getNoticesBySearch(int startRow, int pageSize, String category, String search) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			return mapper.getNoticesBySearch(startRow, pageSize, category, search);
		}
	}
	
	public void updateBoard(NoticeVo noticeVo) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			mapper.updateBoard(noticeVo);
		}
	}
	
	public void deleteNoticeByNum(int num) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			mapper.deleteNoticeByNum(num);
		}
	}
	
	public void deleteAll() {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			mapper.deleteAll();
		}
	}
	
	// ��۾��� �޼���
	public boolean updateAndAddReply(NoticeVo noticeVo) {
		SqlSession sqlSession = null;
		try {
			// Ʈ����� ������ ó���ϱ� ���ؼ� ����Ŀ������ ������
			sqlSession = sqlSessionFactory.openSession(false); // false�� ����Ŀ��
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			
			// ��� insert �ϱ� ���� ���� �۱׷� ���� ���� �����ϱ�
			mapper.updateReSeq(noticeVo.getReRef(), noticeVo.getReSeq());
			
			// ��ۿ� �˸��� ������ VO�� ����
			noticeVo.setReLev(noticeVo.getReLev() + 1);
			noticeVo.setReSeq(noticeVo.getReSeq() + 1);
			
			// ��� insert �ϱ�
			mapper.addNotice(noticeVo);
			
			sqlSession.commit(); // Ŀ���ϱ�
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback(); // �ѹ��ϱ�
			return false;
		} finally {
			sqlSession.close(); // sqlSession �ݱ�
		}
	}
	
//	public JSONArray getCommentsJSON() {
//		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
//			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
//			return mapper.getCommentsJSON();
//		}
//	}
//	
//	public void deleteComment(int num, int lev) {
//		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
//			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
//			mapper.deleteComment(num, lev);
//		}
//	}
	
	public void updateReSeq(int ref, int seq) {
		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
			mapper.updateReSeq(ref, seq);
		}
	}
	
//	// notice ���̺��� attach ���̺� ���� �ܺ������ؼ� ��������
//	public NoticeVo getNoticeAndAttaches(int num) {
//		try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
//			NoticeMapper mapper = sqlSession.getMapper(NoticeMapper.class);
//			return mapper.getNoticeAndAttaches(num);
//		}
//	}
	
	
	
	
//	public static void main(String[] args) {
//		NoticeMyBatisDao dao = NoticeMyBatisDao.getInstance();
//		
//		List<Integer> numList = new ArrayList<>();
//		numList.add(1014);
//		numList.add(1013);
//		numList.add(1010);
//		
//		//List<NoticeVo> noticeList = dao.getNoticesByNums(numList);
//		List<NoticeVo> noticeList = dao.getNoticesByNums(1014, 1013, 1010);
//		for (NoticeVo noticeVo : noticeList) {
//			System.out.println(noticeVo);
//		}
//		
//	}
	
}


