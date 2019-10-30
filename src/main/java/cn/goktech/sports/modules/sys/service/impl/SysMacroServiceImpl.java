package cn.goktech.sports.modules.sys.service.impl;

import cn.goktech.sports.common.constant.MsgConstant;
import cn.goktech.sports.modules.sys.dao.SysMacroMapper;
import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import cn.goktech.sports.modules.sys.service.SysMacroService;
import cn.goktech.sports.common.constant.MsgConstant;
import cn.goktech.sports.common.constant.SystemConstant;
import cn.goktech.sports.common.entity.R;
import cn.goktech.sports.common.utils.CommonUtils;
import cn.goktech.sports.modules.sys.dao.SysMacroMapper;
import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import cn.goktech.sports.modules.sys.service.SysMacroService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * 通用字典
 * @author zcl<yczclcn@163.com>
 */
@Service("sysMacroService")
public class SysMacroServiceImpl implements SysMacroService {

	@Autowired
	private SysMacroMapper sysMacroMapper;

	/**
	 * 字典列表
	 * @return
	 */
	@Override
	public List<SysMacroEntity> listMacro() {
		return sysMacroMapper.list();
	}

	/**
	 * 字典上级列表：ztree数据源
	 * @return
	 */
	@Override
	public List<SysMacroEntity> listNotMacro() {
		List<SysMacroEntity> macros = sysMacroMapper.listNotMacro();
		SysMacroEntity macro = new SysMacroEntity();
		macro.setMacroId(0L);
		macro.setTypeId(-1L);
		macro.setName("一级目录");
		macro.setOpen(true);
		macros.add(macro);
		return macros;
	}

	/**
	 * 新增字典
	 * @param macro
	 * @return
	 */
	@Override
	public R saveMacro(SysMacroEntity macro) {
		int count = sysMacroMapper.save(validateMacro(macro));
		return CommonUtils.msg(count);
	}

	/**
	 * 根据id查询字典
	 * @param id
	 * @return
	 */
	@Override
	public R getObjectById(Long id) {
		SysMacroEntity macro = sysMacroMapper.getObjectById(id);
		return CommonUtils.msg(macro);
	}

	/**
	 * 修改字典
	 * @param macro
	 * @return
	 */
	@Override
	public R updateMacro(SysMacroEntity macro) {
		int count = sysMacroMapper.update(macro);
		return CommonUtils.msg(count);
	}

	/**
	 * 批量删除字典
	 * @param id
	 * @return
	 */
	@Override
	public R batchRemove(Long[] id) {
		boolean children = false;
		for(Long typeId : id) {
			int count = sysMacroMapper.countMacroChildren(typeId);
			if(CommonUtils.isIntThanZero(count)) {
				children = true;
			}
		}
		if(children) {
			return R.error(MsgConstant.MSG_HAS_CHILD);
		}
		int count = sysMacroMapper.batchRemove(id);
		return CommonUtils.msg(id, count);
	}

	/**
	 * 查询指定类型的参数列表
	 * @param type
	 * @return
	 */
	@Override
	public List<SysMacroEntity> listMacroValue(String type) {
		return sysMacroMapper.listMacroValue(type);
	}

	/**
	 * 当为参数类型时，状态为显示
	 * @param macro
	 * @return
	 */
	public SysMacroEntity validateMacro(SysMacroEntity macro) {
		if(macro.getType() == SystemConstant.MacroType.TYPE.getValue()) {
			macro.setStatus(SystemConstant.StatusType.SHOW.getValue());
		}
		return macro;
	}

	/**
	 * 根据多个字典类型获得字典map
	 * @param types
	 * @return
	 */
	@Override
	public Map<String, List<SysMacroEntity>> getByTypes(String types) {
		if(StringUtils.isBlank(types)){
            return null;
		}
		List<String> typeList = Arrays.asList(types.split(","));
		List<SysMacroEntity> list = sysMacroMapper.getByTypes(typeList);
		return list.stream().collect(Collectors.groupingBy(a->a.getTypeName()));
	}

}
