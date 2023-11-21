package com.example.prj1be20231109.mapper;

import com.example.prj1be20231109.domain.Board;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Objects;

@Mapper
public interface BoardMapper {

    @Insert("""
            INSERT INTO board (title, content, writer)
            VALUES (#{title}, #{content}, #{writer})
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Board board);

    @Select("""
            SELECT b.id,
                   b.title,
                   b.writer,
                   m.nickName,
                   b.inserted,
                   COUNT(DISTINCT c.id) countComment,
                   COUNT(DISTINCT l.id) countLike,
                   COUNT(DISTINCT f.id) countFile
            FROM board b JOIN member m ON b.writer = m.id
                         LEFT JOIN comment c ON b.id = c.boardId
                         LEFT JOIN boardLike l ON b.id = l.boardId
                         LEFT JOIN boardLike f ON b.id = f.boardId
            WHERE b.content LIKE #{keyword} 
               OR b.title  LIKE  #{keyword}
            
            GROUP BY b.id
            ORDER BY b.id DESC
            LIMIT #{from},10;
            """)
    List<Board> selectAll(Integer from, String keyword);

    //메소드에 파라미터가 변경되었으니 사용화
    @Select("""
            SELECT b.id,
                   b.title, 
                   b.content, 
                   b.writer, 
                   m.nickName,
                   b.inserted
            FROM board b JOIN member m ON b.writer = m.id
            WHERE b.id = #{id}
            """)
    Board selectById(Integer id);

    @Delete("""
            DELETE FROM board
            WHERE id = #{id}
            """)
    int deleteById(Integer id);

    @Update("""
            UPDATE board
            SET title = #{title},
                content = #{content}
            WHERE id = #{id}
            """)
    int update(Board board);


    @Delete("""
            DELETE FROM board
            WHERE writer = #{writer}
            """)
    int deleteByWriter(String writer);

    @Select("""
            SELECT id
            FROM board
            WHERE writer = #{id}
            """)
    List<Integer> selectIdListByMemberId(String writer);

    @Select("""
            SELECT  COUNT(*) FROM board
            WHERE title LIKE #{keyword}
                OR content like #{keyword}
            """)
    int countAll(String keyword);
}