package com.sinoyoo.familyfunds.mapper;

import com.sinoyoo.familyfunds.pojo.DepositeWithdraw;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawChartPO;
import com.sinoyoo.familyfunds.pojo.DepositeWithdrawExample;
import com.sinoyoo.familyfunds.vo.DepositeWithdrawChartVO;
import com.sinoyoo.familyfunds.vo.LimitQueryVO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepositeWithdrawMapper {
    int countByExample(DepositeWithdrawExample example);

    int deleteByExample(DepositeWithdrawExample example);

    int deleteByPrimaryKey(String id);

    int insert(DepositeWithdraw record);

    int insertSelective(DepositeWithdraw record);

    List<DepositeWithdraw> selectByExample(DepositeWithdrawExample example);

    DepositeWithdraw selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") DepositeWithdraw record, @Param("example") DepositeWithdrawExample example);

    int updateByExample(@Param("record") DepositeWithdraw record, @Param("example") DepositeWithdrawExample example);

    int updateByPrimaryKeySelective(DepositeWithdraw record);

    int updateByPrimaryKey(DepositeWithdraw record);
    
    List<DepositeWithdraw> selectListByLimit(LimitQueryVO queryVO) throws Exception;
    
    List<DepositeWithdrawChartVO> selectDepositeWithdrawGroupByVOList(DepositeWithdrawChartPO dQueryVO);

}