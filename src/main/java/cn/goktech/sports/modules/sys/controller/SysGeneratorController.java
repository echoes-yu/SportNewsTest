package cn.goktech.sports.modules.sys.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.goktech.sports.common.entity.Page;
import cn.goktech.sports.modules.sys.entity.GeneratorParamEntity;
import cn.goktech.sports.modules.sys.service.SysGeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.goktech.sports.common.entity.Page;
import cn.goktech.sports.modules.sys.entity.GeneratorParamEntity;
import cn.goktech.sports.modules.sys.entity.TableEntity;
import cn.goktech.sports.modules.sys.service.SysGeneratorService;

/**
 * 代码生成器
 * @author zcl<yczclcn@163.com>
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

	@Autowired
	private SysGeneratorService sysGeneratorService;
	
	/**
	 * 表格列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Page<TableEntity> list(@RequestBody Map<String, Object> params) {
		return sysGeneratorService.listTable(params);
	}

	/**
	 * 生成代码
	 * @param params
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/code")
	public void generator(GeneratorParamEntity params, HttpServletRequest request, HttpServletResponse response) throws IOException {
		byte[] code = sysGeneratorService.generator(params);
		String attachment = "attachment; filename=" + params.getTables()[0] + ".zip";
		response.reset();  
        response.setHeader("Content-Disposition", attachment);  
        response.addHeader("Content-Length", "" + code.length);  
        response.setContentType("application/octet-stream; charset=UTF-8");  
        IOUtils.write(code, response.getOutputStream());
	}
	
}