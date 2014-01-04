package org.opensms.app.controller;

import org.opensms.app.db.entity.Batch;
import org.opensms.app.db.entity.GsrOrder;
import org.opensms.app.db.service.GsrOrderDAOService;
import org.opensms.app.view.entity.GsrOrderModel;
import org.opensms.app.view.model.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dewmal on 1/3/14.
 */
@Controller
@RequestMapping(value = "/gsrorder")
public class GSROrderController {


    @Autowired
    private GsrOrderDAOService gsrOrderDAOService;


    @RequestMapping(value = "/save",method = RequestMethod.PUT)
    public ResponseMessage createGsrOrder(@RequestBody GsrOrderModel gsrOrderModel){
        Long id=gsrOrderDAOService.save(gsrOrderModel);
        return new ResponseMessage(ResponseMessage.Type.success,"ok");
    }




}
