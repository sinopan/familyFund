package com.sinoyoo.familyfunds.mapper;

import com.sinoyoo.familyfunds.pojo.FundPlan;
import com.sinoyoo.familyfunds.pojo.FundPlanExample;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundPlanMapper {
    int countByExample(FundPlanExample example);

    int deleteByExample(FundPlanExample example);

    int deleteByPrimaryKey(String id);

    int insert(FundPlan record);

    int insertSelective(FundPlan record);

    List<FundPlan> selectByExample(FundPlanExample example);

    FundPlan selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") FundPlan record, @Param("example") FundPlanExample example);

    int updateByExample(@Param("record") FundPlan record, @Param("example") FundPlanExample example);

    int updateByPrimaryKeySelective(FundPlan record);

    int updateByPrimaryKey(FundPlan record);
    
    List<FundPlan> selectListByLimit(LimitQueryVO queryVO) throws Exception;
}