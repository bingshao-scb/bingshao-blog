package com.bs.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

/**
 * @author bingshao
 * @date 2021/8/24
 **/
@Data
public class AdminInfoDto implements Serializable {
    private static final long serialVersionUID = 1005489934382436118L;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long id;

    private String nickname;

    private String pictureUrl;

}
