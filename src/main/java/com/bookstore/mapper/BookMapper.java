package com.bookstore.mapper;

import com.bookstore.entity.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author ME495
 */
@Repository
public interface BookMapper {
    public Book getBook(@Param("isbn") String isbn);
}
