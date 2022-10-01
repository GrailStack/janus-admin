package org.xujin.janus.app.command.cmo.query;

import org.xujin.halo.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/19 20:51
 **/
@Data
public class QueryMultiDictCmd extends Command {

    @NotEmpty(message = "dictCodes不正确")
    private List<String> dictCodes;

}
