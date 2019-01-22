package com.sinoyoo.familyfunds.mapper;

import com.sinoyoo.familyfunds.pojo.FamilyActivity;
import com.sinoyoo.familyfunds.pojo.FamilyActivityExample;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FamilyActivityMapper {
    int countByExample(FamilyActivityExample example);

    int deleteByExample(FamilyActivityExample example);

    int deleteByPrimaryKey(String id);

    int insert(FamilyActivity record);

    int insertSelective(FamilyActivity record);

    List<FamilyActivity> selectByExample(FamilyActivityExample example);

    FamilyActivity selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FamilyActivity record, @Param("example") FamilyActivityExample example);

    int updateByExample(@Param("record") FamilyActivity record, @Param("example") FamilyActivityExample example);

    int updateByPrimaryKeySelective(FamilyActivity record);

    int updateByPrimaryKey(FamilyActivity record);
    
    List<FamilyActivity> selectListByLimit(LimitQueryVO queryVO) throws Exception;
}