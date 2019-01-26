package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @GetMapping
    public Result findAll(){
        return  new Result(true, StatusCode.OK,"");
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){
        return  new Result(true, StatusCode.OK,"");
    }

}


