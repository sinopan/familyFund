package com.sinoyoo.familyfunds.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FundPlanExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FundPlanExample() {
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

        public Criteria andAmountGoalIsNull() {
            addCriterion("amount_goal is null");
            return (Criteria) this;
        }

        public Criteria andAmountGoalIsNotNull() {
            addCriterion("amount_goal is not null");
            return (Criteria) this;
        }

        public Criteria andAmountGoalEqualTo(Float value) {
            addCriterion("amount_goal =", value, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalNotEqualTo(Float value) {
            addCriterion("amount_goal <>", value, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalGreaterThan(Float value) {
            addCriterion("amount_goal >", value, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalGreaterThanOrEqualTo(Float value) {
            addCriterion("amount_goal >=", value, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalLessThan(Float value) {
            addCriterion("amount_goal <", value, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalLessThanOrEqualTo(Float value) {
            addCriterion("amount_goal <=", value, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalIn(List<Float> values) {
            addCriterion("amount_goal in", values, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalNotIn(List<Float> values) {
            addCriterion("amount_goal not in", values, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalBetween(Float value1, Float value2) {
            addCriterion("amount_goal between", value1, value2, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andAmountGoalNotBetween(Float value1, Float value2) {
            addCriterion("amount_goal not between", value1, value2, "amountGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalIsNull() {
            addCriterion("realize_goal is null");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalIsNotNull() {
            addCriterion("realize_goal is not null");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalEqualTo(Float value) {
            addCriterion("realize_goal =", value, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalNotEqualTo(Float value) {
            addCriterion("realize_goal <>", value, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalGreaterThan(Float value) {
            addCriterion("realize_goal >", value, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalGreaterThanOrEqualTo(Float value) {
            addCriterion("realize_goal >=", value, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalLessThan(Float value) {
            addCriterion("realize_goal <", value, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalLessThanOrEqualTo(Float value) {
            addCriterion("realize_goal <=", value, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalIn(List<Float> values) {
            addCriterion("realize_goal in", values, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalNotIn(List<Float> values) {
            addCriterion("realize_goal not in", values, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalBetween(Float value1, Float value2) {
            addCriterion("realize_goal between", value1, value2, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andRealizeGoalNotBetween(Float value1, Float value2) {
            addCriterion("realize_goal not between", value1, value2, "realizeGoal");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNull() {
            addCriterion("plan_type is null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIsNotNull() {
            addCriterion("plan_type is not null");
            return (Criteria) this;
        }

        public Criteria andPlanTypeEqualTo(String value) {
            addCriterion("plan_type =", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotEqualTo(String value) {
            addCriterion("plan_type <>", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThan(String value) {
            addCriterion("plan_type >", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeGreaterThanOrEqualTo(String value) {
            addCriterion("plan_type >=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThan(String value) {
            addCriterion("plan_type <", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLessThanOrEqualTo(String value) {
            addCriterion("plan_type <=", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeLike(String value) {
            addCriterion("plan_type like", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotLike(String value) {
            addCriterion("plan_type not like", value, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeIn(List<String> values) {
            addCriterion("plan_type in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotIn(List<String> values) {
            addCriterion("plan_type not in", values, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeBetween(String value1, String value2) {
            addCriterion("plan_type between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanTypeNotBetween(String value1, String value2) {
            addCriterion("plan_type not between", value1, value2, "planType");
            return (Criteria) this;
        }

        public Criteria andPlanStateIsNull() {
            addCriterion("plan_state is null");
            return (Criteria) this;
        }

        public Criteria andPlanStateIsNotNull() {
            addCriterion("plan_state is not null");
            return (Criteria) this;
        }

        public Criteria andPlanStateEqualTo(Integer value) {
            addCriterion("plan_state =", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateNotEqualTo(Integer value) {
            addCriterion("plan_state <>", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateGreaterThan(Integer value) {
            addCriterion("plan_state >", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("plan_state >=", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateLessThan(Integer value) {
            addCriterion("plan_state <", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateLessThanOrEqualTo(Integer value) {
            addCriterion("plan_state <=", value, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateIn(List<Integer> values) {
            addCriterion("plan_state in", values, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateNotIn(List<Integer> values) {
            addCriterion("plan_state not in", values, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateBetween(Integer value1, Integer value2) {
            addCriterion("plan_state between", value1, value2, "planState");
            return (Criteria) this;
        }

        public Criteria andPlanStateNotBetween(Integer value1, Integer value2) {
            addCriterion("plan_state not between", value1, value2, "planState");
            return (Criteria) this;
        }

        public Criteria andNoteIsNull() {
            addCriterion("note is null");
            return (Criteria) this;
        }

        public Criteria andNoteIsNotNull() {
            addCriterion("note is not null");
            return (Criteria) this;
        }

        public Criteria andNoteEqualTo(String value) {
            addCriterion("note =", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotEqualTo(String value) {
            addCriterion("note <>", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThan(String value) {
            addCriterion("note >", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("note >=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThan(String value) {
            addCriterion("note <", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("note <=", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteLike(String value) {
            addCriterion("note like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotLike(String value) {
            addCriterion("note not like", value, "note");
            return (Criteria) this;
        }

        public Criteria andNoteIn(List<String> values) {
            addCriterion("note in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotIn(List<String> values) {
            addCriterion("note not in", values, "note");
            return (Criteria) this;
        }

        public Criteria andNoteBetween(String value1, String value2) {
            addCriterion("note between", value1, value2, "note");
            return (Criteria) this;
        }

        public Criteria andNoteNotBetween(String value1, String value2) {
            addCriterion("note not between", value1, value2, "note");
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