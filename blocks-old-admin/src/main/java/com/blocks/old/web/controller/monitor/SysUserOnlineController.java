package com.blocks.old.web.controller.monitor;

import java.util.List;

import com.blocks.common.data.page.AjaxResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.blocks.old.common.core.controller.BaseController;
import com.blocks.old.common.core.page.TableDataInfo;
import com.blocks.old.common.core.text.Convert;
import com.blocks.old.common.enums.OnlineStatus;
import com.blocks.old.common.utils.ShiroUtils;
import com.blocks.old.framework.shiro.session.OnlineSession;
import com.blocks.old.framework.shiro.session.OnlineSessionDAO;
import com.blocks.old.system.domain.SysUserOnline;
import com.blocks.old.system.service.ISysUserOnlineService;

/**
 * 在线用户监控
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/online")
public class SysUserOnlineController extends BaseController {
    private String prefix = "monitor/online";

    @Autowired
    private ISysUserOnlineService userOnlineService;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @RequiresPermissions("monitor:online:view")
    @GetMapping()
    public String online() {
        return prefix + "/online";
    }

    @RequiresPermissions("monitor:online:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysUserOnline userOnline) {
        startPage();
        List<SysUserOnline> list = userOnlineService.selectUserOnlineList(userOnline);
        return getDataTable(list);
    }

    @RequiresPermissions(value = {"monitor:online:batchForceLogout", "monitor:online:forceLogout"}, logical = Logical.OR)
    @PostMapping("/batchForceLogout")
    @ResponseBody
    public AjaxResult batchForceLogout(String ids) {
        for (String sessionId : Convert.toStrArray(ids)) {
            SysUserOnline online = userOnlineService.selectOnlineById(sessionId);
            if (online == null) {
                return error("用户已下线");
            }
            OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(online.getSessionId());
            if (onlineSession == null) {
                return error("用户已下线");
            }
            if (sessionId.equals(ShiroUtils.getSessionId())) {
                return error("当前登录用户无法强退");
            }
            onlineSessionDAO.delete(onlineSession);
            online.setStatus(OnlineStatus.off_line);
            userOnlineService.saveOnline(online);
            userOnlineService.removeUserCache(online.getLoginName(), sessionId);
        }
        return success();
    }
}
