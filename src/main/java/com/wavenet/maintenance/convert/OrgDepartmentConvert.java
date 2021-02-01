package com.wavenet.maintenance.convert;


import com.wavenet.maintenance.dao.entity.OrgDept;
import com.wavenet.maintenance.web.vo.OrgDeptTreeVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sun.font.CompositeFont;

import java.util.List;
@Mapper
public interface OrgDepartmentConvert {

    OrgDepartmentConvert INSTANCE = Mappers.getMapper(OrgDepartmentConvert.class);


    /**
     * OrgDept转换成OrgDeptTreeVo对象
     *
     * @param orgDept
     * @return
     */
    OrgDeptTreeVo entityToTreeVo(OrgDept orgDept);

    /**
     * SysDepartment列表转换成SysDepartmentTreeVo列表
     *
     * @param list
     * @return
     */
    List<OrgDeptTreeVo> listToTreeVoList(List<OrgDept> list);

}