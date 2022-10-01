package org.xujin.janus.domain.change.source;

import lombok.Getter;
import org.apache.commons.lang3.Validate;

import java.io.Serializable;
import java.util.Objects;

/**
 * Desc:
 *
 * @author yage.luan
 * @date 2020/6/4 16:09
 **/
public class ChangeSourceInfo implements Serializable {

    @Getter
    private final String sourceCode;

    @Getter
    private final String sourceName;

    public ChangeSourceInfo(String sourceCode, String sourceName) {
        Validate.notBlank(sourceCode, "sourceCode缺失");
        Validate.notBlank(sourceName, "sourceName缺失");
        this.sourceCode = sourceCode;
        this.sourceName = sourceName;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChangeSourceInfo)) {
            return false;
        }
        ChangeSourceInfo that = (ChangeSourceInfo) other;
        return Objects.equals(sourceCode, that.sourceCode) &&
                Objects.equals(sourceName, that.sourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceCode, sourceName);
    }

}
