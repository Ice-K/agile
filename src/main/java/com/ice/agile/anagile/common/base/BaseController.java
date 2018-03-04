package com.ice.agile.anagile.common.base;

import com.ice.agile.anagile.common.vo.ResultVO;
import com.ice.agile.configuration.DateAndTimestampConfig.CustomTimestampEditor;
import com.ice.agile.configuration.DateAndTimestampConfig.StringEscapeEditor;
import com.ice.agile.execption.MyExecption;
import com.ice.agile.utils.ResultUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ControllerAdvice:
 * 是spring3.2提供的新注解，从名字上可以看出大体意思是控制器增强。
 * 即把@ControllerAdvice注解内部使用：@ExceptionHandler、@InitBinder、@ModelAttribute
 * 注解的方法应用到所有的 @RequestMapping注解的方法。
 *
 * 由@InitBinder表示的方法，可以对WebDataBinder对象进行初始化。
 * WebDataBinder是DataBinder的子类，用于完成由表单到JavaBean属性的绑定。
 *
 * 注解@ExceptionHandler用来处理异常
 *
 * Cteated by wangpeng
 * 2018/2/28 18:57
 */
@ControllerAdvice
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        datetimeFormat.setLenient(false);
        //自动转换日期类型的字段格式
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        binder.registerCustomEditor(java.sql.Timestamp.class, new CustomTimestampEditor(datetimeFormat, true));
        //防止XSS攻击
        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));
    }


    /**
     * 全局异常处理 controller中的方法必须 throws Execption
     * @param e 由controller抛出的异常
     * @return  返回给前端的结果
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultVO execptionHandler(Exception e) {
        if (e instanceof MyExecption) {
            MyExecption execption = (MyExecption) e;
            return ResultUtils.error(execption.getCode(),execption.getMessage());
        } else {
            return ResultUtils.error(-1, "未知错误");
        }
    }
}
