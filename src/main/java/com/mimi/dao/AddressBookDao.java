package com.mimi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mimi.common.R;
import com.mimi.domain.AddressBook;
import com.mimi.domain.Employee;
import com.mimi.service.AddressBookService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Mapper
public interface AddressBookDao extends BaseMapper<AddressBook> {

}
