package com.jking.website.exceptions;

import com.jking.website.VO.ResultVO;
import com.jking.website.utils.ResultVoUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class MyExceptionHandler {
@ExceptionHandler(value = MyException.class)
    public ResultVO handlerLoginException(MyException e)
{
    return ResultVoUtil.fail(e.getCode(),e.getMessage());
}
}
