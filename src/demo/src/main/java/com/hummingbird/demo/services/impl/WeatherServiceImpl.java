package com.hummingbird.demo.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hummingbird.common.exception.BusinessException;
import com.hummingbird.demo.mapper.WeatherMapper;
import com.hummingbird.demo.services.WeatherService;
import com.hummingbird.demo.util.HttpProcessUtil;
import com.hummingbird.demo.vo.WeatherBodyVO;
import com.hummingbird.demo.vo.WeatherBodyVOResult;

import net.sf.json.JSONObject;

/**
 * @author 
 * @date 2015-12-12
 * @version 1.0
 *  service接口实现
 */
@Service
public class WeatherServiceImpl  implements WeatherService{

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(this.getClass());
	@Autowired
	WeatherMapper dao;

			/**
	 * 查询城市天气
	 * @param appId 应用id
	 * @param body 参数
	 * @return 
	 * @throws BusinessException 
	 */
	 @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class,value="txManager")
	public WeatherBodyVOResult queryWeather(String appId,WeatherBodyVO body) throws BusinessException{
		if(log.isDebugEnabled()){
				log.debug("查询城市天气开始");
		}
		WeatherBodyVOResult result = new WeatherBodyVOResult();
		String url = "";
		Map<String,String> params = new HashMap<String, String>(); 
		//开发者密钥
		params.put("ak", "GgS4VK9Ci9MgoUx6E6I5MkwB");
		//输出的数据格式json
		params.put("output", "’json");
		//城市名称
		params.put("location", body.getCity());
		//type=true以rest风格传递参数，type=false以&拼接参数
		boolean type = false;
		HttpProcessUtil httpProCessUtil = new HttpProcessUtil();
		try {
			byte[] bytes = httpProCessUtil.doGet(url, params, type);
			String responseBody = new String(bytes, "UTF-8");
			JSONObject json = JSONObject.fromObject(responseBody);
			result.setCityName(json.getString("cityName"));
			result.setMinTemperature(json.getInt("minTemperature"));
			result.setMaxTemperature(json.getInt("maxTemperature"));
			result.setWeather(json.getString("weather"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException();
		}
		
		if(log.isDebugEnabled()){
				log.debug("查询城市天气完成");
		}
		return result;
	}
		
		
		
}