package com.llh.dao;

import com.llh.bean.ModifyScenic;
import com.llh.entity.Scenic;

import java.util.List;

public interface ScenicDao {

   boolean deleteByName(String scenicName);
   boolean addScenic(ModifyScenic modifyScenic);
   boolean updateScenic(ModifyScenic modifyScenic);

   List<Scenic> selectScenic(String select,String mode,int pageNo);

   Scenic selectByName(String lookScenic);


}
