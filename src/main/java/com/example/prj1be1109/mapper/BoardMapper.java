package com.example.prj1be1109.mapper;
import com.example.prj1be1109.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface BoardMapper {

    @Insert("""
        INSERT INTO board (title, content, writer)
        VALUES (#{title}, #{content}, #{writer})
        """)
    int insert(Board board);
}

