package org.emnmem.oblig2.repository;


import org.emnmem.oblig2.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OtherRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<MessageDto> getAllMessages() {
        String sql = "select * from Room";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MessageDto.class));
    }
}
