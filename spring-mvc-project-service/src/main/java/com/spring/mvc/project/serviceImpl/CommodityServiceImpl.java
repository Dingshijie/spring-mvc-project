package com.spring.mvc.project.serviceImpl;

import java.util.List;

import javax.activation.CommandInfo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.project.domain.Category;
import com.spring.mvc.project.service.CommodityService;

@Service
@Transactional(readOnly = true)
public class CommodityServiceImpl implements CommodityService {

	@Override
	public boolean add(CommandInfo commandInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(CommandInfo commandInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(String paramter, String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void delete(CommandInfo commandInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<CommandInfo> findList(Category category, int status, int recommend, int used, int pageIndex,
			int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommandInfo> findCount(Category category, int status, int recommend, int used) {
		// TODO Auto-generated method stub
		return null;
	}

}
