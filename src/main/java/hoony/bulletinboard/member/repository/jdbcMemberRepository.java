package hoony.bulletinboard.member.repository;

import hoony.bulletinboard.member.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class jdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public jdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name, password) values (?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            conn = getConnection();
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, member.getName());
            pstmt.setString(2, member.getPassword());

            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();

            if (rs.next()){
                member.setMemberId(rs.getLong(1));
            }else{
                throw new SQLException("id 조회 실패"); // 왜 조회 실패?
            }
            return member;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }

    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getLong("memberId"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
                members.add(member);
            }
            return members;
        }catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn,pstmt,rs);
        }

    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select *from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if(rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getLong("memberId"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
                return Optional.of(member);
            }

            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }


    }



    @Override
    public Member update(Member member) {

        String sql = "update member set password=? where name=?";

        Connection conn = null;
        PreparedStatement pstmt = null;
//        ResultSet rs = null;

        try{

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getName());

            pstmt.executeUpdate();


            return member;

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    @Override
    public void delete(Member member) {
        String sql = "delete from member where name=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getName());
            pstmt.executeUpdate();

        } catch (Exception e){
            throw new IllegalStateException(e);
        }finally {
            close(conn, pstmt, null);
        }

    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try{
            if (rs != null){
                rs.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        try{
            if(pstmt != null){
                pstmt.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        try{
            if (conn != null){
//                conn.close();
                close(conn);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }

}
