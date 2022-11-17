package com.zklt.parsing.model.enums;

/**
 * @author jhzhu
 * @date 2022/11/17 16:24
 * @Description:
 */
public enum FileTypeEnums {

    COMMON("dat,txt"),
    SPECIAL("nc,cdf,fits");

    String fileType;

    FileTypeEnums(String fileType){
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}
