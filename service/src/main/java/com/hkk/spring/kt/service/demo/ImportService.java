package com.hkk.spring.kt.service.demo;

import com.mi.oa.hr.miesop.domain.entity.param.CommonParam;
import com.mi.oa.hr.miesop.domain.enums.ExcelActionTypeEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author kang
 * @date 2023/9/18
 */
public interface ImportService {

    /**
     * 执行导出
     */
    String importData(CommonParam commonParam, MultipartFile multipartFile, String paramStr);

    /**
     * 支持的导出类型
     *
     * @return 导出类型
     */
    ImportType supportType();


    default int actionType() {
        return ExcelActionTypeEnum.IMPORT.getCode();
    }


    interface ImportType {

        /**
         * 导出类型
         *
         * @return int
         */
        int getCode();

        /**
         * 报表描述
         */
        String getDesc();

        /**
         * 是否异步导入
         *
         * @return boolean
         */
        default boolean isAsync() {
            return true;
        }

        /**
         * 导出文件是否需要加密
         *
         * @return boolean
         */
        default boolean needEncrypt() {
            return true;
        }

    }
}
