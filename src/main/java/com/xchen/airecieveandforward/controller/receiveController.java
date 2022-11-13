package com.xchen.airecieveandforward.controller;

import com.alibaba.fastjson.JSONObject;
import com.xchen.airecieveandforward.dao.supervision.SuperVisionCorrespondenceDao;
import com.xchen.airecieveandforward.dao.sys.SysOrganDao;
import com.xchen.airecieveandforward.dao.sys.SysUserDao;
import com.xchen.airecieveandforward.domain.Message;
import com.xchen.airecieveandforward.domain.supervision.SuperVisionCorrespondence;
import org.apache.commons.codec.Charsets;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
@RestController
public class receiveController {
    @Autowired
    private SuperVisionCorrespondenceDao superVisionCorrespondenceDao;
    @Autowired
    private SysOrganDao sysOrganDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Value("${postURL}")
    private String postURL;

    private static final Logger logger = LoggerFactory.getLogger("预警消息接收转发");
    /**
     * 当浏览器在请求资源时，会通过http返回头中的content-type决定如何显示/处理将要加载的数据，如果这个类型浏览器能够支持阅览，浏览器就会直
     * 接展示该资源，比如png、jpeg、video等格式。在某些下载文件的场景中，服务端可能会返回文件流，并在返回头中带上Content-Type:applicati
     * on/octet-stream，告知浏览器这是一个字节流，浏览器处理字节流的默认方式就是下载。Application/octet-stream是应用程序文件的默认值。
     * 意思是未知的应用程序文件，浏览器一般不会自动执行或询问执行。浏览器会像对待，设置了HTTP头Content-Disposition值为attachment的文件一
     * 样来对待这类文件，即浏览器会触发下载行为。说人话就是，浏览器并不认得这是什么类型，也不知道应该如何展示，只知道这是一种二进制文件，因此
     * 遇到content-type为application/octet-stream的文件时，浏览器会直接把它下载下来。这个类型一般会配合另一个响应头Content-Disposition,
     * 该响应头指示回复的内容该以何种形式展示，是以内联的形式（即网页或者网页的一部分），还是以附件的形式下载并保存到本地。
     * @param req
     * @return
     */
    @RequestMapping(value = "/UpVmsRecord", method = RequestMethod.POST)
    public String demo(HttpServletRequest req) {
        JSONObject jsonObject;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String s = null;
            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            jsonObject = JSONObject.parseObject(sb.toString());
            String ChnName = jsonObject.getString("ChnName");
            List<String> list = Arrays.asList(ChnName.split("-"));
            int organID = Integer.parseInt(list.get(0));
            JSONObject object = findAndPack(organID, jsonObject);
            logger.info(object.toString());
            doPost(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "server response";
    }
    private JSONObject findAndPack(int organID, JSONObject jsonObject) throws IOException {
        JSONObject object = new JSONObject();
        List<String> columnNames = Message.getColumnName();
        for (String a:columnNames) {
            object.put(a, jsonObject.getString(a));
        }
        SuperVisionCorrespondence superVisionCorrespondence = superVisionCorrespondenceDao.selectById((long) organID);
        if (superVisionCorrespondence.getUserId() < 0) {
            List<Long> longs = sysUserDao.selectIdByNameAndOrgCode(superVisionCorrespondence.getUserName(), sysOrganDao.selectOrganCodeById(organID));
            if (longs.size() > 0) {
                superVisionCorrespondence.setUserId(longs.get(0).intValue());
                superVisionCorrespondenceDao.updateUserIdByOrganId((long) organID, longs.get(0));
            }
        }
        object.put("organID", organID);
        object.put("organName", superVisionCorrespondence.getOrganName());
        object.put("userID", superVisionCorrespondence.getUserId());
        object.put("userName", superVisionCorrespondence.getUserName());
        object.put("userPhone", superVisionCorrespondence.getUserPhone());
        return object;
    }
    private void doPost(JSONObject object) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(postURL);
        httpPost.addHeader("Content-Type", "text/plain;charset=UTF-8");
        httpPost.setHeader("Accept", "text/plain");
        CloseableHttpResponse httpResponse = null;
        try {
            //必须加上UTF-8，否则接收端中文乱码
            StringEntity entity = new StringEntity(object.toString(), Charsets.UTF_8);
            httpPost.setEntity(entity);
            httpResponse = client.execute(httpPost);
            logger.info(httpResponse.toString());
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                System.out.println(content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
        }
        client.close();
    }
}
