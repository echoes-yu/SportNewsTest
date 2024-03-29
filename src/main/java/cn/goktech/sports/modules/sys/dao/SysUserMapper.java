package cn.goktech.sports.modules.sys.dao;

import java.util.List;

import cn.goktech.sports.modules.sys.entity.SysUserEntity;
import org.apache.ibatis.annotations.Mapper;

import cn.goktech.sports.common.entity.Query;
import cn.goktech.sports.modules.sys.entity.SysUserEntity;

/**
 * 系统用户dao
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	SysUserEntity getByUserName(String username);

	/**
	 * 查询用户所有菜单id
	 * @param userId
	 * @return
	 */
	List<Long> listAllMenuId(Long userId);

	/**
	 * 查询用户所有机构id
	 * @param userId
	 * @return
	 */
	List<Long> listAllOrgId(Long userId);

	/**
	 * 用户修改密码
	 * @param query
	 * @return
	 */
	int updatePswdByUser(Query query);

	/**
	 * 更新用户状态
	 * @param query
	 * @return
	 */
	int updateUserStatus(Query query);

	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	int updatePswd(SysUserEntity user);
   //查账号
	String queryAcount(String zh);
	
}
