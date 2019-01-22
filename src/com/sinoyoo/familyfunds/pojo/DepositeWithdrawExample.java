package com.sinoyoo.familyfunds.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DepositeWithdrawExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepositeWithdrawExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountIsNull() {
            addCriterion("exchange_amount is null");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountIsNotNull() {
            addCriterion("exchange_amount is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountEqualTo(Float value) {
            addCriterion("exchange_amount =", value, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountNotEqualTo(Float value) {
            addCriterion("exchange_amount <>", value, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountGreaterThan(Float value) {
            addCriterion("exchange_amount >", value, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountGreaterThanOrEqualTo(Float value) {
            addCriterion("exchange_amount >=", value, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountLessThan(Float value) {
            addCriterion("exchange_amount <", value, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountLessThanOrEqualTo(Float value) {
            addCriterion("exchange_amount <=", value, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountIn(List<Float> values) {
            addCriterion("exchange_amount in", values, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountNotIn(List<Float> values) {
            addCriterion("exchange_amount not in", values, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountBetween(Float value1, Float value2) {
            addCriterion("exchange_amount between", value1, value2, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeAmountNotBetween(Float value1, Float value2) {
            addCriterion("exchange_amount not between", value1, value2, "exchangeAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeIsNull() {
            addCriterion("exchange_time is null");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeIsNotNull() {
            addCriterion("exchange_time is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeEqualTo(Date value) {
            addCriterion("exchange_time =", value, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeNotEqualTo(Date value) {
            addCriterion("exchange_time <>", value, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeGreaterThan(Date value) {
            addCriterion("exchange_time >", value, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("exchange_time >=", value, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeLessThan(Date value) {
            addCriterion("exchange_time <", value, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeLessThanOrEqualTo(Date value) {
            addCriterion("exchange_time <=", value, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeIn(List<Date> values) {
            addCriterion("exchange_time in", values, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeNotIn(List<Date> values) {
            addCriterion("exchange_time not in", values, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeBetween(Date value1, Date value2) {
            addCriterion("exchange_time between", value1, value2, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangeTimeNotBetween(Date value1, Date value2) {
            addCriterion("exchange_time not between", value1, value2, "exchangeTime");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceIsNull() {
            addCriterion("exchange_place is null");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceIsNotNull() {
            addCriterion("exchange_place is not null");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceEqualTo(String value) {
            addCriterion("exchange_place =", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceNotEqualTo(String value) {
            addCriterion("exchange_place <>", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceGreaterThan(String value) {
            addCriterion("exchange_place >", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_place >=", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceLessThan(String value) {
            addCriterion("exchange_place <", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceLessThanOrEqualTo(String value) {
            addCriterion("exchange_place <=", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceLike(String value) {
            addCriterion("exchange_place like", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceNotLike(String value) {
            addCriterion("exchange_place not like", value, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceIn(List<String> values) {
            addCriterion("exchange_place in", values, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceNotIn(List<String> values) {
            addCriterion("exchange_place not in", values, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceBetween(String value1, String value2) {
            addCriterion("exchange_place between", value1, value2, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangePlaceNotBetween(String value1, String value2) {
            addCriterion("exchange_place not between", value1, value2, "exchangePlace");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilIsNull() {
            addCriterion("exchange_deatil is null");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilIsNotNull() {
            addCriterion("exchange_deatil is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilEqualTo(String value) {
            addCriterion("exchange_deatil =", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilNotEqualTo(String value) {
            addCriterion("exchange_deatil <>", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilGreaterThan(String value) {
            addCriterion("exchange_deatil >", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_deatil >=", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilLessThan(String value) {
            addCriterion("exchange_deatil <", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilLessThanOrEqualTo(String value) {
            addCriterion("exchange_deatil <=", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilLike(String value) {
            addCriterion("exchange_deatil like", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilNotLike(String value) {
            addCriterion("exchange_deatil not like", value, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilIn(List<String> values) {
            addCriterion("exchange_deatil in", values, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilNotIn(List<String> values) {
            addCriterion("exchange_deatil not in", values, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilBetween(String value1, String value2) {
            addCriterion("exchange_deatil between", value1, value2, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeDeatilNotBetween(String value1, String value2) {
            addCriterion("exchange_deatil not between", value1, value2, "exchangeDeatil");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIsNull() {
            addCriterion("exchange_type is null");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIsNotNull() {
            addCriterion("exchange_type is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeEqualTo(Integer value) {
            addCriterion("exchange_type =", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotEqualTo(Integer value) {
            addCriterion("exchange_type <>", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeGreaterThan(Integer value) {
            addCriterion("exchange_type >", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("exchange_type >=", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeLessThan(Integer value) {
            addCriterion("exchange_type <", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("exchange_type <=", value, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeIn(List<Integer> values) {
            addCriterion("exchange_type in", values, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotIn(List<Integer> values) {
            addCriterion("exchange_type not in", values, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeBetween(Integer value1, Integer value2) {
            addCriterion("exchange_type between", value1, value2, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andExchangeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("exchange_type not between", value1, value2, "exchangeType");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdIsNull() {
            addCriterion("fund_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdIsNotNull() {
            addCriterion("fund_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdEqualTo(String value) {
            addCriterion("fund_plan_id =", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdNotEqualTo(String value) {
            addCriterion("fund_plan_id <>", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdGreaterThan(String value) {
            addCriterion("fund_plan_id >", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("fund_plan_id >=", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdLessThan(String value) {
            addCriterion("fund_plan_id <", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdLessThanOrEqualTo(String value) {
            addCriterion("fund_plan_id <=", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdLike(String value) {
            addCriterion("fund_plan_id like", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdNotLike(String value) {
            addCriterion("fund_plan_id not like", value, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdIn(List<String> values) {
            addCriterion("fund_plan_id in", values, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdNotIn(List<String> values) {
            addCriterion("fund_plan_id not in", values, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdBetween(String value1, String value2) {
            addCriterion("fund_plan_id between", value1, value2, "fundPlanId");
            return (Criteria) this;
        }

        public Criteria andFundPlanIdNotBetween(String value1, String value2) {
            addCriterion("fund_plan_id not between", value1, value2, "fundPlanId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}