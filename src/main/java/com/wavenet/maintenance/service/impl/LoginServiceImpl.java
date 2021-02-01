/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wavenet.maintenance.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wavenet.maintenance.service.LoginService;
import com.wavenetframework.boot.core.properties.SpringBootPlusProperties;
import com.wavenetframework.boot.shiro.cache.LoginRedisService;
import com.wavenetframework.boot.shiro.jwt.JwtProperties;
import com.wavenetframework.boot.shiro.util.JwtTokenUtil;
import com.wavenetframework.boot.shiro.util.JwtUtil;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 登录服务实现类
 * </p>
 *
 * @author geekidea
 * @date 2019-05-23
 **/
@Api
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Lazy
    @Autowired
    private LoginRedisService loginRedisService;

    @Lazy
    @Autowired
    private JwtProperties jwtProperties;



    @Lazy
    @Autowired
    private SpringBootPlusProperties springBootPlusProperties;

    @Lazy
    @Autowired
    private RedisTemplate redisTemplate;








    @Override
    public void logout(HttpServletRequest request) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        //注销
        subject.logout();
        // 获取token
        String token = JwtTokenUtil.getToken(request);
        String username = JwtUtil.getUsername(token);
        // 删除Redis缓存信息
        loginRedisService.deleteLoginInfo(token, username);
        log.info("登出成功,username:{},token:{}", username, token);
    }


}
