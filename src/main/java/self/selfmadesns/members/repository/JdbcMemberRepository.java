package self.selfmadesns.members.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import self.selfmadesns.members.domain.Member;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository{

    private DataSource dataSource;

    @Autowired
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String sql = "";

    @Override
    public Optional<Member> selectMemberByNo(Long no) {
        sql = "select * from member where no=?";
        Member member = new Member();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1,no);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setProfileImage(rs.getString("profileImage"));
                member.setReg_date(rs.getDate("reg_date"));
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.of(member);
    }

    @Override
    public Optional<Member> selectMemberById(String id) {
        sql = "select * from member where id=?";
        Member member = new Member();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setProfileImage(rs.getString("profileImage"));
                member.setReg_date(rs.getDate("reg_date"));
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.of(member);
    }

    @Override
    public Optional<Member> selectMemberByEmail(String email) {
        sql = "select * from member where email=?";
        Member member = new Member();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,email);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setProfileImage(rs.getString("profileImage"));
                member.setReg_date(rs.getDate("reg_date"));
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.of(member);
    }

    @Override
    public Optional<Member> logInMember(String id, String pw) {
        sql = "select * from member where id=? and pw =?";
        Member member = new Member();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            pstmt.setString(2,pw);
            rs = pstmt.executeQuery();
            if(rs.next()){
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setProfileImage(rs.getString("profileImage"));
                member.setReg_date(rs.getDate("reg_date"));
            } else {
                return Optional.empty();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return Optional.of(member);
    }

    @Override
    public List<Member> selectMemberList() {
        sql = "select * from member";
        List<Member> memberList = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            memberList = new ArrayList<>();
            while(rs.next()){
                Member member = new Member();
                member.setNo(rs.getLong("no"));
                member.setId(rs.getString("id"));
                member.setPw(rs.getString("pw"));
                member.setName(rs.getString("name"));
                member.setEmail(rs.getString("email"));
                member.setGender(rs.getString("gender"));
                member.setProfileImage(rs.getString("profileImage"));
                member.setReg_date(rs.getDate("reg_date"));
                memberList.add(member);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return memberList;
    }

    @Override
    public Member insertMember(Member member) {
        sql = "insert into member(id,pw,name,email,gender,profileImage,reg_date) values(?,?,?,?,?,?,sysdate)";
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1,member.getId());
            pstmt.setString(2,member.getPw());
            pstmt.setString(3,member.getName());
            pstmt.setString(4,member.getEmail());
            pstmt.setString(5,member.getGender());
            pstmt.setString(6,member.getProfileImage());
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                member.setNo(rs.getLong(1));
                return member;
            } else {
                throw new SQLException("생성한 회원 조회 실패");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return member;
    }

    @Override
    public int updateMember(Member member) {
        sql = "update member set pw=?,gender=?,profileImage=? where id=?";
        int updateCount = 0;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,member.getPw());
            pstmt.setString(2,member.getGender());
            pstmt.setString(3,member.getProfileImage());
            pstmt.setString(4,member.getId());
            updateCount = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return updateCount;
    }

    @Override
    public int deleteMember(String id) {
        sql = "delete from member where id=?";
        int deleteCount = 0;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,id);
            deleteCount = pstmt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            close(conn,pstmt,rs);
        }
        return deleteCount;
    }

    // 커넥션 생성 메서드
    private Connection getConnection(){
        return DataSourceUtils.getConnection(dataSource);
    }

    // sql 작업 후 커넥션 자원을 반환하는 메서드
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if(null!=rs){
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(null!=pstmt){
                pstmt.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(null!=conn){
                close(conn);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // 커넥션 객체 conn을 close()로 반환시 SQLException이 발생하면 동작
    private void close(Connection conn) throws SQLException{
        DataSourceUtils.releaseConnection(conn,dataSource);
    }






}
