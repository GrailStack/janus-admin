package org.xujin.janus.cluster.test.callServer;

import org.xujin.janus.app.server.client.ServerRequests;
import org.xujin.janus.client.cmo.SendFileCmd;
import org.xujin.janus.client.cmo.SendFileDTO;
import org.xujin.janus.damon.exchange.JanusCmdMsg;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author: ganshitao
 * @date: 2020/5/25
 */
public class SendFileTest {
    private static final String address = "127.0.0.1:8082";
    private static final String cluster = "default";
    private static final String version = "0.0.1";

    @Test
    public void testNewFilter() throws Exception {
        SendFileCmd sendFileCmd = new SendFileCmd();
        SendFileDTO sendFileDTO=new SendFileDTO();
        sendFileDTO.setFileName("PrintFilter.java");
        sendFileDTO.setFileType("filter");
        sendFileDTO.setFileContent(getAuthFilter_Java());
        sendFileCmd.setSendFileDTOS(Arrays.asList(sendFileDTO));
         ServerRequests.sendNewFile(address, cluster, version,"1", sendFileCmd);
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

    @Test
    public void testNewFilter_Groovy() throws Exception {
        SendFileCmd sendFileCmd = new SendFileCmd();
        SendFileDTO sendFileDTO=new SendFileDTO();
        sendFileDTO.setFileName("AuthGFilter.groovy");
        sendFileDTO.setFileType("filter");
        sendFileDTO.setFileContent(getAuthFilter_Groovy());
        sendFileCmd.setSendFileDTOS(Arrays.asList(sendFileDTO));
        ServerRequests.sendNewFile(address, cluster, version,"1", sendFileCmd);
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

    @Test
    public void testNewPredicate() throws Exception {
        SendFileCmd sendFileCmd = new SendFileCmd();
        SendFileDTO sendFileDTO=new SendFileDTO();
        sendFileDTO.setFileName("CookiePredicate.java");
        sendFileDTO.setFileType("predicate");
        sendFileDTO.setFileContent(getPathPredicate_java());
        sendFileCmd.setSendFileDTOS(Arrays.asList(sendFileDTO));
         ServerRequests.sendNewFile(address, cluster, version,"1", sendFileCmd);
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
    }

//    @Test
//    public void testNewPredicate_Groovy() throws Exception {
//        SendFileCmd sendFileCmd = new SendFileCmd();
//        sendFileCmd.setFileName("CookiePredicate.groovy");
//        sendFileCmd.setFileType("predicate");
//        sendFileCmd.setFileContent("newFileContent_Groovy11");
//        JanusCmdMsg janusCmdMsg = ServerRequests.sendNewFile(address, cluster, version, sendFileCmd);
//        Assert.assertTrue(janusCmdMsg.getCode() == 0);
//    }

    private String getAuthFilter_Java() {
        return "package org.xujin.janus.filter.filters;\n" +
                "\n" +
                "import org.xujin.janus.core.app.context.FilterContext;\n" +
                "import org.xujin.janus.core.app.filter.filters.AbstractSyncFilter;\n" +
                "import org.xujin.janus.core.app.filter.FilterType;\n" +
                "import org.xujin.janus.core.app.util.UriBuilder;\n" +
                "import io.netty.handler.codec.http.FullHttpRequest;\n" +
                "import io.netty.util.internal.StringUtil;\n" +
                "import org.slf4j.Logger;\n" +
                "import org.slf4j.LoggerFactory;\n" +
                "\n" +
                "import java.net.URI;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class PrintFilter extends AbstractSyncFilter {\n" +

                "    @Override\n" +
                "    public int order() {\n" +
                "        return 11;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public FilterType type() {\n" +
                "        return FilterType.INBOUND;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void doFilter(FilterContext context) {\n" +
                "        System.out.println(\"I am from printFilter........666\");\n" +
                "    }\n" +
                "}";
    }

    private String getAuthFilter_Groovy() {
        return "package org.xujin.janus.filter.filters\n" +
                "\n" +
                "import org.xujin.janus.core.app.context.FilterContext\n" +
                "import org.xujin.janus.core.app.filter.filters.AbstractSyncFilter\n" +
                "import org.xujin.janus.core.app.filter.FilterType\n" +
                "\n" +
                "/**\n" +
                " * @author: ganshitao* @date: 2020/5/25\n" +
                " */\n" +
                "class AuthGFilter extends AbstractSyncFilter {\n" +

                "    @Override\n" +
                "    void doFilter(FilterContext context) {\n" +
                "        System.out.println(\"authG groovy from admin.....666\")\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    int order() {\n" +
                "        return 0\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    FilterType type() {\n" +
                "        return FilterType.AFTER_OUT;\n" +
                "    }\n" +
                "}\n";
    }

    private String getPathPredicate_java() {
        return "package org.xujin.janus.core.app.predicates;\n" +
                "\n" +
                "import org.xujin.janus.core.app.context.FilterContext;\n" +
                "import org.xujin.janus.config.util.StringUtils;\n" +
                "import org.xujin.janus.core.app.route.Route;\n" +
                "import io.netty.handler.codec.http.FullHttpRequest;\n" +
                "\n" +
                "import java.net.URI;\n" +
                "import java.util.Arrays;\n" +
                "import java.util.List;\n" +
                "import java.util.Map;\n" +
                "import java.util.regex.Pattern;\n" +
                "import java.util.stream.Collectors;\n" +
                "\n" +
                "/**\n" +
                " * @author: gan\n" +
                " * @date: 2020/4/17\n" +
                " * predicates: # and ; if need or add a new route\n" +
                " * - name: Path\n" +
                " * args:\n" +
                " * pattern: /delay/{timeout}\n" +
                " * or\n" +
                " * <p>\n" +
                " * predicates: # and ; if need or add a new route\n" +
                " * - Path = /api/service\n" +
                " */\n" +
                "public class PathPredicate extends AbstractPredicate {\n" +
                "    private static final String PATTERN_ARG_KEY = \"pattern\";\n" +
                "    private List<Pattern> pathPatterns;\n" +
                "\n" +
                "    @Override\n" +
                "    public boolean test(FilterContext context) {\n" +
                "        FullHttpRequest fullHttpRequest=context.getCtx().getOriFullHttpRequest();\n" +
                "        Map<String,String> predicateConfig=getPredicateConfig(context);\n" +
                "        setPathPatterns(predicateConfig);\n" +
                "        if (fullHttpRequest == null || fullHttpRequest.uri() == null) {\n" +
                "            return Boolean.FALSE;\n" +
                "        }\n" +
                "        String uriStr = fullHttpRequest.uri();\n" +
                "        URI uri = URI.create(uriStr);\n" +
                "        String path = uri.getPath();\n" +
                "        for (int i = 0; i < pathPatterns.size(); i++) {\n" +
                "            if (pathPatterns.get(i).matcher(path).matches()) {\n" +
                "                return Boolean.TRUE;\n" +
                "            }\n" +
                "        }\n" +
                "        return Boolean.FALSE;\n" +
                "    }\n" +
                "\n" +
                "    private void setPathPatterns( Map<String,String> predicateConfig) {\n" +
                "        String path;\n" +
                "        if (predicateConfig.containsKey(PATTERN_ARG_KEY)) {\n" +
                "            path = predicateConfig.get(PATTERN_ARG_KEY);\n" +
                "        } else {\n" +
                "            path = predicateConfig.get(predicateConfig.keySet().stream().findFirst());\n" +
                "        }\n" +
                "        String[] regexPaths = StringUtils.tokenizeToStringArray(path, \",\");\n" +
                "        pathPatterns = Arrays.stream(regexPaths).map(e -> Pattern.compile(e)).collect(Collectors.toList());\n" +
                "    }\n" +
                "\n" +
                "\n" +
                "}\n";
    }
}
