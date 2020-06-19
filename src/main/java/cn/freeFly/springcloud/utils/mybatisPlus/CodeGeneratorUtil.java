package cn.freeFly.springcloud.utils.mybatisPlus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CodeGeneratorUtil {
    public static final String[] TABLE_ARR = new String[] {"cacmp_refund_result"};

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    /*public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }*/

    public static void main(String[] args) throws IOException {
        //读取配置文件
        String fileName = "application-dev.yml";
        Resource resource = new ClassPathResource(fileName);
        Properties properties = null;
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource);
        properties =  yamlFactory.getObject();

        String drivername = properties.getProperty("spring.datasource.driver-class-name");
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        // 生成文件输出目录
        gc.setOutputDir(projectPath + "/src/main/java");
        // 开发人员
        gc.setAuthor("admin");
        // 是否打开输出目录
        gc.setOpen(false);
        //service命名方式
        gc.setServiceName("%sService");
        //service impl命名方式
        gc.setServiceImplName("%sServiceImpl");
        //自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        // xml 中自动生成返回对象
        gc.setBaseResultMap(true); // XML ResultMap
        // xml 中自动生成属性
//        gc.setBaseColumnList(true);
        //实体属性 Swagger2 注解
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(drivername);
        dsc.setUsername(username);
        dsc.setPassword(password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 自定义包路径
        pc.setParent("cn.freefly.springboot.mybatisPlus");
        // 设置包名-实体对象
        pc.setEntity("entity");
        // 设置包名-service
        pc.setService("service");
        // 设置包名-serviceImpl
        pc.setServiceImpl("service.impl");
        //设置包名-controller
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/mybatisPlus/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */

        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 自定义继承的Entity类全称，带包名
//        strategy.setSuperEntityClass("com.config.mybatisplus.BaseEntity");
        //【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        //生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        // 公共父类 自定义继承的Controller类全称，带包名
//        strategy.setSuperControllerClass("com.config.mybatisplus.BaseController");
        // 自定义基础的Entity类，公共字段
//        strategy.setSuperEntityColumns("id");
        // 需要包含的表名
        strategy.setInclude(TABLE_ARR);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        strategy.setTablePrefix("cacmp_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
