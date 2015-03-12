package com.spring.mvc.project.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.mvc.project.service.CommodityService;

@Service
@Transactional(readOnly = true)
public class CommodityServiceImpl implements CommodityService {

}
