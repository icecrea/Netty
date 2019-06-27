package com.example.cp10http.xml.test;

import org.jibx.binding.Compile;
import org.jibx.binding.generator.BindGen;
import org.jibx.runtime.JiBXException;

import java.io.IOException;

/**
 * @description:
 *
 *    可以通过命令行生成bing.xml和pojo.xsd
 *    注意：要使用官网下载的包，不能用maven仓库中的，该格式会少包 以下命令会生成 binding.xml和pojo.xsd 文件
 *
 *    进入classes文件目录 cd F:\IdeaProj\Netty\target\classes\
 *    java -cp E:\jibx\jibx_1_3_1\jibx\lib\jibx-tools.jar org.jibx.binding.generator.BindGen -b binding.xml com.example.cp10http.xml.pojo.Order
 * @author: icecrea
 * @create: 2019-06-26 13:42
 **/
public class GenerateBindXmlUtil {
    public static void main(String[] args) throws JiBXException, IOException {

//        genBindFiles();
        compile();
    }

    /**
     * 注意要通过编译后，再运行TestOrder
     */
    private static void compile() {
        String[] args = new String[2];

        // 打印生成过程的详细信息。可选
        args[0] = "-v";

        // 指定 binding 和 schema 文件的路径。必须
        args[1] = "./binding.xml";

        Compile.main(args);
    }

    private static void genBindFiles() throws JiBXException, IOException {
        String[] args = new String[9];

        // 指定pojo源码路径（指定父包也是可以的）。必须
        args[0] = "-s";
        args[1] = "src";

        // 自定义生成的binding文件名，默认文件名binding.xml。可选
        args[2] = "-b";
        args[3] = "binding.xml";

        // 打印生成过程的一些信息。可选
        args[4] = "-v";

        // 如果目录已经存在，就删除目录。可选
        args[5] = "-w";

        //- t 指定xml和xsd输出路径 路径。默认路径 .（当前目录,即根目录）。
        args[6] = "-t";
        args[7] = "./src/main/java/com/example/cp10http/xml/pojo/order";

        // 告诉 BindGen 使用下面的类作为 root 生成 binding 和 schema。必须
        args[8] = "com.example.cp10http.xml.pojo.Order";

        BindGen.main(args);
    }

}
