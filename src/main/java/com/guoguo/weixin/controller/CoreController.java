package com.guoguo.weixin.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.guoguo.common.controller.BaseController;
import com.guoguo.weixin.service.CoreService;
import com.guoguo.weixin.utils.WeiXinEventUtil;

@Controller
@RequestMapping("/api/weixin/core")
public class CoreController extends BaseController{
	
	@Autowired
	private CoreService coreService;

	/**
	 * 微信接入
	 * date：2017年3月27日 下午5:30:07
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/response", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String responseWeixinConnect(HttpServletRequest request,HttpServletResponse response) throws IOException{
		boolean isGet = request.getMethod().toLowerCase().equals("get"); 
		if(isGet) {
			PrintWriter writer = response.getWriter();
			logger.info("微信接入.............................");
			// 微信加密签名
			String signature = request.getParameter("signature");
			// 时间戳
			String timestamp = request.getParameter("timestamp");
			// 随机数
			String nonce = request.getParameter("nonce");
			// 随机字符串
			String echostr = request.getParameter("echostr");
			//通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
			if (WeiXinEventUtil.checkSignature(signature, timestamp, nonce)) {
				writer.write(echostr);
				writer.close();
				writer = null;
			}
		}else {
			String respMessage = "ok";
			request.setCharacterEncoding("UTF-8");
	        response.setCharacterEncoding("UTF-8");
			try {
				//处理微信推事件
				respMessage = coreService.processRequest(request);
				PrintWriter out = response.getWriter();
		        out.print(respMessage);
		        out.flush();
		        out.close();
			} catch (Exception e) {
				logger.error("Failed to convert the message from weixin!"); 
				e.printStackTrace();
			} 
		}
		return null;
	}
}
