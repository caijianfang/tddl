package com.taobao.tddl.executor.function.scalar.bit;

import com.taobao.tddl.executor.common.ExecutionContext;
import com.taobao.tddl.executor.function.ScalarFunction;
import com.taobao.tddl.optimizer.core.datatype.DataType;

/**
 * 对应mysql的bitor '|'
 * 
 * @author jianghang 2014-2-13 上午11:55:29
 * @since 5.0.0
 */
public class BitOr extends ScalarFunction {

    @Override
    public Object compute(Object[] args, ExecutionContext ec) {
        return computeInner(args);
    }

    private Object computeInner(Object[] args) {
        DataType type = this.getReturnType();
        return type.getCalculator().bitOr(args[0], args[1]);
    }

    @Override
    public DataType getReturnType() {
        DataType type = getFirstArgType();
        if (type == DataType.BigIntegerType || type == DataType.BigDecimalType) {
            return DataType.BigIntegerType;
        } else {
            return DataType.LongType;
        }
    }

    @Override
    public String[] getFunctionNames() {
        return new String[] { "|" };
    }
}
