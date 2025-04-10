package com.heysong.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 8912
 * @Date: 2025/3/16 0:55
 * @Version: v1.0.0
 * @Description:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseQuery {
    private String jwt;
    private String dySql;
}