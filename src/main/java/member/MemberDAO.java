package member;
import java.sql.*;
import java.util.*;

import javax.sql.*;//DataSource
import javax.naming.*;//lookup
//비지니스 로직=DB처리 부분
public class MemberDAO {
	//변수
	Connection con=null;//메모리 차지한다 
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String sql="";
		
	//싱글톤 객체 사용
	private static MemberDAO dao=new MemberDAO();
	
	//생성자
	private MemberDAO(){}
	
	//jsp에서 dao 객체 얻기 
	public static MemberDAO getDAO(){
		return dao;
	}
	
	//---------------------
	// 커넥션 풀 사용 
	//---------------------
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}
	
	//---------------------
	// id 중복 체크
	//---------------------
	public int confirmId(String id){
		int x=-1;//사용 가능 id
		try{
			
			con=getCon();
			pstmt=con.prepareStatement("select id from member where id=?");
			pstmt.setString(1, id);/////***
			rs=pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				//사용중인 id
				x=1;
			}
			
		}catch(Exception ex1){
			System.out.println("confirmId() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}//confirmId()-end
	
	//------------------------
	//회원가입
	//------------------------
	public void insertMember(MemberDTO dto){
		try{
			con=getCon();//커넥션 얻기 
			pstmt=con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?,NOW())");
			
			//?값 채우기
			pstmt.setString(1, dto.getId());//id
			pstmt.setString(2, dto.getPw());//pw
			pstmt.setString(3, dto.getName());//name
			pstmt.setString(4, dto.getEmail());//email
			pstmt.setString(5, dto.getTel());//tel
			pstmt.setString(6, dto.getZipcode());//zipcode
			pstmt.setString(7, dto.getAddr());//addr
			pstmt.setString(8, dto.getAddr2());//addr2
			
			pstmt.executeUpdate();//쿼리 수행 
			
		}catch(Exception ex1){
			System.out.println("insertMember() 예외 :"+ex1);
		}finally{
			try{
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
	}//insertMember()-end
	//----------------
	//로그인
	//----------------
	public int loginCheck(String id,String pw){
		int x=-1;
		String dbpw="";
		
		try{
			con=getCon();
			pstmt=con.prepareStatement("select * from member where id=?");
			pstmt.setString(1,id);//?값 채우기 
			rs=pstmt.executeQuery();
			
			if(rs.next()){//해당 id있을때 
				dbpw=rs.getString("pw");
				if(pw.equals(dbpw)){
					x=1;//인증성공
				}else{
					x=0;//암호틀림
				}
			}else{
				x=-1;//없는 id
			}
			
		}catch(Exception ex1){
			System.out.println("loginCheck() 예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;//1  0  -1
	}//loginCheck()-end
	
	//-----------------
	// 내정보 수정 폼
	//-----------------
	public MemberDTO getMember(String id){
		System.out.println("id:"+id);
		MemberDTO dto=null;
		try{
			con=getCon();//케넥션 얻기 
			pstmt=con.prepareStatement("select * from member where id='"+id+"'");
			
			rs=pstmt.executeQuery();
			if(rs.next()){
				//rs내용을 dto에 저장
				dto=new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddr(rs.getString("addr"));
				dto.setAddr2(rs.getString("addr2"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getMember()예외 :"+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return dto;
	}//getMember()-end
	//---------------------------
	// DB에 내정보 수정 
	//---------------------------
	public void updateMember(MemberDTO dto){
		try{
			con=getCon();
			sql="update member set pw=?,name=?,email=?,tel=?,zipcode=?, addr=?, addr2=? where id=?";
			
			pstmt=con.prepareStatement(sql);//생성시 인자 들어 같다 
			
			//?값 채우기 
			pstmt.setString(1,dto.getPw());
			pstmt.setString(2,dto.getName());
			pstmt.setString(3,dto.getEmail());
			pstmt.setString(4,dto.getTel());
			pstmt.setString(5,dto.getZipcode());
			pstmt.setString(6,dto.getAddr());
			pstmt.setString(7,dto.getAddr2());
			pstmt.setString(8,dto.getId());
			
			pstmt.executeUpdate();//쿼리 수행
			
		}catch(Exception ex1){
			System.out.println("updateMember() 예외 :"+ex1);
		}finally{
			try{
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
	}//updateMember()-end
	
	//-----------------------
	//회원탈퇴
	//-----------------------
	public int deleteMember(String id,String pw){
		String dbpw="";
		int x=-1;
		try{
			
			con=getCon();
			pstmt=con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				dbpw=rs.getString("pw");
				if(pw.equals(dbpw)){//암호가 일치하면 삭제
					pstmt=con.prepareStatement("delete from member where id=?");
					pstmt.setString(1, id);
					pstmt.executeUpdate();//쿼리 수행 
					x=1;//삭제 성공
				}else{
					x=-1;//암호 틀림
				}//else-end
				
			}else{
				x=0;//없는 id
			}
			
		}catch(Exception ex1){
			System.out.println("deleteMember() 예외 :"+ex1);
			
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		return x;
	}
}//class-end
