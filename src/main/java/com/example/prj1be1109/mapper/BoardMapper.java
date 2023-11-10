package com.example.prj1be1109.mapper;

import com.example.prj1be1109.domain.Board;
import lombok.Data;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {

    @Insert("""
        INSERT INTO mmlist (Artist, Title, ReleaseDate, Production, Cost)
        VALUES (#{Artist}, #{Title}, #{ReleaseDate},#{Production},#{Cost})
        """)
    int insert(Board board);

    @Select("""
        SELECT id,Artist, Title, ReleaseDate,Production,Cost
        FROM mmlist
        ORDER BY Artist DESC
        """)
    List<Board> selectAll();

    @Select("""
        SELECT id,Artist, Title, ReleaseDate,Production,Cost FROM mmlist
        WHERE Artist = #{Artist}
        """)
    Board selectId (Integer id);

    @Delete("""
        DELETE FROM mmlist
        WHERE id= #{id}
        """)
    int deleteById(Integer id);
    @Update("""
        UPDATE mmlist
        
        SET Artist = #{Artist},
            Title = #{Title},
            ReleaseDate = #{ReleaseDate},
            Production= #{Production},
            Cost = #{Cost}
        WHERE id = #{id}
        """)
    int update(Board board);
}
