package com.jking.website.utils;

import com.jking.website.VO.ResultVO;

public class ResultVoUtil {
    public static ResultVO succees(Object object){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(object);
        resultVO.setMessage("成功");
        return  resultVO;
    }

    public static ResultVO succees()
    {
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return  resultVO;
    }

    public static ResultVO fail(Integer code ,String message){
        ResultVO resultVO=new ResultVO();
        resultVO.setMessage(message);
        resultVO.setCode(code);
        return resultVO;
    }

}
