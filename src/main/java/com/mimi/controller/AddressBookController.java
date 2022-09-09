package com.mimi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.mimi.common.BaseContext;
import com.mimi.common.R;
import com.mimi.domain.AddressBook;
import com.mimi.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressBook")
@Slf4j
@Api(tags = "地址簿接口")
public class AddressBookController {
    @Autowired
    private AddressBookService addressBookService;

    @ApiOperation("新增地址")
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook){
        addressBook.setUserId(BaseContext.getCurrentID());
        log.info(addressBook.toString());
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    @GetMapping("/list")
    public R<List<AddressBook>> list(){
        LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AddressBook::getUserId,BaseContext.getCurrentID());
        lqw.orderByDesc(AddressBook::getUpdateTime);
        List<AddressBook> list = addressBookService.list(lqw);
        return R.success(list);
    }

    @PutMapping("default")
    public R<AddressBook> setDefaultAddress(@RequestBody AddressBook addressBook){
        LambdaUpdateWrapper<AddressBook> luw = new LambdaUpdateWrapper<>();
        luw.eq(AddressBook::getUserId,BaseContext.getCurrentID()).set(AddressBook::getIsDefault,0);
        addressBookService.update(luw);

        addressBook.setIsDefault(1);
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    @GetMapping("default")
    public R<AddressBook> getDefaultAddress(){
        LambdaQueryWrapper<AddressBook> lqw = new LambdaQueryWrapper<>();
        lqw.eq(AddressBook::getUserId,BaseContext.getCurrentID()).eq(AddressBook::getIsDefault,1);
        AddressBook addressBook = addressBookService.getOne(lqw);
        return R.success(addressBook);
    }

    @GetMapping("/{id}")
    @ApiOperation("通过id查询地址")
//    @ApiImplicitParam("地址id")
    public R getOne(@PathVariable Long id){
        AddressBook addressBook = addressBookService.getById(id);
        if (null != addressBook){
            return R.success(addressBook);
        }
        return R.error("查无id");
    }

    @PutMapping
    public R<AddressBook> update(@RequestBody AddressBook addressBook){
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    @DeleteMapping
    public R<String> delete(Long ids){
        addressBookService.removeById(ids);
        return R.success("删除成功");
    }


}
