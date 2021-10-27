package com.fdkj.ky.controller.ky.xm.zxxm;

import com.fdkj.ky.annotation.Log;
import com.fdkj.ky.api.util.ky.xm.zxxm.Zxxm_psApi;
import com.fdkj.ky.api.util.ky.xm.zxxm.Zxxm_zjApi;
import com.fdkj.ky.api.util.sys.DictApi;
import com.fdkj.ky.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 纵向项目 - 评审
 *
 * @author wyt
 */
@Controller
@RequestMapping("CZF/KYXM/ZXXM/PS")
@Log(module = "纵向项目-评审")
public class Zxxm_psController extends BaseController {
    private static final Logger log = LoggerFactory.getLogger(Zxxm_psController.class);

    @Autowired
    private DictApi dictApi;
    @Autowired
    private Zxxm_psApi zxxm_psApi;


}
