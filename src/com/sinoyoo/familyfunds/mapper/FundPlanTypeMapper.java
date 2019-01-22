package com.sinoyoo.familyfunds.mapper;

import com.sinoyoo.familyfunds.pojo.FundPlanType;
import com.sinoyoo.familyfunds.pojo.FundPlanTypeExample;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FundPlanTypeMapper {
    int countByExample(FundPlanTypeExample example);

    int deleteByExample(FundPlanTypeExample example);

    int insert(FundPlanType record);

    int insertSelective(FundPlanType record);

    List<FundPlanType> selectByExample(FundPlanTypeExample example);

    int updateByExampleSelective(@Param("record") FundPlanType record, @Param("example") FundPlanTypeExample example);

    int updateByExample(@Param("record") FundPlanType record, @Param("example") FundPlanTypeExample example);
    
    List<FundPlanType> selectListByLimit(LimitQueryVO queryVO) throws Exception;
}