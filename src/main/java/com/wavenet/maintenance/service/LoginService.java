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

package com.wavenet.maintenance.service;

import com.wavenetframework.boot.shiro.jwt.JwtToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * <p>
 * 登录服务接口
 * </p>
 *
 * @author geekidea
 * @date 2019-05-23
 **/
public interface LoginService {




    /**
     * 退出
     *
     * @param request
     */
    void logout(HttpServletRequest request) throws Exception;




}
