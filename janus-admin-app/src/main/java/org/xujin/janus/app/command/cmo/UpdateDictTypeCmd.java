package org.xujin.janus.app.command.cmo;

import org.xujin.halo.dto.Command;
import lombok.Data;

@Data
public class UpdateDictTypeCmd extends Command {

    private Long id;

    private String dictName;

    private String dictCode;

    private Byte status;
}


