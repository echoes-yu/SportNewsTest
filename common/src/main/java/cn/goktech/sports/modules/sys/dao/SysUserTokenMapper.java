package cn.goktech.sports.modules.sys.dao;

import cn.goktech.sports.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 用户token
 * @author zcl<yczclcn@163.com>
 */
@Mapper
@Repository
public interface SysUserTokenMapper extends BaseMapper<SysUserTokenEntity> {

	/**
	 * 根据token查询
	 * @param token
	 * @return
	 */
	SysUserTokenEntity getByToken(String token);

	/**
	 * 根据用户id查询
	 * @param userId
	 * @return
	 */
	SysUserTokenEntity getByUserId(Long userId);
	
}
