package com.heysong.mapper;

import com.heysong.aspect.DynamicSql;
import com.heysong.domain.LoginUser;
import com.heysong.domain.TPermission;
import com.heysong.domain.TUser;
import com.heysong.model.query.BaseQuery;
import com.heysong.model.vo.OwnerPO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
  *@Author: 8912
  *@Date: 2025/2/25 23:28
  *@Version: v1.0.0
  *@Description: 
 **/

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    LoginUser selectByAct(String loginAct);

    List<String> selectUserRole(Integer loginId);

    List<String> selectPermission(Integer loginId);

    @DynamicSql(tableName = "tu",idColumn = "id")
    List<TUser> selectUsers(BaseQuery query);

    List<TUser> selectUsers();

    Integer updateByUserInfo(TUser tUser);

    @Update("update t_user set last_login_time = #{now} where id = #{uid}")
    void updateLoginTime(@Param("uid")Integer uid,@Param("now") Date date);

    <T> Integer deleteByIds(List<T> idList);

    List<OwnerPO> selectAll();
}