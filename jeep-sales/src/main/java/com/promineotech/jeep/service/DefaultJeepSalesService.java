package com.promineotech.jeep.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.promineotech.jeep.dao.JeepSalesDao;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;


@Service
public class DefaultJeepSalesService implements JeepSalesService {
  
  @Autowired
  private JeepSalesDao jeepSalesDao; //Injection of JeepSalesDao interface
  

  
  @Override
  public List<Jeep> fetchJeeps(JeepModel model, String trim) {
    return jeepSalesDao.fetchJeeps(model, trim);
  }

  
  
}
