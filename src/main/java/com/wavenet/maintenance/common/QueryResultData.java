package com.wavenet.maintenance.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryResultData<T> {
    private long total;//总记录数
    private List<T> rows; //当前记录
    private int pages;//总页数
    private int pageNum;//当前页码
    private int pageSize;//每页记录数

    public static <T> QueryResultData<T> fromPageHelperList(com.github.pagehelper.Page page) {
        QueryResultData<T> pd = new QueryResultData<T>();
        pd.total = page.getTotal();
        pd.rows = page.getResult();
        pd.pages = page.getPages();
        pd.pageNum = page.getPageNum();
        pd.pageSize = page.getPageSize();
        return pd;
    }
}
