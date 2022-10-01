package org.xujin.janus.app.command.co;


import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.sql.Timestamp;

/**
 * 数据字典详情Model
 * @author xujin
 */
@Data
public class DictDataCO {

	private Long id;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp gmtCreate;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Timestamp gmtModified;

	private String dictCode;

	private String itemName;

	private String itemCode;

	private String itemValue;

	private String itemDesc;

	private int itemSort;

	private Byte status;


}
