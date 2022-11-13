package com.xchen.airecieveandforward;

import com.xchen.airecieveandforward.dao.supervision.SuperVisionCorrespondenceDao;
import com.xchen.airecieveandforward.dao.sys.SysOrganDao;
import com.xchen.airecieveandforward.dao.sys.SysUserDao;
import com.xchen.airecieveandforward.domain.supervision.SuperVisionCorrespondence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AiReceiveAndForwardApplicationTests {
    @Autowired
    private SuperVisionCorrespondenceDao superVisionCorrespondenceDao;
    @Autowired
    private SysOrganDao sysOrganDao;
    @Autowired
    private SysUserDao sysUserDao;

    @Test
    void testGetAll() {
        List<SuperVisionCorrespondence> superVisionCorrespondences = superVisionCorrespondenceDao.selectList(null);
        System.out.println(superVisionCorrespondences);
    }
    @Test
    void testFindById() {
        SuperVisionCorrespondence superVisionCorrespondence = superVisionCorrespondenceDao.selectById(33563L);
        System.out.println(superVisionCorrespondence);
    }
    @Test
    void testFindByIdOrgan() {
        String s = sysOrganDao.selectOrganCodeById(315);
        System.out.println(s);
    }
    @Test
    void testSelectIdByNameAndOrgCode() {
        List<Long> list = sysUserDao.selectIdByNameAndOrgCode("邓杰", "5001170206");
        System.out.println(list);
    }
}
