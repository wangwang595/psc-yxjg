package com.wavenet.maintenance.web.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QuestionsCountListVo {




    private List<QuestionsVo> questionsVos;

    private  QuestionsCountVo questionsCountVo;

  


}