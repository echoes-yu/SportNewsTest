package cn.goktech.sports.modules.sys.dao;

import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import cn.goktech.sports.modules.sys.entity.SysMacroEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 通用字典
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysMacroMapper extends BaseMapper<SysMacroEntity> {

	/**
	 * 查询目录集合
	 * @return
	 */
	List<SysMacroEntity> listNotMacro();

	/**
	 * 查询子节点个数
	 * @param typeId
	 * @return
	 */
	int countMacroChildren(Long typeId);

	/**
	 * 根据类型查询所有参数
	 * @param type
	 * @return
	 */
	List<SysMacroEntity> listMacroValue(String type);
	/**
	 * 根据多个字典类型获得字典map
	 * @param typeList 类型集合
	 * @return
	 */
	List<SysMacroEntity> getByTypes(List<String> typeList);
}
