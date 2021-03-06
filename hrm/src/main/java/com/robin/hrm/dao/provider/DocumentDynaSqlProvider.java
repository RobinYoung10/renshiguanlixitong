package com.robin.hrm.dao.provider;

import com.robin.hrm.domain.Document;
import com.robin.hrm.domain.Notice;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

import static com.robin.hrm.util.common.HrmConstants.DOCUMENTTABLE;

public class DocumentDynaSqlProvider {
    //分页查询
    public String selectWithParam(final Map<String, Object> params) {
        String sql = new SQL() {
            {
                SELECT("*");
                FROM(DOCUMENTTABLE);
                if(params.get("document") != null) {
                    Document document = (Document) params.get("document");
                    if(document.getTitle() != null && !document.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT('%', #{document.title}, '%')");
                    }
                }
            }
        }.toString();
        if(params.get("pageModel") != null) {
            sql += " limit #{pageModel.firstLimitParam}, #{pageModel.pageSize} ";
        }
        return sql;
    }

    //动态查询总量
    public String count(final Map<String, Object> params) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM(DOCUMENTTABLE);
                if(params.get("document") != null) {
                    Document document = (Document) params.get("document");
                    if(document.getTitle() != null && !document.getTitle().equals("")) {
                        WHERE(" title LIKE CONCAT('%', #{document.title}, '%')");
                    }
                }
            }
        }.toString();
    }

    //动态插入
    public String insertDocument(final Document document) {
        return new SQL() {
            {
                INSERT_INTO(DOCUMENTTABLE);
                if(document.getTitle() != null && !document.getTitle().equals("")) {
                    VALUES("title", "#{title}");
                }
                if(document.getFileName() != null && !document.getFileName().equals("")) {
                    VALUES("filename", "#{fileName}");
                }
                if(document.getRemark() != null && !document.getRemark().equals("")) {
                    VALUES("remark", "#{remark}");
                }
                if(document.getUser() != null && !document.getUser().equals("")) {
                    VALUES("user_id", "#{user.id}");
                }
            }
        }.toString();
    }

    //动态更新
    public String updateDocument(final Document document) {
        return new SQL() {
            {
                UPDATE(DOCUMENTTABLE);
                if(document.getTitle() != null && !document.getTitle().equals("")) {
                    SET("title", "#{title}");
                }
                if(document.getFileName() != null && !document.getFileName().equals("")) {
                    SET("filename", "#{fileName}");
                }
                if(document.getRemark() != null && !document.getRemark().equals("")) {
                    SET("remark", "#{remark}");
                }
                if(document.getUser() != null && !document.getUser().equals("")) {
                    SET("user_id", "#{user.id}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
