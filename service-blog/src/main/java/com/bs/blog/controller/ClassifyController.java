package com.bs.blog.controller;

import com.bs.blog.dto.ClassifyAllDto;
import com.bs.blog.service.ClassifyService;
import com.bs.blog.vo.InsertLabelVo;
import com.bs.common.utils.CommonResult;
import com.bs.common.utils.ResultCode;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author bingshao
 * @date 2021/8/26
 **/
@RestController
@RequestMapping("/classify")
public class ClassifyController {

    @Resource
    private ClassifyService classifyService;

    @GetMapping("/getAllClassify")
    public CommonResult<List<ClassifyAllDto>> getAllClassify(){
        List<ClassifyAllDto> classifyAllDtoList = classifyService.getAllClassify();
        return CommonResult.success(classifyAllDtoList);
    }

    @PostMapping("/addLabel")
    public CommonResult<ResultCode> addLabel(@RequestBody InsertLabelVo insertLabelVo){
        if(classifyService.addLabel(insertLabelVo)>0){
            return CommonResult.success(ResultCode.SUCCESS);
        }else {
            return CommonResult.failed(ResultCode.SAVE_LABEL_FAIL);
        }
    }
}
