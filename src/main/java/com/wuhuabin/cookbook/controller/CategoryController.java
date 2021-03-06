package com.wuhuabin.cookbook.controller;

import com.github.pagehelper.PageInfo;
import com.wuhuabin.cookbook.common.ApiRestResponse;
import com.wuhuabin.cookbook.exception.CookBookException;
import com.wuhuabin.cookbook.exception.CookBookExceptionEnum;
import com.wuhuabin.cookbook.model.request.AddCategoryReq;
import com.wuhuabin.cookbook.model.vo.CategoryVO;
import com.wuhuabin.cookbook.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/category/add")
    public ApiRestResponse addCategory(@Valid @RequestBody AddCategoryReq addCategoryReq) throws CookBookException {
        if (addCategoryReq.getName() == null || addCategoryReq.getType() == null ||
                addCategoryReq.getParentId() == null || addCategoryReq.getOrderNum() == null) {
            return ApiRestResponse.error(CookBookExceptionEnum.NAME_NOT_NULL);
        }
        categoryService.add(addCategoryReq);
        return ApiRestResponse.success();
    }

    @ApiOperation("获取分类列表")
    @GetMapping("/category/list1")
    public ApiRestResponse categoryList1(@RequestParam("pageNum") Integer pageNum,
                                         @RequestParam("pageSize") Integer pageSize) {
        PageInfo pageInfo = categoryService.categoryList(pageNum, pageSize);
        return ApiRestResponse.success(pageInfo);
    }

    @ApiOperation("获取分类列表")
    @GetMapping("/category/list2")
    public ApiRestResponse categoryList2() {
        List<CategoryVO> categoryVOS = categoryService.listCategoryForCustomer();
        return ApiRestResponse.success(categoryVOS);
    }
}
