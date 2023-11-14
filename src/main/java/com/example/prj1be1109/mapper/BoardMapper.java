package com.example.prj1be1109.mapper;

import com.example.prj1be1109.domain.Board;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
        INSERT INTO board (title, content, writer)
        VALUES (#{title}, #{content}, #{writer})
        """)
    int insert(Board board);

    @Select("""
        SELECT id, title, writer, inserted
        FROM board
        ORDER BY id DESC
        """)
    List<Board> selectAll();

    @Select("""
        SELECT id, title, content, writer, inserted
        FROM board
        WHERE id = #{id}
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
            content = #{content},
            writer = #{writer}
        WHERE id = #{id}
        """)
    int update(Board board);
    @Delete("""
        DELETE FROM board
        WHERE writer = #{writer}
        """)
    int deleteByWrite(String write);
}
