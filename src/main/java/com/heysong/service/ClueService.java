package com.heysong.service;

import com.github.pagehelper.PageInfo;
import com.heysong.domain.TClue;
import com.heysong.domain.TClueRemark;
import com.heysong.model.vo.TClueVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author: 8912
 * @Date: 2025/4/1 22:41
 * @Version: v1.0.0
 * @Description:
 **/
public interface ClueService {
    PageInfo<TClueVo> loadCluesByPage(Integer currentPage);

    Boolean uploadClueExcel(MultipartFile file,String token) throws IOException;

    TClue loadClueDetailById(Integer clueId);

    Integer addClue(TClue tClue,String token);

    Integer editClue(TClue tClue, String token);


}