package org.xujin.janus.app.command.cmo;

import lombok.Data;

@Data
public class AddDictTypeCmd extends CommonCommand {

    private String dictName;

    private String dictCode;

    private Byte status;

}
