package org.java.service.xu;

import org.java.entity.TranOrder;

public interface OrderService {

    TranOrder selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(TranOrder record);

}
