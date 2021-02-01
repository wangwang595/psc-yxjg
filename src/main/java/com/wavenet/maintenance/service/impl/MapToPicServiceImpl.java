package com.wavenet.maintenance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.wavenet.maintenance.dao.entity.Trajectory;
import com.wavenet.maintenance.dao.mapper.PatrolInfoMapper;
import com.wavenet.maintenance.dao.mapper.TrajectoryMapper;
import com.wavenet.maintenance.manager.common.util.HttpUtil;
import com.wavenet.maintenance.service.MapToPicService;
import com.wavenet.maintenance.service.PatrolGpsHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName MapToPicServiceImpl
 * @Description TODO
 * @Author 叶方哲
 * @Date 2020/7/13 13:58
 * @Version 1.0
 */
@Service
@DS("permission1")
public class MapToPicServiceImpl implements MapToPicService {

    @Resource
    private TrajectoryMapper mapper;
    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PatrolInfoMapper patrolInfoMapper;
    @Resource
    private PatrolGpsHistoryService service;


    @Override
    public JSONObject getMapToPic(String patrolCode, String str) {
        //根据道路编号查询轨迹表
        List<Trajectory> list = mapper.getTrajectoryByPatrolCode(patrolCode);
        JSONObject resultData = new JSONObject();
        if (list!=null && list.size()>0){
            String GisIp = "106.75.229.99:6080";
            Double minx= Double.valueOf(list.get(0).getMinx());
            Double maxx= Double.valueOf(list.get(0).getMaxx());
            Double miny= Double.valueOf(list.get(0).getMiny());
            Double maxy= Double.valueOf(list.get(0).getMaxy());
            String baseMap = "http://106.75.229.99:6080/arcgis/rest/services/YP_GDYHJGPT/YPBaseMap/MapServer";
            String result = getPic(GisIp, minx.toString(), miny.toString(), maxx.toString(), maxy.toString(), baseMap, str);
            JSONObject jsonObject = JSONObject.parseObject(result);
            String picPath = jsonObject.get("results") != null ? jsonObject.getJSONArray("results").getJSONObject(0).getJSONObject("value").getString("url"):"";
            String picUrl = "";
            try {
                URL url = new URL(picPath);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                StorePath path=fastFileStorageClient.uploadFile(inputStream,inputStream.available(),"png",null);
                picUrl = path.getFullPath();
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            resultData.put("patrolCode",patrolCode);
            resultData.put("picUrl",picUrl);
//            resultData.put("picUrl",picPath);
            return resultData;
        }

        return resultData;
    }

    public String getPic(String GisIp,String xmin,String ymin,String xmax,String ymax,String baseMap,String str) {

        String str1 = str.replace(",","],[");
        String str2 = str1.replace(" ",",");
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(str2);
        sb.append("]");
        String xyData = sb.toString();
        String json = "{'mapOptions':{'showAttribution':true,'extent':{'xmin':"+xmin+",'ymin':"+ymin+",'xmax':"+xmax+",'ymax':"+ymax+",'spatialReference':{'wkid':4326,'latestWkid':4326}},'spatialReference':{'wkid':4326,'latestWkid':4326},'scale':32000.00059353986},'operationalLayers':[{'id':'layer0','title':'layer0','opacity':1,'minScale':2000000,'maxScale':1000,'url':'http://106.75.229.99:2084/arcgis/rest/services/YP_GDYHJGPT/YPBaseMap/MapServer'},{'id':'mapDiv_graphics','opacity':1,'minScale':0,'maxScale':0,'featureCollection':{'layers':[{'layerDefinition':{'name':'polylineLayer','geometryType':'esriGeometryPolyline'},'featureSet':{'geometryType':'esriGeometryPolyline','features':[{'geometry':{'paths':[["+xyData+"]],'spatialReference':{'wkid':4326,'latestWkid':4326}},'symbol':{'color':[255,0,0,255],'width':2.25,'type':'esriSLS','style':'esriSLSSolid'}}]}}]}}],'exportOptions':{'outputSize':[1200,1200],'dpi':96}}";
        try {
            json = URLEncoder.encode(json,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String url = "http://"+GisIp+"/arcgis/rest/services/Utilities/PrintingTools/GPServer/Export%20Web%20Map%20Task/execute?Web_Map_as_JSON="+json+"&Format=png32&Layout_Template=MAP_ONLY&f=json";

        HttpUtil httpUtil = new HttpUtil();
        String result = httpUtil.executeGet(url, "", "UTF-8");
        System.out.println("gis服务返回数据 === "+result);

        return result;
    }

}
