package com.mfg.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mfg.dao.GoodDao;
import com.mfg.enty.Good;

@RestController 
@RequestMapping("/api")  
public class GoodAction {

	@Autowired
	GoodDao goodDao;
	
	@RequestMapping(value = "/good",method = RequestMethod.GET)
    public List<Good> getGoods(int page,int size) { 
		Sort sort = new Sort(Direction.DESC, "id");
		PageRequest pageRequest = new PageRequest(page-1,size,sort);
		Iterator<Good> customers =  goodDao.findAll(pageRequest).iterator();
	    List<Good> lists = new ArrayList<Good>();
	    while(customers.hasNext()){
	         lists.add(customers.next());
	    }
	    return lists;
    }
	
	@RequestMapping(value = "/good",method = RequestMethod.POST)  
    public Good postGood(@RequestBody Good good) { 
		return goodDao.save(good);
    }  
	
	@RequestMapping(value = "/good",method = RequestMethod.PUT)  
    public Good putGood(@RequestBody Good good) { 
		Good good2 = null;
		if(!StringUtils.isEmpty(good.getId())){
			good2= goodDao.findById(good.getId());
		}else if(!StringUtils.isEmpty(good.getName())){
			good2= goodDao.findByName(good.getName());
		}
		if(good2 == null){
			good2 = new Good();
			return good2;
		}
		/*Good good3 = new Good();
		good3.setId(good2.getId());
		good3.setName(good.getName());
		good3.setAge(good.getAge());
		good3.setProductionDate(good.getProductionDate());*/
		good.setId(good2.getId());
		return goodDao.save(good);
    }  
	@RequestMapping(value="/good/{id}",method = RequestMethod.DELETE)  
    public void deleteGood(@PathVariable Integer id) { 
		goodDao.delete(id);;
    }  
	@RequestMapping(value="/good/{id}",method = RequestMethod.GET)  
    public Good getGoodById(@PathVariable Integer id) { 
		return goodDao.findById(id);
    } 
	
}
