package com.lmxdawn.him.api.vo.req;

import com.lmxdawn.him.common.vo.req.BaseReqVO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * 删除好友
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFriendDeleteReqVO extends BaseReqVO {

    /**
     * 朋友的ID
     */
    @NotNull(message = "参数错误！")
    private Long friendUid;

}
