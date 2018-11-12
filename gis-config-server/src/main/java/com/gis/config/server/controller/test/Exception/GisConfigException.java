package com.gis.config.server.controller.test.Exception;

public class GisConfigException {

    public static final int intFilePathError =1000;
    public static final String strFilePathError = "配置文件路径错误";

    public static final int intExistFileError =1001;
    public static final String strExistFileError = "已存在该名称的配置文件";

    public static final int intBuildFileError =1002;
    public static final String strBuildFileError = "创建配置文件失败";

    public static final int intNonentityFileError =1003;
    public static final String strNonentityFileError = "配置文件不存在";

    public static final int intUpdataFileError =1004;
    public static final String strUpdataFileError = "更新配置文件失败";

}
