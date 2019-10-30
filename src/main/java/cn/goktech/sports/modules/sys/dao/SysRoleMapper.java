package cn.goktech.sports.modules.sys.dao;

import java.util.List;

import cn.goktech.sports.modules.sys.entity.SysRoleEntity;
import org.apache.ibatis.annotations.Mapper;

import cn.goktech.sports.modules.sys.entity.SysRoleEntity;

/**
 * 系统角色
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

	/**
	 * 查询用户角色集合
	 * @param userId
	 * @return
	 */
	List<String> listUserRoles(Long userId);
	
}
