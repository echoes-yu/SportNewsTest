package cn.goktech.sports.modules.sys.controller;

import java.util.List;
import java.util.Map;

import cn.goktech.sports.common.entity.R;
import cn.goktech.sports.modules.sys.entity.SysAreaEntity;
import cn.goktech.sports.modules.sys.service.SysAreaService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.goktech.sports.common.annotation.SysLog;
import cn.goktech.sports.common.entity.R;
import cn.goktech.sports.modules.sys.entity.SysAreaEntity;
import cn.goktech.sports.modules.sys.service.SysAreaService;

/**
 * 行政区域
 * @author zcl<yczclcn@163.com>
 */
@RestController
@RequestMapping("/sys/area")
public class SysAreaController extends AbstractController {

	@Autowired
	private SysAreaService sysAreaService;
	
	/**
	 * 根据父级code查询子节点，子区域列表
	 * @param params
	 * @return
	 */
	@RequestMapping("/list")
	public R list(@RequestBody Map<String, Object> params) {
		return sysAreaService.listAreaByParentCode(params);
	}
	
	/**
	 * 根据父级code查询子节点，树形目录
	 * @return
	 */
	@RequestMapping("/select")
	public List<SysAreaEntity> select(@RequestParam String areaCode) {
		return sysAreaService.listAreaByParentCode(areaCode);
	}

    /**
     * 根据层级获取区域
     * @param layer
     * @return
     */
    @RequestMapping("/layer")
    public List<SysAreaEntity> listAreaByLayer(Integer layer){
        return sysAreaService.listAreaByLayer(layer);
    }
	
	/**
	 * 新增区域
	 * @param area
	 * @return
	 */
	@SysLog("新增区域")
	@RequestMapping("/save")
	public R save(@RequestBody SysAreaEntity area) {
		return sysAreaService.saveArea(area);
	}
	
	/**
	 * 查询详情
	 * @param areaId
	 * @return
	 */
	@RequestMapping("/info")
	public R info(@RequestBody Long areaId) {
		return sysAreaService.getAreaById(areaId);
	}
	
	/**
	 * 修改区域
	 * @param area
	 * @return
	 */
	@SysLog("修改区域")
	@RequestMapping("/update")
	public R update(@RequestBody SysAreaEntity area) {
		return sysAreaService.updateArea(area);
	}
	
	/**
	 * 删除区域
	 * @param id
	 * @return
	 */
	@SysLog("删除区域")
	@RequestMapping("/remove")
	public R remove(@RequestBody Long[] id) {
		return sysAreaService.batchRemoveArea(id);
	}
	
}
