package com.xchen.airecieveandforward.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xchen.airecieveandforward.domain.sys.SysOrgan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 28609
 */
@Mapper
public interface SysOrganDao extends BaseMapper<SysOrgan> {

    /**
     * @param id 对应表中id
     * @return 返回sys_organ;
     */
    @Select("select sys_organ.CODE from sys_organ where sys_organ.ID=${id}")
    public String selectOrganCodeById(int id);
}
