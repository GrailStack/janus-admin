package org.xujin.janus.app.command.co;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.List;

@Data
@Accessors(chain = true)
public class DictTypeListCO {

    private Long id;

    private String dictName;

    private String dictCode;

    private Byte status;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtCreate;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Timestamp gmtModified;

    private List<DictDataCO> dictDataModelList;



}
