package cn.goktech.sports.modules.sys.controller;

import java.util.List;
import java.util.Map;

import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import cn.goktech.sports.modules.sys.service.SysMacroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.goktech.sports.common.annotation.SysLog;
import cn.goktech.sports.common.entity.R;
import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import cn.goktech.sports.modules.sys.service.SysMacroService;

/**
 * 通用字典
 * @author zcl<yczclcn@163.com>
 */
@RestController
@RequestMapping("/sys/macro")
public class SysMacroController extends AbstractController {

	@Autowired
	private SysMacroService sysMacroService;
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public List<SysMacroEntity> list() {
		return sysMacroService.listMacro();
	}
	
	/**
	 * 树形列表
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysMacroEntity> select() {
		return sysMacroService.listNotMacro();
	}
	
	/**
	 * 新增字典
	 * @param macro
	 * @return
	 */
	@SysLog("新增字典")
	@RequestMapping("/save")
	public R save(@RequestBody SysMacroEntity macro) {
		return sysMacroService.saveMacro(macro);
	}
	
	/**
	 * 根据id查询详情
	 * @param id
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestBody Long id) {
		return sysMacroService.getObjectById(id);
	}
	
	/**
	 * 修改字典
	 * @param macro
	 * @return
	 */
	@SysLog("修改字典")
	@RequestMapping("/update")
	public R update(@RequestBody SysMacroEntity macro) {
		return sysMacroService.updateMacro(macro);
	}

	/**
	 * 显示字典
	 * @param id
	 * @return
	 */
	@SysLog("显示字典")
	@RequestMapping("/enable")
	public R updateStateShow(@RequestBody Long id) {
		SysMacroEntity macro = new SysMacroEntity();
		macro.setMacroId(id);
		macro.setStatus(1);
		return sysMacroService.updateMacro(macro);
	}

	/**
	 * 隐藏字典
	 * @param id
	 * @return
	 */
	@SysLog("隐藏字典")
	@RequestMapping("/disable")
	public R updateStateHide(@RequestBody Long id) {
		SysMacroEntity macro = new SysMacroEntity();
		macro.setMacroId(id);
		macro.setStatus(0);
		return sysMacroService.updateMacro(macro);
	}
	
	/**
	 * 删除字典
	 * @param id
	 * @return
	 */
	@SysLog("删除字典")
	@RequestMapping("/remove")
	public R batchRemove(@RequestBody Long[] id) {
		return sysMacroService.batchRemove(id);
	}

	/**
	 * 获取某个类型所有参数值，用于前台构建下拉框
	 * @param value
	 * @return
	 */
	@RequestMapping("/value")
	public List<SysMacroEntity> listMacroValue(@RequestParam String value) {
		return sysMacroService.listMacroValue(value);
	}

	/**
	 * 根据多个字典类型获得字典map
	 * @param types
	 * @return
	 */
	@RequestMapping("/getByTypes")
	public R getByTypes(@RequestParam("types") String types) {
		R r = new R();
		Map<String,List<SysMacroEntity>> map = sysMacroService.getByTypes(types);
		r.putAll(map);
		return r;
	}
}
