/**
 * All rights Reserved, Designed By www.wavenet.com.cn
 *
 * @Title: UserController.java
 * @Package com.wavenet.biz.biz1.controller
 * @Description: (用一句话描述该文件做什么)
 * @author: hp
 * @date: 2019/10/21 10:32
 * @version V1.0
 * @Copyright: 2019 www.wavenet.com.cn. All rights reserved.
 * 注意：本内容仅限于上海网波软件股份有限公司内部传阅，禁止外泄以及用于其他的商业目
 */
package com.wavenet.maintenance.web;



import com.alibaba.fastjson.JSONObject;
import com.wavenet.maintenance.dao.entity.PatrolGpsHistory;
import com.wavenet.maintenance.manager.common.MongoDbFile;
import com.wavenet.maintenance.service.FileService;
import com.wavenetframework.boot.common.api.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


/**
 * @ClassName: UserController
 * @Description: (用户账户)
 * @author: hp
 * @date: 2019/10/21 10:32     
 * @Copyright: 2019 www.wavenet.com.cn. All rights reserved.
 */
@Controller
@RequestMapping(value = "/file")
@Api(tags = "文件操作")
@CrossOrigin
public class FileController {

    @Autowired
    private FileService mFileService;

    @Autowired
    @Qualifier("mongoTemplate")
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "添加图片")
    @RequestMapping(value = "/addImg",method = {RequestMethod.POST})
    @ResponseBody
    public ApiResult<String> addXjImg(List<MultipartFile> file) {
        String ids=mFileService.addImg(file);
        if(ids.equals("")){
            return ApiResult.fail();
        }
        return ApiResult.ok(ids);
    }



//    @ApiOperation(value = "单张图片")
//    @RequestMapping(value = "/image/{id}", produces = MediaType.ALL_VALUE,method = {RequestMethod.GET})
//    @ResponseBody
//    public ResponseEntity<byte[]> image(HttpServletResponse reponse, @PathVariable String id) {
//
//
//        try {
//
//            MongoDbFile file = mongoTemplate.findById(id, MongoDbFile.class,"sjsb");
//            BufferedImage img = null;
//            ByteArrayInputStream bais = new ByteArrayInputStream(file.getContent().getData());
//            img=ImageIO.read(bais);
//
//            ImageIO.write(img, "JPG", reponse.getOutputStream());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @ApiOperation(value = "单个视频")
//    @RequestMapping(value = "/video/{id}", produces = MediaType.ALL_VALUE,method = {RequestMethod.GET})
//    @ResponseBody
//    public ResponseEntity<byte[]> video(HttpServletResponse reponse, @PathVariable String id) {
//
//
//        try {
//
//            MongoDbFile file = mongoTemplate.findById(id, MongoDbFile.class);
//
//            reponse.getOutputStream().write(file.getContent().getData());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/addFile", method = {RequestMethod.POST})
    @ResponseBody
    public ApiResult<?> addFile( List<MultipartFile> file) {
        String s = mFileService.addFile(file);
        return ApiResult.ok(s);
    }




}
