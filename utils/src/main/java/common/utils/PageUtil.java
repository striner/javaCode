package common.utils;

import common.vo.Paged;
import java.util.List;

/**
 * create by mulin on 2018/08/23
 */
public class PageUtil {

    /**
     * 获取当前页页码
     */
    public static int getCurPageNO(Paged page) {
        return page.getPageNo();
    }

    /**
     * 获取上一页页码
     */
    public static int getPrePageNo(Paged page) {
        return page.getPrevPageNo();
    }

    /**
     * 获取下一页页码
     */
    public static int getNextPageNo(Paged page) {
        return page.getNextPageNo();
    }

    /**
     * 跳转到下一页
     */
    public static Paged nextPage(Paged page) {
        if (page.haveNextPage()) {
            page.setPageNo(page.getPageNo()+1);
        }
        return page;
    }

    /**
     * 跳转到上一页
     */
    public static Paged prePage(Paged page) {
        if (page.havePrevPage()) {
            page.setPageNo(page.getPageNo()-1);
        }
        return page;
    }

    /**
     * 跳转到首页
     */
    public static Paged firstPage(Paged page) {
        page.setPageNo(1);
        return page;
    }

    /**
     * 跳转到页尾
     */
    public static Paged endPage(Paged page) {
        page.setPageNo(page.getTotalPages());
        return page;
    }

    /**
     * 获取当前页数据
     */
    public static List getCurrentPageData(Paged page) {
        if ((page.getPageNo()-1)*page.getPageSize() > page.getData().size()) {
            return null;
        }

        int startNum = page.getPageSize()*(page.getPageNo()-1);
        int endNum = page.getPageSize()*page.getPageNo();
        endNum = endNum < page.getTotalRows() ? endNum : page.getTotalRows();
        return page.getData().subList(startNum, endNum);
    }

    /**
     * 获取上一页数据
     */
    public static List getPrePageData(Paged page) {
        List list = null;
        if (page.havePrevPage()) {
            list = StepPrePageAndGetData(page);
            nextPage(page);
        }
        return list;
    }

    /**
     * 跳转到上一页并打印数据
     */
    public static List StepPrePageAndGetData(Paged page) {
        return getCurrentPageData(prePage(page));
    }

    /**
     * 获取下一页数据
     */
    public static List getNextPageData(Paged page) {
        List list = null;
        if (page.haveNextPage()) {
            list = StepNextPageAndGetData(page);
            prePage(page);
        }
        return list;
    }

    /**
     * 跳转到下一页并打印数据
     */
    public static List StepNextPageAndGetData(Paged page) {
        return getCurrentPageData(nextPage(page));
    }

    /**
     * 打印Paged信息
     */
    public static String pageInfo(Paged page) {
        return page.toString();
    }
}