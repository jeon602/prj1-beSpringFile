package com.example.prj1be1109.mapper;

import com.example.prj1be1109.domain.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    @Insert("""
            INSERT INTO member (id, password, email)
            VALUES (#{id}, #{password}, #{email})
            """)
    int insert(Member member);

    @Select("""
            SELECT id FROM member WHERE id = #{id} 
            """)
    String selectId(String id);

    @Select("""
            SELECT email from member
            WHERE email = #{email}
            """)
    String selectEmail(String email);

    @Select("""
        select id, password,email, inserted
        from member ORDER BY  inserted DESC
        """)
    List<Member> selectAll();
}