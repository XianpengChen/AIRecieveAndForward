package com.xchen.airecieveandforward.dao.supervision;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xchen.airecieveandforward.domain.supervision.SuperVisionCorrespondence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author 28609
 */
@Mapper
public interface SuperVisionCorrespondenceDao extends BaseMapper<SuperVisionCorrespondence> {
    /**
     * @param id 对应表中的organ_id
     * @return 返回一行
     * FindById
     */
    @Select("select * from supervision_correspondence where organID=#{id}")
    public SuperVisionCorrespondence selectById(Long id);

    /**
     * @param organId sys_organ的主键
     * 根据组织主键更新用户主键
     */
    @Update("update supervision_correspondence set user_id={#userId} where organ_id={#organId}")
    public void updateUserIdByOrganId(Long organId, Long userId);
}
