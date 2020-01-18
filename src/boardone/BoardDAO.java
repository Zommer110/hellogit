package boardone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class BoardDAO {
	private static BoardDAO instance = null;
	private BoardDAO() {
		
	}
	public static BoardDAO getInstance() {
		if(instance == null) {
			synchronized (BoardDAO.class) {
				instance = new BoardDAO();
			}
		}
		return instance;
	}
	
	public void insertArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int num = article.getNum();
		int depth = article.getDepth();
		int step = article.getStep();
		int ref = article.getRef();
		int number = 0; //ref의 값을 정하려고 임시로 만들어 놓은 변수
		String sql = "";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select max(ref) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) number = rs.getInt(1) + 1;//ref의 값을 정해줌(새글일 경우이고 1번작업) max(ref) + 1
			else number = 1;
			if(num != 0) {//답변글일경우
				sql = "update board set step = step+1 where ref = ? and step > ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();
				step = step + 1;//답글일 경우 3번작업 - 나의 step + 1, depth + 1
				depth = depth + 1;
			} else { //새글일 경우
				ref = number;
				step = 0;
				depth = 0;
			} // 쿼리를 작성 - 글쓰기 작업
			sql = "insert into board(num, writer, email, subject, pass, regdate, ref, step, depth, content, ip) values(board_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getEmail());
			pstmt.setString(3, article.getSubject());
			pstmt.setString(4, article.getPass());
			pstmt.setTimestamp(5, article.getRegdate());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, depth);
			pstmt.setString(9, article.getContent());
			pstmt.setString(10, article.getIp());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
	}

	public int getArticleCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
		return x;
	}
	
	public List<BoardVO> getArticles(int start, int end){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> articleList = null;
		try {
			conn = ConnUtil.getConnection();			
			pstmt = conn.prepareStatement("select * from (select rownum rnum, num, writer, email, subject, pass, regdate, readcount, ref, step, depth, content, ip from (select * from board order by ref desc, step asc)) where rnum>=? and rnum<=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				articleList = new ArrayList<BoardVO>(end-start+1);
				do {
					BoardVO article = new BoardVO();
					article.setNum(rs.getInt("num"));
					article.setWriter(rs.getString("writer"));
					article.setEmail(rs.getString("email"));
					article.setSubject(rs.getString("subject"));
					article.setPass(rs.getString("pass"));
					article.setRegdate(rs.getTimestamp("regdate"));
					article.setReadcount(rs.getInt("readcount"));
					article.setRef(rs.getInt("ref"));
					article.setStep(rs.getInt("step"));
					article.setDepth(rs.getInt("depth"));
					article.setContent(rs.getString("content"));
					article.setIp(rs.getString("ip"));
					articleList.add(article);
				} while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
		return articleList;
	}
	
	public BoardVO getArticle(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO article = null;
		try {
			conn = ConnUtil.getConnection();
			upCount(num);
			pstmt = conn.prepareStatement("select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				article = new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPass(rs.getString("pass"));
				article.setRegdate(rs.getTimestamp("regdate"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setStep(rs.getInt("step"));
				article.setDepth(rs.getInt("depth"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip"));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
		return article;
	}
	
	public void upCount(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("update board set readcount = readcount + 1 where num = ?");
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
	}
	
	public int updateArticle(BoardVO article) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		String dbpasswd = getPass(article.getNum());
		String sql = "";
		int result = -1;
		try {
			if(dbpasswd.equals(article.getPass())) {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("update board set writer=?, email=?, subject=?, content=? where num=?");
				pstmt.setString(1, article.getWriter());
				pstmt.setString(2, article.getEmail());
				pstmt.setString(3, article.getSubject());
				pstmt.setString(4, article.getContent());
				pstmt.setInt(5, article.getNum());
				pstmt.executeUpdate();
				result = 1;//수정성공
			} else {
				result = 0;//수정 실패(비밀번호 틀림)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
		return result;
	}
	
	public String getPass(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String dbpasswd ="";
		try {
			conn = ConnUtil.getConnection();
			pstmt = conn.prepareStatement("select pass from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dbpasswd = rs.getString("pass");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) try { rs.close(); } catch (SQLException ex) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
		return dbpasswd;
	}
	
	public int deleteArticle(int num, String pass) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		String dbpasswd = getPass(num);
		int result = -1;
		try {
			if(dbpasswd.equals(pass)) {
				conn = ConnUtil.getConnection();
				pstmt = conn.prepareStatement("delete from board where num = ?");
				pstmt.setInt(1, num);
				pstmt.executeUpdate();
				result = 1;//글삭제 성공
			} else {
				result = 0; //비밀번호 틀림
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt != null) try { pstmt.close(); } catch (SQLException ex) {}
			if(conn != null) try { conn.close(); } catch (SQLException ex) {}
		}
		return result;
	}
}
