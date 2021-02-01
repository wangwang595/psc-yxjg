package com.wavenet.maintenance.common;


import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder()
@NoArgsConstructor
@ApiModel
public class FindArticleDto extends PageDto {

//    public void setRows(int rows) {
//        this.setPageSize(rows);
//    }

}
