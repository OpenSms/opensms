package org.opensms.app.db.controller.impl;

import org.opensms.app.db.controller.PaymentMethodDAO;
import org.opensms.app.db.entity.PaymentMethod;
import org.springframework.stereotype.Repository;

/**
 * Created by sadika on 1/3/14.
 */
@Repository
public class PaymentMethodDAOImpl extends AbstractDAOImpl<PaymentMethod, Integer> implements PaymentMethodDAO {

    public PaymentMethodDAOImpl() {
        super(PaymentMethod.class, Integer.class);
    }
}
