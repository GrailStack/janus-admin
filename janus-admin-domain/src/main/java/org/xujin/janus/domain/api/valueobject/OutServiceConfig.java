package org.xujin.janus.domain.api.valueobject;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/6 11:11
 **/
@Data
@Accessors(chain = true)
public class OutServiceConfig implements Serializable {

    @NotNull(message = "outServices of outServiceConfig is required")
    @Size(min = 1, message = "outServices of outServiceConfig is required")
    private List<@Valid OutServiceVO> outServices;

}
