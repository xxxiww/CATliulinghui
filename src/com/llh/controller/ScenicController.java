package com.llh.controller;

import com.llh.bean.ModifyScenic;
import com.llh.entity.Scenic;
import com.llh.service.ScenicService;
import com.llh.service.ScenicServiceimpl;
import com.llh.service.UserService;
import com.llh.service.UserServiceimpl;
import com.llh.utils.CheckPic;

import java.util.List;

public class ScenicController {
    private ScenicService scenicService = new ScenicServiceimpl();
    //更改景点信息


    //添加景点
    public  boolean addScenic(ModifyScenic modifyScenic){
        //判断图片路径
        boolean pic = CheckPic.checkPic(modifyScenic.getImg());
        if(pic){
            return scenicService.addScenic(modifyScenic);

        }
        else{
            return false;
        }
    }

    //通过景点名字删除景点
    public boolean deleteByName(String scenicName){
        if("".equals(scenicName)){
            return  false;
        }
        else{
            return scenicService.deleteByName(scenicName);
        }

    }


    public boolean updateScenic(ModifyScenic modifyScenic){
        return scenicService.updateScenic(modifyScenic);
    }
    //通过传过来的String来模糊搜索景点
    public List<Scenic> selectScenic(String select,String mode,int pageNo){
          return  scenicService.selectScenic(select,mode,pageNo);
    }

    public Scenic selectByName(String lookScenic){
        return scenicService.selectByName(lookScenic);
    }
}
