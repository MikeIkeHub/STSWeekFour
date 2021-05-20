package com.promineotech.jeep.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultJeepSalesDao implements JeepSalesDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate; // used for making SQL statements in a secured way.

  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    log.debug("REACHED DAO: model={}, trim={}", model, trim); //Spring shows if it has been reached
    
    String sql = "" 
        + "SELECT * " 
        + "FROM models "
        + "WHERE model_id = :model_id AND trim_level = :trim_level";
    
    Map<String,Object> params = new HashMap<>(); //Used to grab from the database.
    params.put("model_id", model.toString());
    params.put("trim_level", trim);
    
    return jdbcTemplate.query(sql, params, new RowMapper<Jeep>() { //RowMapper unique to jdbc to map rows of the sql Results
      @Override
      public Jeep mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Jeep.builder()
            .modelPK(rs.getLong("model_pk"))
            .modelId(JeepModel.valueOf(rs.getString("model_id"))) //JeepModel is an enum, so you have to convert to string
            .numDoors(rs.getInt("num_doors"))
            .trimLevel(rs.getString("trim_level"))
            .wheelSize(rs.getInt("wheel_size"))
            .basePrice(rs.getBigDecimal("base_price"))
            .build();
      }});
  }
}
