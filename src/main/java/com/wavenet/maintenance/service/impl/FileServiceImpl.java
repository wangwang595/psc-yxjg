/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: UserServiceImpl.java
 * @Package com.wavenet.biz.biz1.service.impl
 * @Description: (用一句话描述该文件做什么)
 * @author: hp
 * @date: 2019/10/21 10:43
 * @version V1.0
 * @Copyright: 2019 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.service.impl;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.JsonObject;
import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.manager.common.MongoDbFile;
import com.wavenet.maintenance.service.FileService;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: (这里用一句话描述这个类的作用)
 * @author: hp
 * @date: 2019/10/21 10:43     
 * @Copyright: 2019 www.wavenet.com.cn. All rights reserved.
 */
@Service
@Data
public class FileServiceImpl implements FileService {

    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Override
    public String addImg(List<MultipartFile> files) {
        String imgIds="";
        try{
            if(files!=null){
                for(MultipartFile m:files){
                    MongoDbFile mongoDbFile=new MongoDbFile();
                    mongoDbFile.setContent(new Binary(m.getBytes()));
                   // mongoDbFile.setType(m.getName().split(".")[1]);
                    //MongoDbFile mongoDbFile1=mongoTemplate.save(mongoDbFile,"sjsb");
                    //imgIds=imgIds+mongoDbFile1.getsId().trim()+",";
                }
            }
            String subs = imgIds.substring(0, imgIds.length() - 1);
            return subs;
        }catch (Exception e){
            return imgIds;
        }
    }

    @Override
    public String addFile(List<MultipartFile> files) {
        String fileIds = "";
        try{
            if(files!=null){
                for(MultipartFile m:files){

                    StorePath path=fastFileStorageClient.uploadFile(m.getInputStream(),m.getSize(),"png",null);
                    fileIds = fileIds +path.getFullPath().trim()+",";
                }

            }
            String subs = fileIds.substring(0, fileIds.length() - 1);
            System.out.println(subs);
            return subs;
        }catch (Exception e){
            System.out.println(fileIds);
            return fileIds;
        }

    }





}
