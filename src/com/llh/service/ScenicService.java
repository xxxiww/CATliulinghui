package com.llh.service;

import com.llh.bean.ModifyScenic;
import com.llh.entity.Scenic;

import java.util.List;

public interface ScenicService {
     boolean addScenic(ModifyScenic modifyScenic);


     //通过景点名字删除景点
     boolean deleteByName(String scenicName);

     boolean updateScenic(ModifyScenic modifyScenic);

     //通过搜索内容来模糊搜索
     List<Scenic> selectScenic(String select,String mode,int pageNo);

      Scenic selectByName(String lookScenic);


}
