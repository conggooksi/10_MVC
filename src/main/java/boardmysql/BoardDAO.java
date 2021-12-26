package boardmysql;

import java.sql.*;//Connection,PreparedStaement,,,
import javax.sql.*;//DataSource
import javax.naming.*;
import java.util.*;//List, ArrayList

//자바빈, 커넥션 풀 사용
//DAO:비지니스 로직
public class BoardDAO {

	//변수
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
	
	//--------------------------------
	//생성자
	private BoardDAO(){}
	
	//싱글톤 객체 사용 할 것임, 메모리 절약 효과가 있음
	private static BoardDAO dao=new BoardDAO();//객체생성
	
	//jsp에서 dao객체 얻기
	public static BoardDAO getDAO(){
		return dao;
	}
	//--------------------------------
	
	//커넥션 얻기 
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}
	//---------------------------------
	// 원글,답글쓰기
	//---------------------------------
	public void insertBoard(BoardDTO dto){
		Connection con=null;
		
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		
		int number=0;//글 그룹처리 하려고
		
		try{
			con=getCon();//커넥션 얻기
			
			pstmt=con.prepareStatement("select max(num) from board");//최대 글번호 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()){//글이 존재하면
				number=rs.getInt(1)+1;// 최대글번호+1
			}else{//첫번째 글쓰기 일때
				number=1;//ref=number
			}//else-end
			
			if(num != 0){//답글이면
				//답글 끼워 넣기 위치 확보
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
				
				//?값 채우기 
				pstmt.setInt(1, ref);
				pstmt.setInt(2,re_step);
				pstmt.executeUpdate();//쿼리 수행
				
				re_step=re_step+1;
				re_level=re_level+1;
				
			}else{//원글이면
				ref=number;
				re_step=0;
				re_level=0;
			}//else-end
			
			sql="insert into board(writer,subject,pw,"+
			"regdate,ref,re_step,re_level,content,ip) values(?,?,?,NOW(),?,?,?,?,?)";
			
			//NOW() : 년월일 시분초 : mysql
			//curdate() : 년월일  : mysql
			//sysdate  : 년월일 시분초  오라클
			
			
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
			
			// ? 값 채우기 
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getPw());
			//날짜
			pstmt.setInt(4, ref);////조심하세요
			pstmt.setInt(5, re_step);
			pstmt.setInt(6, re_level);
			
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getIp());
			
			pstmt.executeUpdate();//쿼리수행, insert 
		}catch(Exception ex1){
			System.out.println("insertBoard() 예외:"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
				
			}catch(Exception ex2){}
		}//finally-end
	}//insertBoard()-end
	//------------------
	// 글 갯수
	//------------------
	public int getBoardCount(){
		Connection con=null;
		int cnt=0;
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("select count(*) from board");
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cnt=rs.getInt(1);//1은 필드 번호
			}
			
		}catch(Exception ex1){
			System.out.println("getBoardCount() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return cnt;
		
	}//getBoardCount()-end
	//------------------
	//리스트
	//------------------
	public List getList(int start,int cnt){
		List<BoardDTO> list=null;//변수
		Connection con=null;
		try{
			con=getCon();//커넥션 얻기 
			sql="select * from board order by ref desc, re_step asc limit ?,?";
			// limit 시작위치=0부터,갯수
			
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 
			
			pstmt.setInt(1, start-1);//시작 위치
			pstmt.setInt(2, cnt);//글 갯수
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				list=new ArrayList<BoardDTO>();
				do{
					BoardDTO dto=new BoardDTO();//객체생성
					//rs내용 dto넣고, dto는 list에 넣어서 리턴
					
					dto.setNum(rs.getInt("num"));
					dto.setWriter(rs.getString("writer"));
					dto.setSubject(rs.getString("subject"));
					dto.setPw(rs.getString("pw"));
					
					dto.setRegdate(rs.getTimestamp("regdate"));//년월일 시분초
					
					System.out.println("날짜:"+rs.getString("regdate"));
					System.out.println("날짜:"+rs.getDate("regdate"));
					System.out.println("날짜:"+rs.getTimestamp("regdate"));
					
					dto.setReadcount(rs.getInt("readcount"));
					dto.setRef(rs.getInt("ref"));//글 그룹
					dto.setRe_step(rs.getInt("re_step"));//글순서
					dto.setRe_level(rs.getInt("re_level"));//글 깊이 
					
					dto.setContent(rs.getString("content"));//글내용
					dto.setIp(rs.getString("ip"));
					
					list.add(dto);//꼭~~~~~~~~~~~~~
					
				}while(rs.next());
				
			}//if-end
		}catch(Exception ex1){
			System.out.println("getList() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return list;//클라이언트로 리턴한다 
	}//getList()-end
	//---------------------
	// 글내용보기 ,조횟수 증가
	//---------------------
	public BoardDTO getBoard(int num){
		System.out.println("num:"+num);
		
		BoardDTO dto=null;
		Connection con=null;
		try{
			con=getCon();//커넥션 얻기 
			
			//조회수 증가
			sql="update board set readcount=readcount+1 where num="+num;
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();//쿼리 수행
			//--
			//글 내용조기 위한 쿼리문
			pstmt=con.prepareStatement("select * from board where num="+num);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				dto=new BoardDTO();
				
				//rs내용들을 dto에 넣기
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setPw(rs.getString("pw"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
	 		}//if-end
		}catch(Exception ex1){
			System.out.println("getBoard() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return dto;
	}// getBoard()-end
	//---------------------
	// 글수정 : 클라이언트로 보낼것
	//---------------------
	public BoardDTO getUpdate(int num){
		BoardDTO dto=null;
		Connection con=null;
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("select * from board where num="+num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
			 dto=new BoardDTO();
			 
			 //rs내용들을 dto에 넣는다
			 dto.setNum(rs.getInt("num"));
			 dto.setWriter(rs.getString("writer"));
			 dto.setSubject(rs.getString("subject"));
			 dto.setPw(rs.getString("pw"));

			 dto.setRegdate(rs.getTimestamp("regdate"));
			 dto.setReadcount(rs.getInt("readcount"));

			 dto.setRef(rs.getInt("ref"));
			 dto.setRe_step(rs.getInt("re_step"));
			 dto.setRe_level(rs.getInt("re_level"));

			 dto.setContent(rs.getString("content"));
			 dto.setIp(rs.getString("ip"));
			 
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getUpdate() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return dto;//클라이언트 리턴 한다 
	}//getUpdate()-end
	
	//---------------------
	// 글수정 : DB글 수정
	//---------------------
	public int updateBoard(BoardDTO dto){
		String dbpw="";//변수
		int x=-100;
		Connection con=null;
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select pw from board where num=?");
			pstmt.setInt(1, dto.getNum());
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				dbpw=rs.getString("pw");
				if(dbpw.equals(dto.getPw())){//암호가 일치하면 글 수정한다
					sql="update board set writer=?, subject=?, content=? where num=?";
					pstmt=con.prepareStatement(sql);
					
					pstmt.setString(1, dto.getWriter());
					pstmt.setString(2, dto.getSubject());
					pstmt.setString(3, dto.getContent());
					pstmt.setInt(4, dto.getNum());
					
					pstmt.executeUpdate();//쿼리 수행 
					x=1;//정상적으로 수정
					
				}else{//암호가 일치하지 않을 때
					x=0;
				}
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("updateBoard() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//updateBoard()-end
	//--------------------
	// DB글 삭제 
	//--------------------
	public int deleteBoard(int num,String inpw){
		String dbpw="";//변수
		int x=-100;
		Connection con=null;
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select pw from board where num="+num);
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				dbpw=rs.getString("pw");
				if(inpw.equals(dbpw)){//암호가 일치 하면 글삭제 
					pstmt=con.prepareStatement("delete from board where num="+num);
					pstmt.executeUpdate();//쿼리 수행 
					x=1;//삭제 성공
					
				}else{//암호가 일치 하지 않으면
					x=0;//삭제 실패
				}
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("deleteBoard() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//deleteBoard()-end
	
	
	
}//class-end
