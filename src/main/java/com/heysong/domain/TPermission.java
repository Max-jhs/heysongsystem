package com.heysong.domain;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 权限表
 */
@Data
public class TPermission {
    @Setter
    @Getter
    private Integer id;

    private String name;

    private String code;

    private String url;

    private String type;

    private Integer parentId;

    private Integer orderNo;

    private String icon;

    private List<TPermission> children;
}