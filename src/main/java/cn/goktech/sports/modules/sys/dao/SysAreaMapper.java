package cn.goktech.sports.modules.sys.dao;

import cn.goktech.sports.common.entity.Query;
import cn.goktech.sports.modules.sys.entity.SysAreaEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 行政区域
 * @author zcl<yczclcn@163.com>
 */
@Mapper
public interface SysAreaMapper extends BaseMapper<SysAreaEntity> {

	/**
	 * 根据父计编码查询
	 * @param query
	 * @return
	 */
	List<SysAreaEntity> listAreaByParentCode(Query query);

	/**
	 * 子节点总数
	 * @param areaId
	 * @return
	 */
	int countAreaChildren(Long areaId);

    /**
     * 根据层级获取区域
     * @param layer
     * @return
     */
    List<SysAreaEntity> listAreaByLayer(Integer layer);

}
