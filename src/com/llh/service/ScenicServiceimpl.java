package com.llh.service;


import com.llh.bean.ModifyScenic;
import com.llh.dao.ScenicDao;
import com.llh.dao.ScenicDaoimpl;
import com.llh.entity.Scenic;

import java.util.List;

public class ScenicServiceimpl implements  ScenicService{
    private ScenicDao scenicDao = new ScenicDaoimpl();//Dao层user
    public Scenic scenic = null; //实体类

    public boolean deleteByName(String scenicName){
        return scenicDao.deleteByName(scenicName);

    }
    public  boolean addScenic(ModifyScenic modifyScenic){
        return scenicDao.addScenic(modifyScenic);
    }

    public boolean updateScenic(ModifyScenic modifyScenic){
        return  scenicDao.updateScenic(modifyScenic);
    }
    public List<Scenic> selectScenic(String select,String mode,int pageNo){
        return scenicDao.selectScenic(select,mode,pageNo);
    }

    public Scenic selectByName(String lookScenic){

        return scenicDao.selectByName(lookScenic);
    }
}
