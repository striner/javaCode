package utils;

import common.utils.PageUtil;
import common.vo.Paged;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PageUtilTest {
    @Test
    public void test1() {
//        Paged page = new Paged(20, 25);
        Paged page = new Paged();
        page.setTotalPages(25);
        page.setPageNo(20);
        System.out.println(PageUtil.pageInfo(page));
        System.out.println(PageUtil.getCurPageNO(page));
        System.out.println(PageUtil.getPrePageNo(page));
        System.out.println(PageUtil.getNextPageNo(page));
    }

    @Test
    public void test2() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("data: " + i);
        }

        Paged<String> page = new Paged<>(list);
        System.out.println(PageUtil.pageInfo(page));
        System.out.println(PageUtil.nextPage(page));
        System.out.println(PageUtil.getCurrentPageData(page));
        PageUtil.endPage(page);
        System.out.println(PageUtil.getCurrentPageData(page));
    }

    @Test
    public void test3() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("data: " + i);
        }
        Paged page = new Paged(2, 20, list);
        System.out.println(PageUtil.pageInfo(page));
        System.out.println(PageUtil.getPrePageNo(page));
        System.out.println(PageUtil.getCurrentPageData(page));
        System.out.println(PageUtil.StepPrePageAndGetData(page));
        System.out.println(PageUtil.getNextPageData(page));
        System.out.println(PageUtil.StepPrePageAndGetData(page));
        System.out.println(PageUtil.StepNextPageAndGetData(page));
    }

    @Test
    public void test4() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("data: " + i);
        }
        Paged page = new Paged(2, 20, 15, list);
        System.out.println(PageUtil.pageInfo(page));
        System.out.println(PageUtil.getCurrentPageData(page));
        PageUtil.endPage(page);
        System.out.println(PageUtil.getCurrentPageData(page));
        PageUtil.firstPage(page);
        System.out.println(PageUtil.getCurrentPageData(page));
    }

    @Test
    public void test5() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add("data: " + i);
        }
        Paged page = new Paged(4, 4, 14, list);
        System.out.println(PageUtil.pageInfo(page));
        System.out.println(PageUtil.getCurrentPageData(page));
    }

    @Test
    public void test6() {
        Paged page = new Paged(1, 4, 14, new ArrayList());
        System.out.println(PageUtil.pageInfo(page));
        System.out.println(PageUtil.getCurrentPageData(page));
    }
}
