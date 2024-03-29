package cn.goktech.sports.modules.sys.service;

import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import cn.goktech.sports.common.entity.R;
import cn.goktech.sports.modules.sys.entity.SysMacroEntity;

import java.util.List;
import java.util.Map;

/**
 * 通用字典
 * @author zcl<yczclcn@163.com>
 */
public interface SysMacroService {

	/**
	 * 字典列表
	 * @return
	 */
	List<SysMacroEntity> listMacro();

	/**
	 * 字典上级列表：ztree数据源
	 * @return
	 */
	List<SysMacroEntity> listNotMacro();

	/**
	 * 新增字典
	 * @param macro
	 * @return
	 */
	R saveMacro(SysMacroEntity macro);

	/**
	 * 根据id查询字典
	 * @param id
	 * @return
	 */
	R getObjectById(Long id);

	/**
	 * 修改字典
	 * @param macro
	 * @return
	 */
	R updateMacro(SysMacroEntity macro);

	/**
	 * 批量删除字典
	 * @param id
	 * @return
	 */
	R batchRemove(Long[] id);

	/**
	 * 查询指定类型的参数列表
	 * @param type
	 * @return
	 */
	List<SysMacroEntity> listMacroValue(String type);

	/**
	 * 根据多个字典类型获得字典map
	 * @param types
	 * @return
	 */
	Map<String,List<SysMacroEntity>> getByTypes(String types);
}
