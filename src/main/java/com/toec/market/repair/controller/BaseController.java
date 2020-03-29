package com.toec.market.repair.controller;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.toec.market.repair.utils.HttpUtil;
import com.toec.market.repair.utils.JsonUtil;
 


public abstract class BaseController {

	public  Logger logger = Logger.getLogger(this.getClass());

	private static final String HEADER_ENCODING = "UTF-8";
	private static final boolean HEADER_NO_CACHE = true;
	private static final String HEADER_TEXT_CONTENT_TYPE = "text/plain";
	private static final String HEADER_JSON_CONTENT_TYPE = "text/plain";
	public static final String STATUS_PARAMETER_NAME = "status";// 操作状态参数名称
	public static final String MESSAGE_PARAMETER_NAME = "message";// 操作消息参数名称

	// AJAX输出
	protected String ajax(String content, String contentType, HttpServletResponse response) {
		try {
			HttpServletResponse resp = initResponse(contentType, response);
			byte[] responseBody = content.getBytes("utf-8");
			resp.setStatus(200);
			resp.setContentLength(responseBody.length);
			resp.getOutputStream().write(responseBody);
			resp.flushBuffer();
			this.responseLogger(resp,content);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
		// 根据Object输出AJAXajax
	protected String ajax(Object object, HttpServletResponse response) throws Exception {
			HttpServletResponse resp = initResponse(HEADER_JSON_CONTENT_TYPE,response);
			String responseBodyString = JsonUtil.toJson(object);
			byte[] responseBody = responseBodyString.getBytes("utf-8");
			resp.setStatus(200);
			resp.setContentLength(responseBody.length);
			resp.getOutputStream().write(responseBody);
			resp.flushBuffer();
			this.responseLogger(resp,responseBodyString);
			return null;
	}
		
	// 根据文本内容输出AJAX
	protected String ajax(String text, HttpServletResponse resp) {
		return ajax(text, HEADER_TEXT_CONTENT_TYPE, resp);
	}


	private HttpServletResponse initResponse(String contentType, HttpServletResponse response) {
		response.setContentType(contentType + ";charset=" + HEADER_ENCODING);
		if (HEADER_NO_CACHE) {
			response.setDateHeader("Expires", 1L);
			response.addHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
		}
		return response;
	}

	public String redirect(String format, Object... arguments) {
		return new StringBuffer("redirect:").append(MessageFormat.format(format, arguments)).toString();
	}
	 
	/**
	 * 得到ModelAndView
	 * 
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpSession getHttpSession() {
		HttpSession session = getRequest().getSession();
		return session;
	}

	/**
	 * 得到request对象
	 * 
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request;
	}
	
	public String requestLogger(HttpServletRequest request) throws IOException {
         byte[] requestBody = HttpUtil.getRequestBody(request);
         String requestBodyString = new String(requestBody, "utf-8");
         String requestString = HttpUtil.getRequestString(request, requestBodyString);
         logger.info(String.format("Received Request: %s", HttpUtil.getRequestString(request, requestBodyString)));
         return requestString;
	}
	
	public void responseLogger(HttpServletResponse response,String responseResult) throws IOException {
        String responseString = HttpUtil.getResponseString(response);
   	    logger.info(String.format("Send Response: %s ,Result %s", responseString,responseResult));
	}
	 
}
 