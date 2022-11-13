package com.xchen.airecieveandforward.dao.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xchen.airecieveandforward.domain.sys.SysUer;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 28609
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUer> {

    /**
     * @param name 用户名
     * @param orgCode 所属部门编码
     * @return 用户主键list
     */
    @Select("select ID from sys_user where sys_user.NAME=#{name} and sys_user.ORG_CODE=#{orgCode}")
    public List<Long> selectIdByNameAndOrgCode(String name, String orgCode);
}
